package com.example.petstore.api.user.domain.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserServiceInput {
  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private String phone;
}
