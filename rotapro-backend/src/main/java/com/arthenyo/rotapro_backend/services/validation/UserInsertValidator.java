package com.arthenyo.rotapro_backend.services.validation;


import com.arthenyo.rotapro_backend.customErro.FieldMessage;
import com.arthenyo.rotapro_backend.dto.UserInsertDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.UserPostgresql;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.UserPostgresqlRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserPostgresqlRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        UserPostgresql user = repository.findByEmail(dto.getEmail());
        if(user != null){
            list.add(new FieldMessage("email", "Email ja existe"));
        }
        for(FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
