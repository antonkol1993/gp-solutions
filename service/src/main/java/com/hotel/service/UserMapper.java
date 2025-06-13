package com.hotel.service;

import com.hotel.dto.UserDto;
import com.hotel.model.entity.Hotel;

public class UserMapper {
    public static UserDto toDto(Hotel entity) {
        return new UserDto(entity.getId(), entity.getUsername(), entity.getEmail());
    }

    public static Hotel toEntity(UserDto dto) {
        Hotel entity = new Hotel();
        entity.setId(dto.id());
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        return entity;
    }
}
