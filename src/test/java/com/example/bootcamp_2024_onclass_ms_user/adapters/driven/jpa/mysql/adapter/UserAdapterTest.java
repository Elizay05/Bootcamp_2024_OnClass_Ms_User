package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.adapter;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.exception.IdentificationDocumentAlreadyExistsException;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserAdapterTest {
    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Mock
    private IRolRepository rolRepository;

    private UserAdapter userAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userAdapter = new UserAdapter(userRepository, userEntityMapper, rolRepository);
    }

    @Test
    @DisplayName("Save User - Success: Should save a new user")
    void testSaveUser_Success() {
        User user = new User(1L, "John", "Doe", "123456789", "12345678", "johndoe@uc.cl ", 1L, "12345678");
        RolEntity rolEntity = new RolEntity();
        rolEntity.setId(1L);
        rolEntity.setName("TestRole");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("John");
        userEntity.setLastName("Doe");
        userEntity.setIdentificationDocument("123456789");
        userEntity.setCellphoneNumber("123456789");
        userEntity.setEmail("johndoe@uc.cl ");
        userEntity.setPassword("12345678");
        userEntity.setRol(rolEntity);

        when(userRepository.findByIdentificationDocument("123456789")).thenReturn(new ArrayList<>());
        when(rolRepository.findById(1L)).thenReturn(Optional.of(rolEntity));
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityMapper.toModel(userEntity)).thenReturn(user);

        User savedUser = userAdapter.saveUser(user);

        assertNotNull(savedUser);
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getLastName(), savedUser.getLastName());
        assertEquals(user.getIdentificationDocument(), savedUser.getIdentificationDocument());
        assertEquals(user.getCellphoneNumber(), savedUser.getCellphoneNumber());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getRolId(), savedUser.getRolId());
        assertEquals(user.getRolName(), savedUser.getRolName());
    }


    @Test
    @DisplayName("Save User - Error: Should throw IdentificationDocumentAlreadyExistsException")
    void testSaveUserWithExistingIdentificationDocument() {
        User user = new User(1L, "John", "Doe", "123456789", "12345678", "johndoe@uc.cl ", 1L, "12345678");

        when(userRepository.findByIdentificationDocument("123456789")).thenReturn(Collections.singletonList(new UserEntity()));

        assertThrows(IdentificationDocumentAlreadyExistsException.class, () -> userAdapter.saveUser(user));
    }

    @Test
    @DisplayName("Save User - Error: Should throw ElementNotFoundException")
    void testSaveUserWithNonExistingRol() {
        User user = new User(1L, "John", "Doe", "123456789", "12345678", "johndoe@uc.cl ", 1L, "12345678");

        when(userRepository.findByIdentificationDocument("123456789")).thenReturn(Collections.emptyList());
        when(rolRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, () -> userAdapter.saveUser(user));
    }
}
