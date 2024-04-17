package com.example.bootcamp_2024_onclass_ms_user.domain.api;

import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;

public interface IUserServicePort {

    User saveAdmin(User user);

    User saveTutor(User user);

    User saveStudent(User user);
}
