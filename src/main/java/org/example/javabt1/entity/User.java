package org.example.javabt1.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.example.javabt1.enums.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String address;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate;

    @UpdateTimestamp
    private LocalDate updateDate;

}
