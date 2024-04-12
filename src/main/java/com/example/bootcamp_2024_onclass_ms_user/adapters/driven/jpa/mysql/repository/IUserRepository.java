package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByIdentificationDocument(String identificationDocument);
}
