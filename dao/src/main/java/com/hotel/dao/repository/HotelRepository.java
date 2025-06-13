package com.hotel.dao.repository;

import com.hotel.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Репозиторий для Hotel
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // Можно добавить методы поиска, если надо
}