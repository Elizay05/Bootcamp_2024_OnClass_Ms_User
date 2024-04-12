package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.bcrypt;

import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IPasswordEncryptionPort;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordEncryptionAdapter implements IPasswordEncryptionPort {
    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
