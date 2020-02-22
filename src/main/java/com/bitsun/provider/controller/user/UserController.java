package com.bitsun.provider.controller.user;

import com.bitsun.provider.Entity.UserEntity;
import com.bitsun.provider.dto.request.UserReqDto;
import com.bitsun.provider.dto.response.UserResDto;
import com.bitsun.provider.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "用户信息",description = "用户信息接口")
@RestController
@RequestMapping("/api/v1/provider/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "创建用户",notes = "")
    @PostMapping(value = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,0:成功，否则失败",response = UserResDto.class)})
    public UserResDto saverUser(UserReqDto userReqDto) {
        return userService.saverUser(userReqDto);
    }

    @ApiOperation(value = "根据id获取用户信息",notes = "")
    @GetMapping(value = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "query",dataType = "long")
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,0:成功，否则失败",response = UserResDto.class)})
    public UserResDto getUser(Long id){
            return userService.getUser(id);
    };

    @ApiOperation(value = "根据id列表获取用户信息",notes = "")
    @GetMapping(value = "/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "id列表",required = true,paramType = "query",dataType = "string")
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,0:成功，否则失败",response = UserResDto.class)})
    public List<UserResDto> getUserList(@RequestParam String ids){
            return userService.getUserList(ids);
    };

}
