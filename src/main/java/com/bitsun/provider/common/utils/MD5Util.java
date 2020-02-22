package com.bitsun.provider.common.utils;

/**
 * @Description:
 * @author: xuanwei
 */


import org.springframework.util.DigestUtils;

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5DigestAsHex(src.getBytes());
    }

}


