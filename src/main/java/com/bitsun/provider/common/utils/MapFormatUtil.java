package com.bitsun.provider.common.utils;

import com.google.common.base.CaseFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFormatUtil {

    private final static Logger logger = LoggerFactory.getLogger(MapFormatUtil.class);


    /**
     * 将Map中的key由下划线转换为驼峰
     */
    public static Map formatHumpName(Map map) {

        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        Map newMap = new HashMap<>();
        map.forEach((k, v) -> newMap.put(toFormatCol(k.toString()), v));
        return newMap;
    }

    public static String toFormatCol(String colName) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, colName);
    }

    /**
     * 将List中map的key值命名方式格式化为驼峰
     */
    public static List<Map> formatHumpNameForList(List<Map> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Map> newList = new ArrayList<>();
        for (Map o : list) {
            newList.add(formatHumpName(o));
        }
        return newList;
    }
}
