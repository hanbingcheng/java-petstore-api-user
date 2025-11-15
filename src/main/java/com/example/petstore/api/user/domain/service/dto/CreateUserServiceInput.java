package com.example.petstore.api.user.domain.service.dto;

import com.example.petstore.common.core.base.logging.annotation.LogMask;
import com.example.petstore.common.core.base.logging.annotation.LogMaskBean;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@LogMaskBean
public class CreateUserServiceInput {
  private String username;
  private String firstName;
  private String lastName;
  private String email;

  @LogMask(kind = LogMask.Kind.SIMPLE, maskString = "*")
  private String password;

  private String phone;
}
