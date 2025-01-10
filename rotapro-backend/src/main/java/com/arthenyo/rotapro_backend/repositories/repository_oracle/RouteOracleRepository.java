package com.arthenyo.rotapro_backend.repositories.repository_oracle;

import com.arthenyo.rotapro_backend.model.model_oracle.RouteOracle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteOracleRepository extends JpaRepository<RouteOracle, Long> {
    @Query(value = "SELECT " +
            "m.nummdfe AS numMdfe, " +
            "LISTAGG(DISTINCT p.CODCLI, ',') WITHIN GROUP (ORDER BY p.CODCLI) AS codCli, " +
            "m.codmotorista AS codMotorista, " +
            "m.codveiculo AS codVeiculo, " +
            "n.NUMCAR AS numCar, " +
            "MAX(n.DTSAIDA) AS dtSaida, " +
            "c.totpeso / 1000.0 AS totalPeso, " +
            "m.situacaomdfe AS situacaoMdfe " +
            "FROM pcnfsaid n " +
            "JOIN pcpedc p ON n.NUMCAR = p.NUMCAR " +
            "JOIN pcmanifestoeletronicoi i ON n.NUMNOTA = i.numnota " +
            "JOIN pcmanifestoeletronicoc m ON i.nummdfe = m.nummdfe " +
            "JOIN pcveicul v ON m.codveiculo = v.codveiculo " +
            "JOIN pcempr e ON m.codmotorista = e.matricula AND e.tipo = 'M' " +
            "JOIN pccarreg c ON n.NUMCAR = c.NUMCAR " +
            "WHERE TRUNC(n.DTSAIDA) = TRUNC(SYSDATE)  " +
            "AND n.CODMOTORISTA IS NOT NULL " +
            "AND n.CODVEICULO IS NOT NULL " +
            "AND n.CODMOTORISTA != 0 " +
            "AND n.CODVEICULO != 0 " +
            "AND n.CODFILIAL = :codfilial " +
            "AND m.codfilial = :codfilial " +
            "GROUP BY m.nummdfe, m.codmotorista, e.nome, m.codveiculo, v.descricao, n.NUMCAR, c.totpeso, m.situacaomdfe " +
            "ORDER BY m.nummdfe",
            nativeQuery = true)
    List<RouteOracle> findAllRoutesDataForToday(@Param("codfilial") Integer codfilial);
}
