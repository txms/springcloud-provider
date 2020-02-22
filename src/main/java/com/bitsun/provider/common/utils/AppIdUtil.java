package com.bitsun.provider.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 获取当前登录的用户信息
 * @author: xuanwei
 */
@Component
public class AppIdUtil {
    //@Value("${biztier.appid}")
    private String appId;

    public String getAppId() {
        return appId;
    }
}
