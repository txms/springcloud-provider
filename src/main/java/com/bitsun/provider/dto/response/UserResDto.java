package com.bitsun.provider.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@ApiModel("用户对象返回dto")
@Validated
@Data
public class UserResDto {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("真实名称")
    private String realname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("生日")
    private Date birthday;

}
