package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.model.model_oracle.VehicleOracle;
import com.arthenyo.rotapro_backend.model.model_postgresql.VehiclePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.Availabilities;
import com.arthenyo.rotapro_backend.repositories.repository_oracle.VehicleOracleRepository;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.VehiclePostgresqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehiclePostgresqlRepository vehiclePostgresqlRepository;
    @Autowired
    private VehicleOracleRepository vehicleOracleRepository;

    public String createVehicle() {
        List<VehicleOracle> vehicleOracles = vehicleOracleRepository.getAllVehicles();
        List<VehiclePostgresql> vehiclePostgresqls = new ArrayList<>();
        if (vehicleOracles.isEmpty()) {
            return "No vehicles found in Oracle Database!";
        }

        for (VehicleOracle vehicleOracle : vehicleOracles) {
            if (!vehiclePostgresqlRepository.existsByCodVehicle(vehicleOracle.getCodVehicle())) {
                VehiclePostgresql vehicle = new VehiclePostgresql();
                vehicle.setCodVehicle(vehicleOracle.getCodVehicle());
                vehicle.setPlate(vehicleOracle.getPlate());
                vehicle.setDescricao(vehicleOracle.getDescricao());
                vehicle.setMark(vehicleOracle.getMark());
                vehicle.setTypeVehicle(vehicleOracle.getTypeVehicle());
                vehicle.setColor(vehicleOracle.getColor());
                vehicle.setCapacity(null);
                vehicle.setQntEixos(0);
                vehicle.setQntRodas(0);
                vehicle.setQntLitros(0.0);
                vehicle.setStatus(true);
                vehicle.setAvailabilities(Availabilities.AVAILABLE);
                vehiclePostgresqls.add(vehicle);
            } else {
                return "No to any new registrations!";
            }
        }
        vehiclePostgresqlRepository.saveAll(vehiclePostgresqls);
        return "Vehicle created successfully!";
    }
}
