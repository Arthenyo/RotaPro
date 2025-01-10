package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.dto.RoleDTO;
import com.arthenyo.rotapro_backend.dto.UserDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.RolePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.UserPostgresql;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.UserPostgresqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserPostgresqlRepository userPostgresqlRepository;

    public UserDTO createUser(UserDTO dto){
        UserPostgresql entity =new UserPostgresql();
        copyDtoToEntity(dto, entity);
        entity = userPostgresqlRepository.save(entity);
        return new UserDTO(entity);
    }

    private void copyDtoToEntity(UserDTO dto, UserPostgresql entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
        entity.getRoles().clear();
        for (RoleDTO roleDTO: dto.getRoles()){
            RolePostgresql role = new RolePostgresql();
            role.setId(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }
}
