package com.example.petstore.api.user.domain.service;

import com.example.petstore.api.user.domain.constant.UserStatus;
import com.example.petstore.api.user.domain.model.UserEntity;
import com.example.petstore.api.user.domain.repository.UserRepository;
import com.example.petstore.api.user.domain.service.dto.CreateUserServiceInput;
import com.example.petstore.api.user.domain.service.dto.CreateUserServiceOutput;
import com.example.petstore.api.user.domain.support.PasswordSupport;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.api.errorhandler.exception.AppValidationException;
import com.example.petstore.common.core.base.exception.SystemException;
import com.example.petstore.common.core.base.logging.AppLogger;
import com.example.petstore.common.core.base.logging.annotation.StartEndLog;
import com.example.petstore.common.core.base.logging.constant.CommonLogId;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserService {

  private final AppLogger logger;
  private final UserRepository userRepository;
  private final PasswordSupport passwordSupport;

  @StartEndLog
  @Transactional
  public CreateUserServiceOutput execute(CreateUserServiceInput input) {

    if (checkEmailExists(input.getEmail())) {
      throw new AppValidationException(
          CommonErrorCode.REQUEST_PARAMETER_ERROR,
          "email",
          input.getEmail(),
          "email already registered.");
    }
    long id = insertUser(input);
    return CreateUserServiceOutput.builder().userId(id).build();
  }

  private boolean checkEmailExists(String email) {
    try {
      return ObjectUtils.isNotEmpty(userRepository.findUserByEmail(email));
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "UserRepository", "findUserByEmail");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
  }

  private long insertUser(CreateUserServiceInput input) {

    UserEntity user =
        UserEntity.builder()
            .username(input.getUsername())
            .firstName(input.getFirstName())
            .lastName(input.getLastName())
            .email(input.getEmail())
            .password(passwordSupport.encode(input.getPassword()))
            .phone(input.getPhone())
            .userStatus(UserStatus.ACTIVE.getCode())
            .build();
    try {
      userRepository.insert(user);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "UserRepository", "insert");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    return user.getId();
  }
}
