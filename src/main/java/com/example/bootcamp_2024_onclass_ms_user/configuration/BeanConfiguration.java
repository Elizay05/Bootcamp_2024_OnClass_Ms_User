package com.example.bootcamp_2024_onclass_ms_user.configuration;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.bootcamp_2024_onclass_ms_user.domain.api.IUserServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.api.usecase.UserUseCase;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IPasswordEncryptionPort;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRolRepository rolRepository;
    private final IPasswordEncryptionPort passwordEncryptionPort;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userEntityMapper, rolRepository);
    }
    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), passwordEncryptionPort);
    }
 }
