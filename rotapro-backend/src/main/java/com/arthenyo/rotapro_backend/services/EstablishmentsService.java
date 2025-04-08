package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.dto.EstablishmentsDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.EstablishmentsPostgresql;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.EstablishmentsPostgresqlRepository;
import com.arthenyo.rotapro_backend.services.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstablishmentsService {
    @Autowired
    private EstablishmentsPostgresqlRepository establishmentsPostgresqlRepository;

    public Page<EstablishmentsDTO> findAll(Pageable pageable){
        Page<EstablishmentsPostgresql> user = establishmentsPostgresqlRepository.findAll(pageable);
        return user.map(EstablishmentsDTO::new);
    }
    public EstablishmentsDTO findById(Long id){
        EstablishmentsPostgresql user = establishmentsPostgresqlRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Usuario nao encontrado"));
        return new EstablishmentsDTO(user);
    }
    public List<EstablishmentsDTO> findByName(String nome){
        List<EstablishmentsPostgresql> user = establishmentsPostgresqlRepository.findByNameStartingWith(nome);
        return user.stream().map(EstablishmentsDTO::new).collect(Collectors.toList());
    }

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
