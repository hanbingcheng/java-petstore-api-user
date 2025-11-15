package com.example.petstore.api.user.domain.service;

import com.example.petstore.api.user.domain.model.UserEntity;
import com.example.petstore.api.user.domain.repository.UserRepository;
import com.example.petstore.api.user.domain.service.dto.FindUserByEmailServiceInput;
import com.example.petstore.api.user.domain.service.dto.FindUserByEmailServiceOutput;
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
public class FindUserByEmailService {

  private final AppLogger logger;
  private final UserRepository userRepository;

  @StartEndLog
  public FindUserByEmailServiceOutput execute(FindUserByEmailServiceInput input) {

    UserEntity user;
    try {
      user = userRepository.findUserByEmail(input.getEmail());
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "UserRepository", "findUserByEmail");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    return FindUserByEmailServiceOutput.builder().user(user).build();
  }
}
