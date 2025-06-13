package com.hotel.controller;

import com.hotel.dto.UserDto;
import com.hotel.model.entity.Hotel;
import com.hotel.service.UserMapper;
import com.hotel.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        Hotel saved = userService.save(UserMapper.toEntity(dto));
        return UserMapper.toDto(saved);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return UserMapper.toDto(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
