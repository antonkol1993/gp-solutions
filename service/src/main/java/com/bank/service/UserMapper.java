package com.bank.service;

import com.bank.dto.UserDto;
import com.bank.model.entity.UserEntity;

public class UserMapper {
    public static UserDto toDto(UserEntity entity) {
        return new UserDto(entity.getId(), entity.getUsername(), entity.getEmail());
    }

    public static UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.id());
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        return entity;
    }
}
