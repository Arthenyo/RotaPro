package com.arthenyo.rotapro_backend.repositories.repository_postgresql;

import com.arthenyo.rotapro_backend.model.model_postgresql.UserPostgresql;
import com.arthenyo.rotapro_backend.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPostgresqlRepository extends JpaRepository<UserPostgresql, Long> {
    boolean existsByEmail(String email);
    UserPostgresql findByEmail(String email);
    List<UserPostgresql> findByNameStartingWith(String name);
    @Query(nativeQuery = true, value = """
    SELECT tb_usuario.email AS username, tb_usuario.password AS password, tb_role.id AS roleId, tb_role.authority AS authority
    FROM tb_usuario
    INNER JOIN tb_user_role ON tb_usuario.id = tb_user_role.user_id
    INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
    WHERE tb_usuario.email = :email
    """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
