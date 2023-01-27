package com.whiz.customermanagement.customerService.service;

import com.whiz.customermanagement.customerService.api.model.Client;
import com.whiz.customermanagement.customerService.api.model.StatisticsClient;

import java.util.List;

public interface ClientService {

    void createClient(Client client);

    List<Client> getAllClients();

    StatisticsClient getClientsKpi();

}
