package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.model.model_oracle.DriverOracle;
import com.arthenyo.rotapro_backend.model.model_postgresql.DriverPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.Availabilities;
import com.arthenyo.rotapro_backend.repositories.repository_oracle.DriverOracleRepository;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.DriverPostgresqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverPostgresqlRepository driverPostgresqlRepository;
    @Autowired
    private DriverOracleRepository driverOracleRepository;

    public String syncDrivers() {
        List<DriverOracle> driverOracleList = driverOracleRepository.getAllDriver();
        if (driverOracleList.isEmpty()) {
            return "No drivers found in Oracle Database!";
        }

        List<DriverPostgresql> driversToSave = new ArrayList<>();

        for (DriverOracle driverOracle : driverOracleList) {

            Optional<DriverPostgresql> existingDriverOptional = driverPostgresqlRepository.findByRegistration(driverOracle.getRegistration());

            if (existingDriverOptional.isPresent()) {
                DriverPostgresql existingDriver = existingDriverOptional.get();
                boolean isUpdated = false;

                if (!existingDriver.getName().equals(driverOracle.getName())) {
                    existingDriver.setName(driverOracle.getName());
                    isUpdated = true;
                }

                if (!existingDriver.getCnh().equals(driverOracle.getCnh())) {
                    existingDriver.setCnh(driverOracle.getCnh());
                    isUpdated = true;
                }

                if (!existingDriver.getCategoryCNH().equals(driverOracle.getCategoryCNH())) {
                    existingDriver.setCategoryCNH(driverOracle.getCategoryCNH());
                    isUpdated = true;
                }

                if (!existingDriver.getValidityCNH().equals(driverOracle.getValidityCNH())) {
                    existingDriver.setValidityCNH(driverOracle.getValidityCNH());
                    isUpdated = true;
                }

                if (isUpdated) {
                    driversToSave.add(existingDriver);
                }
            } else {
                DriverPostgresql newDriver = new DriverPostgresql();
                newDriver.setRegistration(driverOracle.getRegistration());
                newDriver.setName(driverOracle.getName());
                newDriver.setCnh(driverOracle.getCnh());
                newDriver.setCategoryCNH(driverOracle.getCategoryCNH());
                newDriver.setValidityCNH(driverOracle.getValidityCNH());
                newDriver.setStatus(Boolean.TRUE);
                newDriver.setAvailabilities(Availabilities.AVAILABLE);
                driversToSave.add(newDriver);
            }
        }

        if (!driversToSave.isEmpty()) {
            driverPostgresqlRepository.saveAll(driversToSave);
            return "Drivers synchronized successfully!";
        }

        return "No changes detected for drivers.";
    }

}
