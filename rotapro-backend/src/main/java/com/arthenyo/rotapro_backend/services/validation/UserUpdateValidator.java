package com.arthenyo.rotapro_backend.services.validation;


import com.arthenyo.rotapro_backend.customErro.FieldMessage;
import com.arthenyo.rotapro_backend.dto.UserUpdateDTO;
import com.arthenyo.rotapro_backend.model.model_postgresql.UserPostgresql;
import com.arthenyo.rotapro_backend.repositories.repository_postgresql.UserPostgresqlRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserPostgresqlRepository repository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        UUID userId = UUID.fromString(uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        // Teste de validação
        UserPostgresql user = repository.findByEmail(dto.getEmail());
        if (user != null && !userId.equals(user.getId())) {
            list.add(new FieldMessage("email", "Email ja existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
