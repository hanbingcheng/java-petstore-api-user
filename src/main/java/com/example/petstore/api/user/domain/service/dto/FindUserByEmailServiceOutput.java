package com.example.petstore.api.user.domain.service.dto;

import com.example.petstore.api.user.domain.model.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindUserByEmailServiceOutput {
  private UserEntity user;
}
