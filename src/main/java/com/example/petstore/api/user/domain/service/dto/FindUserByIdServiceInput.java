package com.example.petstore.api.user.domain.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindUserByIdServiceInput {
  private long id;
}
