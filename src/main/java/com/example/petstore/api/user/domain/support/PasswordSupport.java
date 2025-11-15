package com.example.petstore.api.user.domain.support;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordSupport {

  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  // パスワードをハッシュ化
  public String encode(String plainPassword) {
    return passwordEncoder.encode(plainPassword);
  }

  // パスワードの検証
  public boolean verifyPassword(String plainPassword, String encodedPassword) {
    return passwordEncoder.matches(plainPassword, encodedPassword);
  }
}
