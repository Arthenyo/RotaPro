package com.arthenyo.rotapro_backend.services;

import com.arthenyo.rotapro_backend.dto.RoleDTO;
import com.arthenyo.rotapro_backend.dto.UserDTO;
import com.arthenyo.rotapro_backend.dto.UserInsertDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.RolePostgresql;
import com.arthenyo.rotapro_backend.model.model_postgresql.UserPostgresql;
import com.arthenyo.rotapro_backend.projections.UserDetailsProjection;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.UserPostgresqlRepository;
import com.arthenyo.rotapro_backend.services.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserPostgresqlRepository userPostgresqlRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserInsertDTO dto){
        UserPostgresql entity =new UserPostgresql();
        copyDtoToEntity(dto, entity);
        entity = userPostgresqlRepository.save(entity);
        return new UserDTO(entity);
    }
    public Page<UserDTO> findAll(Pageable pageable){
        Page<UserPostgresql> user = userPostgresqlRepository.findAll(pageable);
        return user.map(UserDTO::new);
    }
    public UserDTO findById(Long id){
        UserPostgresql user = userPostgresqlRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Usuario nao encontrado"));
        return new UserDTO(user);
    }
    public List<UserDTO> findByName(String nome){
        List<UserPostgresql> user = userPostgresqlRepository.findByNameStartingWith(nome);
        return user.stream().map(UserDTO::new).collect(Collectors.toList());
    }


    private void copyDtoToEntity(UserInsertDTO dto, UserPostgresql entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setPhone(dto.getPhone());
        entity.getRoles().clear();
        for (RoleDTO roleDTO: dto.getRoles()){
            RolePostgresql role = new RolePostgresql();
            role.setId(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = userPostgresqlRepository.searchUserAndRolesByEmail(username);
        if(result.size() == 0){
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }
        UserPostgresql user = new UserPostgresql();
        user.setEmail(username);
        user.setPassword(
                result.get(0).getPassword());
        for(UserDetailsProjection projection : result){
            user.addRole(new RolePostgresql(projection.getRoleId(),projection.getAuthority()));
        }
        return user;
    }
}
