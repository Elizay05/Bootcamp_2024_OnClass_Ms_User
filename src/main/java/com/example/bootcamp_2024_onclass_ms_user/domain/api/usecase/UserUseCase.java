package com.example.bootcamp_2024_onclass_ms_user.domain.api.usecase;

import com.example.bootcamp_2024_onclass_ms_user.domain.api.IUserServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.exception.InvalidArgumentsEmailException;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IPasswordEncryptionPort;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncryptionPort passwordEncryptionPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncryptionPort passwordEncryptionPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncryptionPort = passwordEncryptionPort;
    }

    @Override
    public User saveUser(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidArgumentsEmailException();
        }

        String encryptedPassword = passwordEncryptionPort.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        return userPersistencePort.saveUser(user);
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
        return email.matches(regex);
    }
}
