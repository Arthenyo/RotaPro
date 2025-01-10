package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.dto.GasStationUsageDTO;
import com.arthenyo.rotapro_backend.dto.DriverSupplyCountDTO;
import com.arthenyo.rotapro_backend.dto.DriverTotalCostDTO;
import com.arthenyo.rotapro_backend.dto.FuelSupplyDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.*;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.TypeEstablishments;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.*;
import com.arthenyo.rotapro_backend.services.exception.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelSupplyService {

    @Autowired
    private FuelSupplyPostgresqlRepository fuelSupplyPostgresqlRepository;
    @Autowired
    private DriverPostgresqlRepository driverPostgresqlRepository;
    @Autowired
    private VehiclePostgresqlRepository vehiclePostgresqlRepository;
    @Autowired
    private RoutePostgresqlRepository routePostgresqlRepository;
    @Autowired
    private EstablishmentsPostgresqlRepository establishmentsPostgresqlRepository;
    public Page<FuelSupplyDTO> findByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Page<FuelSupplyPostgresql> pageRange = fuelSupplyPostgresqlRepository.findAllBetweenDates(startDate, endDate, pageable);
        return pageRange.map(FuelSupplyDTO::new);
    }

    public Page<FuelSupplyDTO> findByVehicleAndDateRange(Long vehicleId, LocalDate startDate, LocalDate endDate,Pageable pageable) {
        Page<FuelSupplyPostgresql> pageVehicle = fuelSupplyPostgresqlRepository.findAllByVehicleAndDateRange(vehicleId, startDate, endDate, pageable);
        return pageVehicle.map(FuelSupplyDTO::new);
    }

    public List<GasStationUsageDTO> getGasStationUsage() {
        List<Object[]> results = fuelSupplyPostgresqlRepository.findGasStationUsageCount();
        List<GasStationUsageDTO> dtoList = new ArrayList<>();

        for (Object[] row : results) {
            String stationName = (String) row[0];
            Long count = (Long) row[1];

            GasStationUsageDTO dto = new GasStationUsageDTO(stationName, count);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<DriverSupplyCountDTO> getSuppliesCountByDriver() {
        List<Object[]> results = fuelSupplyPostgresqlRepository.countSuppliesByDriver();

        List<DriverSupplyCountDTO> dtoList = new ArrayList<>();
        for (Object[] row : results) {
            String driverName = (String) row[0];
            Long countSupplies = (Long) row[1];

            DriverSupplyCountDTO dto = new DriverSupplyCountDTO(driverName, countSupplies);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<DriverTotalCostDTO> getTotalCostByDriver() {
        List<Object[]> results = fuelSupplyPostgresqlRepository.sumCostByDriver();

        List<DriverTotalCostDTO> dtoList = new ArrayList<>();
        for (Object[] row : results) {
            String driverName = (String) row[0];
            Double totalCost = (Double) row[1];

            DriverTotalCostDTO dto = new DriverTotalCostDTO(driverName, totalCost);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public FuelSupplyDTO createFuelSupply(FuelSupplyDTO dto){
        FuelSupplyPostgresql entity = new FuelSupplyPostgresql();
        copyDtoToEntity(entity,dto);
        entity = fuelSupplyPostgresqlRepository.save(entity);
        return new FuelSupplyDTO(entity);
    }

    public FuelSupplyDTO updateFuelSupply(Long id, FuelSupplyDTO dto) {
        FuelSupplyPostgresql entity = fuelSupplyPostgresqlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException("ERRO: Abastecimento ID " + id + " não encontrado."));
        copyDtoToEntity(entity, dto);
        entity = fuelSupplyPostgresqlRepository.save(entity);
        return new FuelSupplyDTO(entity);
    }

    public void deleteFuelSupply(Long id) {
        FuelSupplyPostgresql entity = fuelSupplyPostgresqlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException("ERRO: Abastecimento ID " + id + " não encontrado."));
        fuelSupplyPostgresqlRepository.delete(entity);
    }

    private void copyDtoToEntity(FuelSupplyPostgresql entity, FuelSupplyDTO dto) {
        if (dto.getRoute() != null) {
            Optional<RoutePostgresql> optRoute = routePostgresqlRepository.findByCharge(dto.getRoute());
            if (!optRoute.isPresent()) {
                throw new ResponseStatusException("ERRO: A rota indicada não existe. Verifique os dados.");
            }
            RoutePostgresql route = optRoute.get();
            entity.setRoute(route);
            entity.setDriver(route.getDriver());
            entity.setVehicle(route.getVehicle());
        } else {
            if (dto.getVehicle() == null
                    || vehiclePostgresqlRepository.findByDescription(dto.getVehicle()) == null) {
                throw new ResponseStatusException("ERRO: O veículo está nulo ou não existe. Verifique os dados.");
            }
            Optional<VehiclePostgresql> vehicle = vehiclePostgresqlRepository.findByDescription(dto.getVehicle());
            entity.setVehicle(vehicle.get());
            if (dto.getDriver() != null) {
                Optional<DriverPostgresql> driver = driverPostgresqlRepository.findByName(dto.getDriver());
                if (!driver.isPresent()) {
                    throw new ResponseStatusException("ERRO: O motorista indicado não existe. Verifique os dados.");
                }
                entity.setDriver(driver.get());
            }
        }
        entity.setFuelDate(dto.getFuelDate());
        entity.setOdômetro(dto.getOdômetro());
        entity.setLiters(dto.getLiters());
        entity.setTotalCost(dto.getTotalCost());
        if (dto.getLiters() != null && dto.getLiters() > 0) {
            entity.setPricePerLiter(dto.getTotalCost() / dto.getLiters());
        }
        if (dto.getGasStation() != null) {
            Optional<EstablishmentsPostgresql> optEst = establishmentsPostgresqlRepository
                    .findByName(dto.getGasStation());
            if (!optEst.isPresent()) {
                throw new ResponseStatusException("ERRO: Estabelecimento não encontrado: " + dto.getGasStation());
            }
            entity.setEstablishments(optEst.get());
        }

        entity.setObservations(dto.getObservations());
    }
}
