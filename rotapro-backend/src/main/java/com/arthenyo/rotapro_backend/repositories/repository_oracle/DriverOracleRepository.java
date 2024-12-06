package com.arthenyo.rotapro_backend.repositories.repository_oracle;

import com.arthenyo.rotapro_backend.model.model_oracle.DriverOracle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverOracleRepository extends JpaRepository<DriverOracle,Integer> {

    @Query(nativeQuery = true, value = "SELECT MATRICULA, NOME, CNH, CATEGORIACNH, DTVALIDADECNH FROM pcempr WHERE tipo = 'M'")
    List<DriverOracle> getAllDriver();
}
