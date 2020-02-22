package com.bitsun.provider.service;

import com.bitsun.provider.Entity.UserEntity;
import com.bitsun.provider.dto.request.UserReqDto;
import com.bitsun.provider.dto.response.UserResDto;

import java.util.List;

public interface UserService {

    public UserResDto saverUser(UserReqDto userReqDto);

    public UserResDto getUser(Long id);

    public List<UserResDto> getUserList(String ids);


}
