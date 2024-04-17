package com.example.bootcamp_2024_onclass_ms_user.domain.api.usecase;

import com.example.bootcamp_2024_onclass_ms_user.domain.exception.InvalidArgumentsEmailException;
import com.example.bootcamp_2024_onclass_ms_user.domain.exception.InvalidRoleException;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IPasswordEncryptionPort;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IPasswordEncryptionPort passwordEncryptionPort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When_SaveUserWith_PasswordEncryption_Expect_Success")
    void testSaveUserSuccess_With_PasswordEncryption() {
        User userAdmin = new User(1L, "John", "Doe", "123456789", "555-5555", "john.doe@example.com", 1L, "password");
        User userTutor = new User(1L, "John", "Doe", "123456789", "555-5555", "john.doe@example.com", 2L, "password");
        User userStudent = new User(1L, "John", "Doe", "123456789", "555-5555", "john.doe@example.com", 3L, "password");


        when(passwordEncryptionPort.encryptPassword("password")).thenReturn("encryptedPassword");
        when(userPersistencePort.saveUser(userAdmin)).thenReturn(userAdmin);
        when(userPersistencePort.saveUser(userTutor)).thenReturn(userTutor);
        when(userPersistencePort.saveUser(userStudent)).thenReturn(userStudent);

        User savedUserAdmin = userUseCase.saveAdmin(userAdmin);
        User savedUserTutor = userUseCase.saveTutor(userTutor);
        User savedUserStudent = userUseCase.saveStudent(userStudent);

        assertEquals("encryptedPassword", savedUserAdmin.getPassword());
        assertEquals("encryptedPassword", savedUserTutor.getPassword());
        assertEquals("encryptedPassword", savedUserStudent.getPassword());
        verify(userPersistencePort, times(1)).saveUser(userAdmin);
        verify(userPersistencePort, times(1)).saveUser(userTutor);
        verify(userPersistencePort, times(1)).saveUser(userStudent);
    }

    @Test
    @DisplayName("When_SaveUserWith_InvalidEmail_Expect_Exception")
    void testSaveUserSuccess_With_InvalidEmail() {
        User user = new User(1L, "John", "Doe", "123456789", "555-5555", "invalidEmail", 1L, "password");

        assertThrows(InvalidArgumentsEmailException.class, () -> {
            userUseCase.saveAdmin(user);
        });
        assertThrows(InvalidArgumentsEmailException.class, () -> {
            userUseCase.saveTutor(user);
        });
        assertThrows(InvalidArgumentsEmailException.class, () -> {
            userUseCase.saveStudent(user);
        });

        verify(userPersistencePort, never()).saveUser(user);
    }

    @Test
    @DisplayName("When_SaveUserWith_InvalidRol_Expect_Exception")
    void testSaveUserSuccess_With_InvalidRol() {
        User userAdmin = new User(1L, "John", "Doe", "123456789", "555-5555", "john.doe@example.com", 2L, "password");
        User userTutor = new User(1L, "John", "Doe", "123456789", "555-5555", "john.doe@example.com", 3L, "password");
        User userStudent = new User(1L, "John", "Doe", "123456789", "555-5555", "john.doe@example.com", 1L, "password");

        assertThrows(InvalidRoleException.class, () -> {
            userUseCase.saveAdmin(userAdmin);
        });
        assertThrows(InvalidRoleException.class, () -> {
            userUseCase.saveTutor(userTutor);
        });
        assertThrows(InvalidRoleException.class, () -> {
            userUseCase.saveStudent(userStudent);
        });

        verify(userPersistencePort, never()).saveUser(userAdmin);
        verify(userPersistencePort, never()).saveUser(userTutor);
        verify(userPersistencePort, never()).saveUser(userStudent);
    }
}
