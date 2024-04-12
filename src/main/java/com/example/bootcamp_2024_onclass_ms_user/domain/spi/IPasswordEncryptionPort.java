package com.example.bootcamp_2024_onclass_ms_user.domain.spi;

public interface IPasswordEncryptionPort {
    String encryptPassword(String password);
}
