package com.bitsun.provider.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@ApiModel(value = "登陆dto")
public class LoginReqDto implements Serializable {

    @ApiModelProperty(value = "用户名",example = "admin")
    private String username;

    @ApiModelProperty(value = "密码",example = "admin")
    private String password;

}
