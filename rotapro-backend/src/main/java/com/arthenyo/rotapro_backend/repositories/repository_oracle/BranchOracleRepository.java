package com.arthenyo.rotapro_backend.repositories.repository_oracle;

import com.arthenyo.rotapro_backend.model.model_oracle.BranchOracle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchOracleRepository extends JpaRepository<BranchOracle, Integer> {
    @Query(nativeQuery = true, value = "SELECT CODIGO, RAZAOSOCIAL, CGC FROM pcfilial WHERE codigo = :codigo")
    List<BranchOracle> getAllBranch(@Param("codigo") Integer codigo);
}
