package com.bitsun.provider.common.utils;

import java.util.HashMap;

/**
 * Created with IDEA
 * author:ShuQuanQiang
 * Date:2019/1/31
 * Time:10:21
 */
public class ChannelUtil {

    public static HashMap<Integer, String> map;

    static {
        map = new HashMap<>();
        map.put(1,"惠购");
        map.put(2,"POS");
        map.put(3,"直销");
        map.put(4,"批发");
    }
}
