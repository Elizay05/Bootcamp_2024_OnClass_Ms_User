package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.mapper;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {

    @Mapping(source = "rol.id", target = "rolId")
    @Mapping(source = "rol.name", target = "rolName")
    User toModel(UserEntity userEntity);

    UserEntity toEntity(User user);
}
