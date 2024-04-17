package com.example.bootcamp_2024_onclass_ms_user.configuration;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.bootcamp_2024_onclass_ms_user.configuration.security.jwt.JwtService;
import com.example.bootcamp_2024_onclass_ms_user.domain.api.IAuthenticationServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.api.IUserServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IAuthenticationPersistencePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IPasswordEncryptionPort;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeanConfigurationTest {

    @MockBean
    private IUserRepository userRepository;

    @MockBean
    private IUserEntityMapper userEntityMapper;

    @MockBean
    private IRolRepository rolRepository;

    @MockBean
    private IPasswordEncryptionPort passwordEncryptionPort;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    @DisplayName("When_BeanConfiguration_Expect_PortsToBeCreatedSuccessfully")
    void testBeanConfiguration() {
        BeanConfiguration beanConfiguration = new BeanConfiguration(
                userRepository, userEntityMapper, rolRepository, passwordEncryptionPort, jwtService, authenticationManager);

        IUserPersistencePort userPersistencePort = beanConfiguration.userPersistencePort();
        assertNotNull(userPersistencePort);

        IUserServicePort userServicePort = beanConfiguration.userServicePort();
        assertNotNull(userServicePort);

        IAuthenticationPersistencePort authenticationPersistencePort = beanConfiguration.authenticationPersistencePort();
        assertNotNull(authenticationPersistencePort);

        IAuthenticationServicePort authenticationServicePort = beanConfiguration.authenticationServicePort();
        assertNotNull(authenticationServicePort);
    }

}

