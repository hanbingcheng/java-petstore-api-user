package com.example.petstore.api.user.domain.model;

import com.example.petstore.common.core.base.crypt.annotation.Encrypted;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  private Long id;
  private String username;
  @Encrypted private String firstName;
  @Encrypted private String lastName;
  private String email;
  private String password;
  @Encrypted private String phone;
  private String role;
  private int userStatus;
}
