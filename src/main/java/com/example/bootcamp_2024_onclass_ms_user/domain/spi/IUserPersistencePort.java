package com.example.bootcamp_2024_onclass_ms_user.domain.spi;

import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;

public interface IUserPersistencePort {

    User saveUser(User user);
}
