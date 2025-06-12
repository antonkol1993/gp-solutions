// UserService.java
package com.bank.service;


import com.bank.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity save(UserEntity user);
    List<UserEntity> findAll();
    UserEntity findById(Long id);
    void deleteById(Long id);
}
