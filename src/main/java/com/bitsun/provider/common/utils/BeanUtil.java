package com.bitsun.provider.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实体转换
 */
public class BeanUtil {


    /**
     * @param object 要强转的对象 , entityClass 强转后的类型
     * @return T
     * @descprition   对象类型强转
     */
    public static <T> T convertBean(Object object, Class<T> entityClass) {
        if(null == object) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(object), entityClass);
    }

    /**
     * @param mpaList 要强转的对象 , entityClass 强转后的类型
     * @return T
     * @descprition   对象类型强转
     */
    public static <T> List<T> convertBeanList(List<Map> mpaList,  Class<T> entityClass) {
        List<T> entityClassList=new ArrayList<>();
        if(CollectionUtils.isEmpty(mpaList)) {
            return entityClassList;
        }
        mpaList.forEach(o -> {
            entityClassList.add(convertBean(o,entityClass));
        });
        return entityClassList;
    }


    /**
     * @param object 要转话的对象
     * @return java.util.Map<?,?>
     * @descprition   对象转为map
     */
    public static Map<?, ?> objectToMap(Object object){
        return convertBean(object, Map.class);
    }

    /**
     * 将List<T>转换为List<Map>
     *
     * @param objList
     * @return
     */
    public static <T> List<Map> objectsToMaps(List<T> objList) {
        List<Map> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(objList)){
            objList.forEach(obj->{
                list.add(objectToMap(obj));
            });
        }
        return list;

    }
    /**
     * @param source 资源对象, target 目标对象, ignoreProperties 赋值new String[]{}
     * @return T  target对象
     * @descprition   对象转换
     */
    public static <T> T copy(Object source, Class<T> target, String...ignoreProperties){
        T targetInstance = null;
        try {
            targetInstance = target.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ArrayUtils.isEmpty(ignoreProperties)) {
            assert targetInstance != null;
            org.springframework.beans.BeanUtils.copyProperties(source, targetInstance);
        }else {
            assert targetInstance != null;
            org.springframework.beans.BeanUtils.copyProperties(source, targetInstance, ignoreProperties);
        }
        return targetInstance;

    }



    /**
     * @param list, target, ignoreProperties]
     * @return java.util.List<T>
     * @descprition   对象list转换
     */
    public static <T, E> List<T> copyList(List<E> list, Class<T> target, String...ignoreProperties){
        List<T> targetList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)) {
            return targetList;
        }
        for(E e : list) {
            targetList.add(copy(e, target, ignoreProperties));
        }
        return targetList;
    }
}
