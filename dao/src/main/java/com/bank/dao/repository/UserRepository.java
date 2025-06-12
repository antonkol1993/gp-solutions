package com.bank.dao.repository;

import com.bank.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Можно добавить кастомные методы поиска, если потребуется
}
