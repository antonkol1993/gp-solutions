package com.hotel.service;

import com.hotel.dao.repository.UserRepository;
import com.hotel.model.entity.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Hotel save(Hotel user) {
        return userRepository.save(user);
    }

    @Override
    public List<Hotel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Hotel findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
