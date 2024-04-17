package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name field cannot be empty")
    private String name;

    @NotBlank(message = "Last name field cannot be empty")
    private String lastName;

    @NotBlank(message = "Identification document field cannot be empty")
    private String identificationDocument;

    @NotBlank(message = "Cellphone number field cannot be empty")
    private String cellphoneNumber;

    @NotBlank(message = "Email field cannot be empty")
    private String email;

    @NotBlank(message = "Password field cannot be empty")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private RolEntity rol;
}
