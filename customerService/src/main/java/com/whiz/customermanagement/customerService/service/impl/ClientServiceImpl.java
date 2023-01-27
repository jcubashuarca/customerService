package com.whiz.customermanagement.customerService.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.whiz.customermanagement.customerService.api.model.Client;
import com.whiz.customermanagement.customerService.api.model.StatisticsClient;
import com.whiz.customermanagement.customerService.config.AppProperties;
import com.whiz.customermanagement.customerService.entity.Clients;
import com.whiz.customermanagement.customerService.repository.ClientRepository;
import com.whiz.customermanagement.customerService.service.ClientService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.whiz.customermanagement.customerService.constant.Constants.BIRTH_DATE_FORMAT;
import static com.whiz.customermanagement.customerService.util.DateUtils.formatDate;
import static com.whiz.customermanagement.customerService.util.DateUtils.getAttemptDeathDateByAge;
import static com.whiz.customermanagement.customerService.util.DateUtils.getStringFromDate;

@Slf4j
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private AppProperties appProperties;

    @Override
    public void createClient(Client client) {
        clientRepository.save(
                Clients.builder()
                        .id(UUID.randomUUID())
                        .firstName(client.getName())
                        .lastName(client.getLastName())
                        .age(new BigDecimal(client.getAge()))
                        .birthDate(formatDate(BIRTH_DATE_FORMAT, client.getBirthDate()))
                        .attemptDeathDate(getAttemptDeathDateByAge(appProperties, client.getAge()))
                        .build());
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(cli -> Client.builder()
                        .name(cli.getFirstName())
                        .lastName(cli.getLastName())
                        .age(cli.getAge().intValue())
                        .birthDate(getStringFromDate(BIRTH_DATE_FORMAT, cli.getBirthDate()))
                        .attemptDeathDate(getStringFromDate(BIRTH_DATE_FORMAT, cli.getAttemptDeathDate()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public StatisticsClient getClientsKpi() {
        List<Clients> clients = clientRepository.findAll();
        return StatisticsClient
                .builder()
                .averageAge(
                        clients.stream()
                                .mapToInt(cli -> cli.getAge().intValue())
                                .average().getAsDouble())
                .standardDeviationAge(
                        calculateStandardDeviationFromList(
                                clients.stream()
                                        .mapToDouble(cli -> cli.getAge().doubleValue())
                                        .boxed()
                                        .collect(Collectors.toList()))
                ).build();
    }

    public double calculateStandardDeviationFromList(List<Double> ageClientList) {
        return Math.sqrt(
                ageClientList
                        .stream()
                        .reduce(0.0, (a, b) -> a + Math.pow(b - (
                                ageClientList
                                        .stream()
                                        .reduce(0.0, Double::sum) / ageClientList.size()), 2)
                        ) / ageClientList.size());
    }

}



