package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.model.model_postgresql.EstablishmentsPostgresql;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.EstablishmentsPostgresqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstablishmentsService {
    @Autowired
    private EstablishmentsPostgresqlRepository establishmentsPostgresqlRepository;

    public String createEstablishments(EstablishmentsPostgresql establishments){
        EstablishmentsPostgresql entity = new EstablishmentsPostgresql();
        entity.setName(establishments.getName());
        entity.setAddress(establishments.getAddress());
        entity.setCity(establishments.getCity());
        entity.setState(establishments.getState());
        entity.setType(establishments.getType());
        establishmentsPostgresqlRepository.save(entity);
        return "Estabelecimento criado com sucesso!";
    }
}
