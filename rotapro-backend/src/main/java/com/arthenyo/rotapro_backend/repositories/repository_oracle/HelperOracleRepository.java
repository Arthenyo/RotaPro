package com.arthenyo.rotapro_backend.repositories.repository_oracle;

import com.arthenyo.rotapro_backend.model.model_oracle.HelperOracle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HelperOracleRepository extends JpaRepository<HelperOracle, Integer> {
    @Query(
            nativeQuery = true,
            value = "SELECT MATRICULA, NOME, CODSETOR, EMAIL, FONE FROM pcempr WHERE codsetor = :codsetor AND codfilial = :codfilial"
    )
    List<HelperOracle> getAllHelper(@Param("codsetor") Integer codsetor,
                                    @Param("codfilial") Integer codfilial);

}
