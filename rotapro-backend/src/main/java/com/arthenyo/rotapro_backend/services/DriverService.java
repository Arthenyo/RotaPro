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

@Service
public class DriverService {

    @Autowired
    private DriverPostgresqlRepository driverPostgresqlRepository;
    @Autowired
    private DriverOracleRepository driverOracleRepository;

    public String createDriver(){
        List<DriverOracle> driverOracleList = driverOracleRepository.getAllDriver();
        List<DriverPostgresql> driverPostgresqlList = new ArrayList<>();
        if(driverOracleList.isEmpty()){
            return "No drivers found in Oracle Database!";
        }
        for(DriverOracle driverOracle : driverOracleList){
            if(!driverPostgresqlRepository.existsByRegistration(driverOracle.getRegistration())){
                DriverPostgresql driver = new DriverPostgresql();
                driver.setRegistration(driverOracle.getRegistration());
                driver.setName(driverOracle.getName());
                driver.setCnh(driverOracle.getCnh());
                driver.setCategoryCNH(driverOracle.getCategoryCNH());
                driver.setValidityCNH(driverOracle.getValidityCNH());
                driver.setStatus(Boolean.TRUE);
                driver.setAvailabilities(Availabilities.AVAILABLE);
                driverPostgresqlList.add(driver);
            }else {
                return "No to any new registrations!";
            }
        }
        driverPostgresqlRepository.saveAll(driverPostgresqlList);

        return "Drivers created successfully!";
    }


}
