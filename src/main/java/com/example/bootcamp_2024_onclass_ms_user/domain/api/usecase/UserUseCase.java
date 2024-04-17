package com.example.bootcamp_2024_onclass_ms_user.domain.api.usecase;

import com.example.bootcamp_2024_onclass_ms_user.domain.api.IUserServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.exception.InvalidArgumentsEmailException;
import com.example.bootcamp_2024_onclass_ms_user.domain.exception.InvalidRoleException;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IPasswordEncryptionPort;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IUserPersistencePort;

import static com.example.bootcamp_2024_onclass_ms_user.domain.util.DomainConstants.*;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncryptionPort passwordEncryptionPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncryptionPort passwordEncryptionPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncryptionPort = passwordEncryptionPort;
    }

    @Override
    public User saveAdmin(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidArgumentsEmailException();
        }
        if (user.getRolId() == ID_ROL_TUTOR || user.getRolId() == ID_ROL_STUDENT) {
            throw new InvalidRoleException();
        }
        String encryptedPassword = passwordEncryptionPort.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        return userPersistencePort.saveUser(user);
    }

    @Override
    public User saveTutor(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidArgumentsEmailException();
        }
        if (user.getRolId() == ID_ROL_ADMINISTRATOR || user.getRolId() == ID_ROL_STUDENT) {
            throw new InvalidRoleException();
        }
        String encryptedPassword = passwordEncryptionPort.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        return userPersistencePort.saveUser(user);
    }

    @Override
    public User saveStudent(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidArgumentsEmailException();
        }
        if (user.getRolId() == ID_ROL_ADMINISTRATOR || user.getRolId() == ID_ROL_TUTOR) {
            throw new InvalidRoleException();
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
