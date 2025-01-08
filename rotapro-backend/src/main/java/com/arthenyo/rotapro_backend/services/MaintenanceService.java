package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.dto.MaintenanceDTO;
import com.arthenyo.rotapro_backend.dto.MaintenanceMinDTO;
import com.arthenyo.rotapro_backend.dto.PartCostDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.MaintenancePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.PartCostPostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.VehiclePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.enums.StatusMaintenance;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.MaintenancePostgresqlRepository;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.VehiclePostgresqlRepository;
import com.arthenyo.rotapro_backend.services.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MaintenanceService {
    @Autowired
    private MaintenancePostgresqlRepository maintenanceRepository;
    @Autowired
    private VehiclePostgresqlRepository vehiclePostgresqlRepository;
    public Page<MaintenanceMinDTO> findAll(Pageable pageable){
        Page<MaintenancePostgresql> maintenancePage = maintenanceRepository.findAll(pageable);
        return maintenancePage.map(MaintenanceMinDTO::new);
    }

    public MaintenanceMinDTO findById(Long id){
        MaintenancePostgresql maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("ERRO: Manutenção não encontrado" + id));
        return new MaintenanceMinDTO(maintenance);
    }
    public Page<MaintenanceMinDTO> findByVehicleIdMaintenances(Integer vehicle, Pageable pageable){
        VehiclePostgresql manintenanceVehicle = vehiclePostgresqlRepository.findByCodVehicle(vehicle)
                .orElseThrow(() -> new ObjectNotFound("ERRO: Veiculo não encontrado, Por favor verifique o cadigo do veiculo"));
        Page<MaintenancePostgresql> maintenancePage = maintenanceRepository.findByVehicle(manintenanceVehicle, pageable);
        return maintenancePage.map(MaintenanceMinDTO::new);
    }
    public Page<MaintenanceMinDTO> findByNextMaintenanceDate(Pageable pageable) {
        LocalDate hoje = LocalDate.now();
        Page<MaintenancePostgresql> pageResult = maintenanceRepository.findAllWithNextMaintenanceDateAfter(hoje, pageable);
        return pageResult.map(MaintenanceMinDTO::new);
    }
    public Page<MaintenanceMinDTO> findByStatusMaintenances(StatusMaintenance status, Pageable pageable) {
        Page<MaintenancePostgresql> resultPage = maintenanceRepository.findAllByStatus(status, pageable);
        return resultPage.map(MaintenanceMinDTO::new);
    }
    public Page<MaintenanceMinDTO> findByCostAboveMaintenances(Double cost, Pageable pageable) {
        Page<MaintenancePostgresql> pageResult = maintenanceRepository.findAllByTotalCostGreaterThan(cost, pageable);
        return pageResult.map(MaintenanceMinDTO::new);
    }
    public Page<MaintenanceMinDTO> findByDateRangeMaintenances(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Page<MaintenancePostgresql> pageResult = maintenanceRepository.findAllByMaintenanceDateBetween(startDate, endDate, pageable);
        return pageResult.map(MaintenanceMinDTO::new);
    }

    public MaintenanceDTO createMaintenance(MaintenanceDTO dto){
        MaintenancePostgresql entity = new MaintenancePostgresql();
        copyToEntity(entity,dto);
        entity = maintenanceRepository.save(entity);
        return new MaintenanceDTO(entity);
    }

    private void copyToEntity(MaintenancePostgresql entity, MaintenanceDTO dto) {
        Optional<VehiclePostgresql> vehicle = vehiclePostgresqlRepository.findByDescription(dto.getVehicle());
        if(!vehicle.isPresent()){
            throw new ObjectNotFound("Veiculo " + dto.getVehicle() + "não foi encontrado, por favor verifique o veiculo");
        }
        entity.setVehicle(vehicle.get());
        entity.setMaintenanceDate(dto.getMaintenanceDate());
        entity.setMaintenanceType(dto.getMaintenanceType());
        entity.setDescription(dto.getDescription());
        entity.setServiceProvider(dto.getServiceProvider());
        entity.setResponsiblePerson(dto.getResponsiblePerson());
        entity.setStatus(StatusMaintenance.PENDENTE);
        entity.setNextMaintenanceDate(dto.getNextMaintenanceDate());
        entity.setOdometerReading(dto.getOdometerReading());
        entity.setFuelLevel(dto.getFuelLevel());
        entity.setInvoiceNumber(dto.getInvoiceNumber());
        entity.setMaintenanceReport(dto.getMaintenanceReport());
        entity.setApproved(dto.getApproved());
        entity.setApprovalComments(dto.getApprovalComments());
        double totalCost = 0.0;
        for (PartCostDTO partCostDTO : dto.getPartCosts()) {
            PartCostPostgresql partCostPostgresql = new PartCostPostgresql();
            partCostPostgresql.setId(partCostDTO.getId());
            partCostPostgresql.setPartName(partCostDTO.getPartName());
            partCostPostgresql.setCost(partCostDTO.getCost());
            partCostPostgresql.setMaintenance(entity);
            totalCost += partCostDTO.getCost();
            entity.getPartCosts().add(partCostPostgresql);
        }
        entity.setTotalCost(totalCost);
    }
}
