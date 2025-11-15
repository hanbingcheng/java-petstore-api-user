package com.example.petstore.api.user.domain.repository;

import com.example.petstore.api.user.domain.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

  UserEntity findUserByEmail(String email);

  UserEntity findUserById(long userId);

  void insert(UserEntity user);

  void update(UserEntity user);
}
