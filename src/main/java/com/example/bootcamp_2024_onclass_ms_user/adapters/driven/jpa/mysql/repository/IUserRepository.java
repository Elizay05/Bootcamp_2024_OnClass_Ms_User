package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByIdentificationDocument(String identificationDocument);

    Optional<UserEntity> findByEmail(String email);
}
