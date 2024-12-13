package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.model.model_oracle.ClientOracle;
import com.arthenyo.rotapro_backend.model.model_postgresql.ClientPostgresql;
import com.arthenyo.rotapro_backend.repositories.repository_oracle.ClientOracleRepository;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.ClientPostgresqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientPostgresqlRepository clientPostgresqlRepository;
    @Autowired
    private ClientOracleRepository clientOracleRepository;

    public String syncClients() {
        List<ClientOracle> clientOracleList = clientOracleRepository.getAllClient();
        if (clientOracleList.isEmpty()) {
            return "No client found in Oracle Database!";
        }

        List<ClientPostgresql> clientToSave = new ArrayList<>();

        for (ClientOracle clientOracle : clientOracleList) {

            Optional<ClientPostgresql> existingClientOptional = clientPostgresqlRepository.findByCodClient(clientOracle.getCodcli());

            if (existingClientOptional.isPresent()) {
                ClientPostgresql existingClient = existingClientOptional.get();
                boolean isUpdated = false;

                if (!existingClient.getName().equals(clientOracle.getClient())) {
                    existingClient.setName(existingClient.getName());
                    isUpdated = true;
                }

                if (!existingClient.getAddress().equals(clientOracle.getAddress())) {
                    existingClient.setAddress(clientOracle.getAddress());
                    isUpdated = true;
                }

                if (!existingClient.getLatitude().equals(clientOracle.getLatitude())) {
                    existingClient.setLatitude(clientOracle.getLatitude());
                    isUpdated = true;
                }

                if (!existingClient.getLongitude().equals(clientOracle.getLongitude())) {
                    existingClient.setLongitude(clientOracle.getLongitude());
                    isUpdated = true;
                }

                if (isUpdated) {
                    clientToSave.add(existingClient);
                }
            } else {
                ClientPostgresql newClient = new ClientPostgresql();
                newClient.setCodClient(clientOracle.getCodcli());
                newClient.setName(clientOracle.getClient());
                newClient.setAddress(clientOracle.getAddress());
                newClient.setLatitude(clientOracle.getLatitude());
                newClient.setLongitude(clientOracle.getLongitude());
                clientToSave.add(newClient);
            }
        }

        if (!clientToSave.isEmpty()) {
            clientPostgresqlRepository.saveAll(clientToSave);
            return "Clients synchronized successfully!";
        }

        return "No changes detected for clients.";
    }
}
