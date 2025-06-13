// UserService.java
package com.hotel.service;


import com.hotel.model.entity.Hotel;

import java.util.List;

public interface UserService {
    Hotel save(Hotel user);
    List<Hotel> findAll();
    Hotel findById(Long id);
    void deleteById(Long id);
}
