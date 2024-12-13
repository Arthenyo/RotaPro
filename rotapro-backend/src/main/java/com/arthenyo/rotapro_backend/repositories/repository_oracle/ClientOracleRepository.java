package com.arthenyo.rotapro_backend.repositories.repository_oracle;

import com.arthenyo.rotapro_backend.model.model_oracle.ClientOracle;
import com.arthenyo.rotapro_backend.model.model_oracle.DriverOracle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientOracleRepository extends JpaRepository<ClientOracle, Integer> {

    @Query(nativeQuery = true, value = "SELECT CODCLI, CLIENTE, ENDERENT, LATITUDE, LONGITUDE FROM pcclient")
    List<ClientOracle> getAllClient();
}
