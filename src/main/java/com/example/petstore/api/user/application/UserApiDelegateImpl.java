package com.example.petstore.api.user.application;

import com.example.petstore.api.user.application.mapper.CreateUserMapper;
import com.example.petstore.api.user.application.mapper.FindUserByIdMapper;
import com.example.petstore.api.user.domain.service.CreateUserService;
import com.example.petstore.api.user.domain.service.FindUserByIdService;
import com.example.petstore.api.user.domain.service.dto.CreateUserServiceInput;
import com.example.petstore.api.user.domain.service.dto.CreateUserServiceOutput;
import com.example.petstore.api.user.domain.service.dto.FindUserByIdServiceInput;
import com.example.petstore.api.user.domain.service.dto.FindUserByIdServiceOutput;
import com.example.petstore.api.user.oas.api.UserApiDelegate;
import com.example.petstore.api.user.oas.model.CreateUserRequestBody;
import com.example.petstore.api.user.oas.model.CreateUserResponseBody;
import com.example.petstore.api.user.oas.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserApiDelegateImpl implements UserApiDelegate {

  private final CreateUserMapper createUserMapper;
  private final CreateUserService createUserService;

  private final FindUserByIdMapper findUserByIdMapper;
  private final FindUserByIdService findUserByIdService;

  @Override
  public ResponseEntity<CreateUserResponseBody> createUser(
      CreateUserRequestBody createUserRequestBody) {
    CreateUserServiceInput input = createUserMapper.map(createUserRequestBody);
    CreateUserServiceOutput output = createUserService.execute(input);
    CreateUserResponseBody responseBody = createUserMapper.map(output);
    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<User> getUserById(Long xUserId) {
    FindUserByIdServiceInput input = findUserByIdMapper.map(xUserId);
    FindUserByIdServiceOutput output = findUserByIdService.execute(input);
    User user = findUserByIdMapper.map(output);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
