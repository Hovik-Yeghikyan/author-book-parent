package com.vector.authorbookcommon.mapper;

import com.vector.authorbookcommon.dto.SaveUserRequest;
import com.vector.authorbookcommon.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SaveUserRequest saveUserRequest);

}
