package com.arthenyo.rotapro_backend.repositories.repository_oracle;

import com.arthenyo.rotapro_backend.model.model_oracle.VehicleOracle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleOracleRepository extends JpaRepository<VehicleOracle,Integer> {
    @Query(nativeQuery = true, value = "SELECT CODVEICULO, PLACA, CHASSI, MARCA, DESCRICAO, TIPORODADO, PESOCARGAKG, QTEIXOS, QTRODAS, QTLITROS, COR, COMBUSTIVEL, NOMEPROPRIETARIO FROM pcveicul WHERE codfilial = :codfilial")
    List<VehicleOracle> getAllVehicles(@Param("codfilial") Integer codfilial);
}
