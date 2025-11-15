package com.example.petstore.api.user.application.mapper;

import com.example.petstore.api.user.domain.service.dto.CreateUserServiceInput;
import com.example.petstore.api.user.domain.service.dto.CreateUserServiceOutput;
import com.example.petstore.api.user.oas.model.CreateUserRequestBody;
import com.example.petstore.api.user.oas.model.CreateUserResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateUserMapper {

  public CreateUserServiceInput map(CreateUserRequestBody requestBody) {

    return CreateUserServiceInput.builder()
        .username(requestBody.getUsername())
        .firstName(requestBody.getFirstName())
        .lastName(requestBody.getLastName())
        .email(requestBody.getEmail())
        .password(requestBody.getPassword())
        .phone(requestBody.getPhone())
        .build();
  }

  public CreateUserResponseBody map(CreateUserServiceOutput output) {
    CreateUserResponseBody body = new CreateUserResponseBody();
    body.setId(output.getUserId());
    return body;
  }
}
