package com.bitsun.provider.service.impl;

import com.bitsun.provider.Entity.UserEntity;
import com.bitsun.provider.common.utils.NumberUtil;
import com.bitsun.provider.common.utils.RandomUtil;
import com.bitsun.provider.convertor.UserConvertor;
import com.bitsun.provider.dao.UserRepository;
import com.bitsun.provider.dto.request.UserReqDto;
import com.bitsun.provider.dto.response.UserResDto;
import com.bitsun.provider.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConvertor userConvertor;

    /**
     * 保存用户到数据库
     */
    @Override
    public UserResDto saverUser(UserReqDto userReqDto) {

        UserEntity userEntity= userConvertor.clientToEntity(userReqDto);

        userEntity.setId(NumberUtil.toLong(RandomUtil.generateDigitalString(6)));

        userEntity=userRepository.save(userEntity);

        UserResDto userResDto=userConvertor.entityToClient(userEntity);

        return userResDto;
    }

    /**
     *  根据用户id查询用户
     * @param id
     * @return
     */
    public UserResDto getUser(Long id){
        UserEntity userEntity=userRepository.findById(id).orElse(null);

        UserResDto userResDto=userConvertor.entityToClient(userEntity);

        return userResDto;
    }

    /**
     *  根据用户id列表查询用户列表
     * @param ids
     * @return
     */
    public List<UserResDto> getUserList(String ids){

        List<Long> idList= Arrays.asList(ids.split(",")).stream().map(s -> NumberUtils.createLong(s.trim()))
                .collect(Collectors.toList());

        Iterable<Long> iterable=new ArrayList<>();
        ((ArrayList<Long>) iterable).addAll(idList);
        Iterable<UserEntity> userEntityIterable=userRepository.findAllById(iterable);
        Iterator<UserEntity> userEntityIterator=userEntityIterable.iterator();

        List<UserEntity> userEntityList= Lists.newArrayList(userEntityIterator);

        List<UserResDto> userResDtoList=userConvertor.entityListToClientList(userEntityList);
        return userResDtoList;

    }


}
