package com.hotel.dao.repository;

import com.hotel.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Hotel, Long> {
    // Можно добавить кастомные методы поиска, если потребуется
}
