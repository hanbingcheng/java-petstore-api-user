package com.example.petstore.api.user.application.mapper;

import com.example.petstore.api.user.domain.model.UserEntity;
import com.example.petstore.api.user.domain.service.dto.FindUserByIdServiceInput;
import com.example.petstore.api.user.domain.service.dto.FindUserByIdServiceOutput;
import com.example.petstore.api.user.oas.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindUserByIdMapper {

  public FindUserByIdServiceInput map(long userId) {
    return FindUserByIdServiceInput.builder().id(userId).build();
  }

  public User map(FindUserByIdServiceOutput output) {

    UserEntity u = output.getUser();
    User user = new User();
    user.setId(u.getId());
    user.setUsername(u.getUsername());
    user.setFirstName(u.getFirstName());
    user.setLastName(u.getLastName());
    user.setEmail(u.getEmail());
    user.setPhone(u.getPhone());

    return user;
  }
}
