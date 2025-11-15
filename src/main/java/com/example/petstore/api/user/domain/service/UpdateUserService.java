package com.example.petstore.api.user.domain.service;

import com.example.petstore.api.user.domain.model.UserEntity;
import com.example.petstore.api.user.domain.repository.UserRepository;
import com.example.petstore.api.user.domain.service.dto.UpdateUserServiceInput;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.core.base.exception.SystemException;
import com.example.petstore.common.core.base.logging.AppLogger;
import com.example.petstore.common.core.base.logging.annotation.StartEndLog;
import com.example.petstore.common.core.base.logging.constant.CommonLogId;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateUserService {

  private final AppLogger logger;
  private final UserRepository userRepository;

  @StartEndLog
  @Transactional
  public void execute(UpdateUserServiceInput input) {

    updateUser(input);
  }

  private void updateUser(UpdateUserServiceInput input) {
    UserEntity user =
        UserEntity.builder()
            .id(input.getId())
            .username(input.getUsername())
            .firstName(input.getFirstName())
            .lastName(input.getLastName())
            .phone(input.getPhone())
            .build();
    try {
      userRepository.update(user);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "UserRepository", "update");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
  }
}
