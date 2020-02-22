package com.bitsun.provider.convertor;

import com.bitsun.provider.Entity.UserEntity;
import com.bitsun.provider.dto.request.UserReqDto;
import com.bitsun.provider.dto.response.UserResDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserConvertor implements Convetor<UserReqDto, UserEntity, UserResDto> {

    @Override
    public abstract UserResDto entityToClient(UserEntity entityObject);

    @Override
    public abstract UserEntity clientToEntity(UserReqDto userReqDto);

    @Override
    public abstract List<UserResDto> entityListToClientList(List<UserEntity> userEntities) ;

    @Override
    public abstract List<UserEntity> clientListToEntityList(List<UserReqDto> userReqDtos);
}
