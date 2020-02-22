package com.bitsun.provider.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Document(indexName = "mymayikt",type = "user")
public class UserEntity {

    @Id
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实名称
     */
    private String realname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     *  邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Date birthday;
}
