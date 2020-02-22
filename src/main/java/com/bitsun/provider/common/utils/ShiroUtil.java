package com.bitsun.provider.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bitsun.provider.dto.response.UserResDto;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;


@Component
public class ShiroUtil {

    @Autowired
    JwtUtils jwtUtils;

    public Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }


    public UserResDto getUser() {
        String token = (String) getSubject().getPrincipal();
        LinkedHashMap claims=(LinkedHashMap)jwtUtils.getClaimByToken(token).get("user");
        String json= JSON.toJSONString(claims);
        UserResDto userResDto= JSONObject.parseObject(json,UserResDto.class);
        //SysUserPo user=SysUserAssembler.c(sysUserResDto);
        //claim中加入登录用户的详细信息
        return userResDto;

    }

    public  Long getUserId() {
        String principal = (String)getSubject().getPrincipal();
        Long id = null;
        if(StringUtils.isNoneBlank(principal)){
            Claims claims =jwtUtils.getClaimByToken(principal);
            id = NumberUtils.toLong(claims.getSubject());
        }
        return id;
    }

    public  String getUserName() {
        return getUser().getNickname();
    }

    public  void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public  Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public  boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

}
