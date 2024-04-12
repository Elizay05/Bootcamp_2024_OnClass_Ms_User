package com.example.bootcamp_2024_onclass_ms_user.domain.api.usecase;

import com.example.bootcamp_2024_onclass_ms_user.domain.exception.InvalidArgumentsEmailException;
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
        User user = new User(1L, "John", "Doe", "123456789", "555-5555", "john.doe@example.com", 1L, "password");

        when(passwordEncryptionPort.encryptPassword("password")).thenReturn("encryptedPassword");
        when(userPersistencePort.saveUser(user)).thenReturn(user);

        User savedUser = userUseCase.saveUser(user);

        assertEquals("encryptedPassword", savedUser.getPassword());
        verify(userPersistencePort, times(1)).saveUser(user);
    }

    @Test
    @DisplayName("When_SaveUserWith_InvalidEmail_Expect_Exception")
    void testSaveUserSuccess_With_InvalidEmail() {
        User user = new User(1L, "John", "Doe", "123456789", "555-5555", "invalidEmail", 1L, "password");

        assertThrows(InvalidArgumentsEmailException.class, () -> {
            userUseCase.saveUser(user);
        });
        verify(userPersistencePort, never()).saveUser(user);
    }
}
