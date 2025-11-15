package com.example.petstore.api.user.application.mapper;

import com.example.petstore.api.user.domain.service.dto.UpdateUserServiceInput;
import com.example.petstore.api.user.oas.model.UpdateUserRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateUserMapper {

  public UpdateUserServiceInput map(Long petId, UpdateUserRequestBody requestBody) {

    return UpdateUserServiceInput.builder()
        .id(petId)
        .username(requestBody.getUsername())
        .firstName(requestBody.getFirstName())
        .lastName(requestBody.getLastName())
        .phone(requestBody.getPhone())
        .build();
  }
}
