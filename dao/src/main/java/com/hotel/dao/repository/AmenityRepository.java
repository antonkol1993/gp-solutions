package com.hotel.dao.repository;

import com.hotel.model.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    // Можно добавить поиск по имени, например:
    Optional<Amenity> findByName(String name);
}