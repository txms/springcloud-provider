package com.bitsun.provider.common.utils;

import java.util.HashMap;

public class MapUtil extends HashMap<String,Object> {

    @Override
    public MapUtil put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
