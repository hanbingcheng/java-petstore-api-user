package com.example.petstore.api.user.domain.service;

import com.example.petstore.api.user.domain.model.UserEntity;
import com.example.petstore.api.user.domain.repository.UserRepository;
import com.example.petstore.api.user.domain.service.dto.FindUserByIdServiceInput;
import com.example.petstore.api.user.domain.service.dto.FindUserByIdServiceOutput;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.core.base.exception.SystemException;
import com.example.petstore.common.core.base.logging.AppLogger;
import com.example.petstore.common.core.base.logging.annotation.StartEndLog;
import com.example.petstore.common.core.base.logging.constant.CommonLogId;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindUserByIdService {

  private final AppLogger logger;
  private final UserRepository userRepository;

  @StartEndLog
  public FindUserByIdServiceOutput execute(FindUserByIdServiceInput input) {

    UserEntity user;
    try {
      user = userRepository.findUserById(input.getId());
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "UserRepository", "findUserById");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    return FindUserByIdServiceOutput.builder().user(user).build();
  }
}
