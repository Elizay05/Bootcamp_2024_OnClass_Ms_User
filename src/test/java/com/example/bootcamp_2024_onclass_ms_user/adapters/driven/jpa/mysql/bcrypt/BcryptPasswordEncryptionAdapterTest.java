package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.bcrypt;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.bcrypt.BcryptPasswordEncryptionAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BcryptPasswordEncryptionAdapterTest {

    private BcryptPasswordEncryptionAdapter bcryptPasswordEncryptionAdapter = new BcryptPasswordEncryptionAdapter();

    @Test
    @DisplayName("Should Encrypt Password")
    void testEncryptPassword() {
        String password = "testPassword";

        String encryptedPassword = bcryptPasswordEncryptionAdapter.encryptPassword(password);

        assertNotEquals(password, encryptedPassword);
    }
}
