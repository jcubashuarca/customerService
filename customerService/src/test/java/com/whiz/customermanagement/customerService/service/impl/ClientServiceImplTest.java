package com.whiz.customermanagement.customerService.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import com.whiz.customermanagement.customerService.BaseSpringTest;
import com.whiz.customermanagement.customerService.api.model.Client;
import com.whiz.customermanagement.customerService.config.AppProperties;
import com.whiz.customermanagement.customerService.entity.Clients;
import com.whiz.customermanagement.customerService.repository.ClientRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static com.whiz.customermanagement.customerService.util.WiremockUtil.obtainResource;

@Slf4j
public class ClientServiceImplTest extends BaseSpringTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepositoryMock;

    @Mock
    private AppProperties appPropertiesMock;

    @Autowired
    private AppProperties appProperties;

    @Before
    public void load() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createClient() throws Exception {
        when(appPropertiesMock.getLifeExpectancyMan()).thenReturn(Math.toIntExact(63));
        when(clientRepositoryMock.save(new Clients())).thenReturn(new Clients());
        clientService.createClient(getClient());
    }

    @Test
    public void getAllClients() throws Exception {
        when(clientRepositoryMock.findAll()).thenReturn(getClientEntity());
        clientService.getAllClients();
    }

    @Test
    public void getClientsKpi() throws Exception {
        when(clientRepositoryMock.findAll()).thenReturn(getClientEntity());
        clientService.getClientsKpi();
    }

    private Client getClient() throws IOException {
        return new ObjectMapper().readValue(obtainResource("json/create_client.json"), Client.class);
    }

    private List<Clients> getClientEntity() {
        List<Clients> ls = new ArrayList<>();
        ls.add(createClientsEntity("Juan", "Perez", 23));
        ls.add(createClientsEntity("Jorge", "Perez", 34));
        return ls;
    }

    private Clients createClientsEntity(String firstName, String lastName, int age) {
        Clients clients = Clients.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .age(new BigDecimal(age))
                .birthDate(new Date())
                .attemptDeathDate(new Date()).build();
        log.info("object client: {} ", clients.toString());
        return clients;
    }

}
