package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.adapter;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.exception.FieldAlreadyExistsException;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRolRepository rolRepository;

    @Override
    public User saveUser(User user) {
        List<UserEntity> usersIdentification = userRepository.findByIdentificationDocument(user.getIdentificationDocument());
        if (!usersIdentification.isEmpty()) {
            throw new FieldAlreadyExistsException("The identification document");
        }
        Optional<UserEntity> usersEmail = userRepository.findByEmail(user.getEmail());
        if (usersEmail.isPresent()) {
            throw new FieldAlreadyExistsException("The email");
        }

        RolEntity rolEntity = rolRepository.findById(user.getRolId())
                .orElseThrow(ElementNotFoundException::new);

        String rolName = rolEntity.getName();

        user.setRolName(rolName);

        UserEntity userEntity = userEntityMapper.toEntity(user);
        userEntity.setRol(rolEntity);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        return userEntityMapper.toModel(savedUserEntity);
    }

}
