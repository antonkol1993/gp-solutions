package com.bank.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "testing", name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;
}
