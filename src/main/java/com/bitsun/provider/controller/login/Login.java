package com.bitsun.provider.controller.login;


import com.bitsun.provider.Entity.UserEntity;
import com.bitsun.provider.common.web.ResultDTO;
import com.bitsun.provider.dao.UserRepository;
import com.bitsun.provider.dto.request.UserReqDto;
import com.bitsun.provider.dto.request.LoginReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "用户登陆")
public class Login {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆")
    public ResultDTO login(@RequestBody LoginReqDto loginReqDto){
        if("admin".equals(loginReqDto.getUsername())&&"admin".equals(loginReqDto.getPassword())){
            UserReqDto userReqDto=new UserReqDto();
            userReqDto.setPassword("123");
            userReqDto.setUsername("admin");

            UserEntity userEntity=new UserEntity();
            userEntity.setId(20L);
            userEntity.setAge(20);
            //es操作
            userRepository.save(userEntity);

            return ResultDTO.ok(userReqDto);
        }
        return ResultDTO.error();
    }


    @PostMapping("/loginOut")
    @ApiOperation(value = "用户登出")
    public ResultDTO loginOut(@RequestBody LoginReqDto loginReqDto){

        return ResultDTO.ok();

    }
}
