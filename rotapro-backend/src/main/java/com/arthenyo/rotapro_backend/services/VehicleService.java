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
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehiclePostgresqlRepository vehiclePostgresqlRepository;
    @Autowired
    private VehicleOracleRepository vehicleOracleRepository;

    public String syncVehicles() {
        // Recupera todos os veículos do Oracle
        List<VehicleOracle> vehicleOracles = vehicleOracleRepository.getAllVehicles();
        if (vehicleOracles.isEmpty()) {
            return "No vehicles found in Oracle Database!";
        }

        List<VehiclePostgresql> vehiclesToSave = new ArrayList<>();

        // Percorre a lista de veículos do Oracle
        for (VehicleOracle vehicleOracle : vehicleOracles) {
            // Verifica se o veículo já existe no PostgreSQL
            Optional<VehiclePostgresql> existingVehicleOptional = vehiclePostgresqlRepository.findByCodVehicle(vehicleOracle.getCodVehicle());

            if (existingVehicleOptional.isPresent()) {
                // Atualiza os campos necessários para registros existentes
                VehiclePostgresql existingVehicle = existingVehicleOptional.get();
                boolean isUpdated = false;

                if (!existingVehicle.getPlate().equals(vehicleOracle.getPlate())) {
                    existingVehicle.setPlate(vehicleOracle.getPlate());
                    isUpdated = true;
                }

                if (!existingVehicle.getDescricao().equals(vehicleOracle.getDescricao())) {
                    existingVehicle.setDescricao(vehicleOracle.getDescricao());
                    isUpdated = true;
                }

                if (!existingVehicle.getMark().equals(vehicleOracle.getMark())) {
                    existingVehicle.setMark(vehicleOracle.getMark());
                    isUpdated = true;
                }

                if (!existingVehicle.getTypeVehicle().equals(vehicleOracle.getTypeVehicle())) {
                    existingVehicle.setTypeVehicle(vehicleOracle.getTypeVehicle());
                    isUpdated = true;
                }

                if (!existingVehicle.getColor().equals(vehicleOracle.getColor())) {
                    existingVehicle.setColor(vehicleOracle.getColor());
                    isUpdated = true;
                }

                if (isUpdated) {
                    vehiclesToSave.add(existingVehicle);
                }
            } else {
                // Cria um novo registro se não existir no PostgreSQL
                VehiclePostgresql newVehicle = new VehiclePostgresql();
                newVehicle.setCodVehicle(vehicleOracle.getCodVehicle());
                newVehicle.setPlate(vehicleOracle.getPlate());
                newVehicle.setDescricao(vehicleOracle.getDescricao());
                newVehicle.setMark(vehicleOracle.getMark());
                newVehicle.setTypeVehicle(vehicleOracle.getTypeVehicle());
                newVehicle.setColor(vehicleOracle.getColor());
                newVehicle.setCapacity(null);
                newVehicle.setQntEixos(0);
                newVehicle.setQntRodas(0);
                newVehicle.setQntLitros(0.0);
                newVehicle.setStatus(true);
                newVehicle.setAvailabilities(Availabilities.AVAILABLE);
                vehiclesToSave.add(newVehicle);
            }
        }

        // Salva os registros atualizados ou novos
        if (!vehiclesToSave.isEmpty()) {
            vehiclePostgresqlRepository.saveAll(vehiclesToSave);
            return "Vehicles synchronized successfully!";
        }

        return "No changes detected for vehicles.";
    }
}
