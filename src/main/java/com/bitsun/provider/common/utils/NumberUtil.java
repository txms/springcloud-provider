package com.bitsun.provider.common.utils;

import org.apache.commons.lang3.BooleanUtils;

import java.math.BigDecimal;

/**
 * NumberUtils工具类
 *
 * @author 540565856@qq.com
 * @since 20180817
 */
public class NumberUtil extends org.apache.commons.lang3.math.NumberUtils {

    /**
     * 整形相加
     * @param a
     * @param b
     * @return
     */
    public static Integer add(Integer a,Integer b) {
        if(a==null) {
            a=0;
        }
        if(b==null) {
            b=0;
        }
        return a+b;
    }
    
    /**
     * 长整形相加
     * @param a
     * @param b
     * @return
     */
    public static Long add(Long a,Long b) {
        if(a==null) {
            a=0L;
        }
        if(b==null) {
            b=0L;
        }
        return a+b;
    }
    /***
     * 转换为int对象
     * @param obj
     * @return
     */
    public static Integer toInt(final Object obj) {
        return toInteger(obj, 0);
    }

    /***
     * 转换为int对象
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int toInt(final Object obj, final int defaultValue) {
        return toInteger(obj, defaultValue);
    }

    /***
     * 转换为Integer对象
     * @param obj
     * @return
     */
    public static int toInteger(final Object obj) {
        return toInteger(obj, null);
    }

    /***
     * 转换为Integer对象
     * @param obj
     * @param defaultValue
     * @return
     */
    public static Integer toInteger(final Object obj, final Integer defaultValue) {
        Integer value = null;
        if (obj == null) {
            value = defaultValue;
        } else if (obj instanceof Integer) {
            value = (Integer)obj;
        } else if (obj instanceof Number) {
            Number num = (Number)obj;
            value = num.intValue();
        } else if (obj instanceof Boolean) {
            Boolean num = (Boolean)obj;
            value= BooleanUtils.toIntegerObject(num);
        } else {
            try {
                value = Integer.parseInt(obj.toString());
            } catch (final NumberFormatException nfe) {
                value = defaultValue;
            }
        }
        return value;
    }



    /***
     * 转换为Short对象
     * @param obj
     * @return
     */
    public static Short toShort(final Object obj) {
        return toShort(obj, SHORT_ZERO);
    }

    /***
     * 转换为 Short对象
     * @param obj
     * @param defaultValue
     * @return
     */
    public static Short toShort(final Object obj, final short defaultValue) {
        return toShort(obj, defaultValue);
    }

    /***
     * 转换为Short对象
     * @param obj
     * @param defaultValue
     * @return
     */
    public static Short toShort(final Object obj, final Short defaultValue) {
        Short value = null;
        if (obj == null) {
            value = defaultValue;
        } else if (obj instanceof Short) {
            value = (Short)obj;
        } else if (obj instanceof Number) {
            Number num = (Number)obj;
            value = num.shortValue();
        } else if (obj instanceof Boolean) {
            Boolean num = (Boolean)obj;
            Integer inum= BooleanUtils.toIntegerObject(num);
            value = inum.shortValue();
        } else {
            try {
                value = Short.parseShort(obj.toString());
            } catch (final NumberFormatException nfe) {
                value = defaultValue;
            }
        }
        return value;
    }


    /***
     * 转换为 Long 对象
     * @param str
     * @return
     */
    public static Long toLongObj(final String str) {
        return toLong((Object)str, null);
    }

    /***
     * 转换为 Long 对象
     * @param str
     * @param defaultValue
     * @return
     */
    public static Long toLongObj(final String str, final long defaultValue) {
        return toLong((Object)str, defaultValue);
    }

    /***
     * 转换为 Long 对象
     * @param str
     * @param defaultValue
     * @return
     */
    public static Long toLong(final String str, final Long defaultValue) {
        return toLong((Object)str, defaultValue);
    }

    /***
     * 转换为 Long 对象
     * @param obj
     * @param defaultValue
     * @return
     */
    public static Long toLong(final Object obj, final long defaultValue) {
        return toLong(obj, (Long)defaultValue);
    }

    /***
     * 转换为 Long 对象
     * @param obj
     * @return
     */
    public static Long toLong(final Object obj) {
        return toLong(obj, null);
    }

    /***
     * 转换为 Long 对象
     * @param obj
     * @param defaultValue
     * @return
     */
    public static Long toLong(final Object obj, final Long defaultValue) {
        Long value = null;
        if (obj == null) {
            value = defaultValue;
        } else if (obj instanceof Long) {
            value = (Long)obj;
        } else if (obj instanceof Number) {
            Number num = (Number)obj;
            value = num.longValue();
        } else if (obj instanceof Boolean) {
            Boolean num = (Boolean)obj;
            Integer inum= BooleanUtils.toIntegerObject(num);
            value = inum.longValue();
        } else {
            try {
                value = Long.parseLong(obj.toString());
            } catch (final NumberFormatException nfe) {
                value = defaultValue;
            }
        }
        return value;
    }


    public static boolean isNullOrZero(BigDecimal value) {
        if(value == null)
            return true;
        
        return BigDecimal.ZERO.compareTo(value) == 0;
    }

    /***
     * 转换为 BigDecimal 对象
     * @param obj
     * @return
     */
    public static BigDecimal toBigDecimal(final Object obj) {
        return toBigDecimal(obj,null);
    }
    /***
     * 转换为 BigDecimal 对象
     * @param obj
     * @param defaultValue
     * @return
     */
    public static BigDecimal toBigDecimal(final Object obj, final BigDecimal defaultValue) {
        BigDecimal value = null;
        if (obj == null) {
            value = defaultValue;
        } else if (obj instanceof BigDecimal) {
            value = (BigDecimal)obj;
        } else if (obj instanceof Boolean) {
            Boolean num = (Boolean)obj;
            Integer val= BooleanUtils.toIntegerObject(num);
            value=new BigDecimal(""+val);
        } else {
            try {
                value=new BigDecimal( obj.toString() );
            } catch (final NumberFormatException nfe) {
                value = defaultValue;
            }
        }
        return value;
    }

    /***
     *  value 跟 0 比较
     * @param value
     * @return
     */
    public static int compareToZero(BigDecimal value) {
        if(value == null)
            return -1;

        return value.compareTo(BigDecimal.ZERO) ;
    }

    /**
     * 为Null转换为值为0的对象
     * @param value
     * @return
     */
    public static Integer nullToZero(Integer value) {
        if(value==null) {
            value=0;
        }
        return value;
    }
    
    /**
     * 为Null转换为值为0的对象
     * @param value
     * @return
     */
    public static Long nullToZero(Long value) {
        if(value==null) {
            value=0L;
        }
        return value;
    }
    
    /**
     * 为Null转换为值为0的对象
     * @param value
     * @return
     */
    public static BigDecimal nullToZero(BigDecimal value) {
        if(value==null) {
            value=new BigDecimal("0");
        }
        return value;
    }
    
    
    /**
     * 为Null时返回默认值
     * @param value
     * @param defVal
     * @return
     */
    public static Integer defaultIfNull(Integer value,Integer defVal) {
        if(value==null) {
            return defVal;
        }
        return value;
    }
    
    /**
     * 为Null时返回默认值
     * @param value
     * @param defVal
     * @return
     */
    public static Long defaultIfNull(Long value,Long defVal) {
        if(value==null) {
            return defVal;
        }
        return value;
    }
    
    /**
     * 为Null时返回默认值
     * @param value
     * @param defVal
     * @return
     */
    public static BigDecimal defaultIfNull(BigDecimal value,BigDecimal defVal) {
        if(value==null) {
            return defVal;
        }
        return value;
    }
    
    
    /**
     *  value1 跟 value2 比较 <br/>
     *  value1 == null && value2==null  return 0 <br/>
     *  value1 == null && value2!=null  return -1<br/>
     *  value1 != null && value2==null  return 1<br/>
     *   其它情况：
     *  return value1.compareTo(value2) <br/>
     * @param value1
     * @param value2
     * @return
     */
    public static int compare(BigDecimal value1,BigDecimal value2) {
        if(value1 == null && value2==null) {
            return 0;
        }
        if(value1 == null && value2!=null) {
            return -1;
        }
        if(value1 != null && value2==null) {
            return 1;
        }
        return value1.compareTo(value2) ;
    }

    /**
     *  value1 跟 value2 比较 <br/>
     *  value1 == null && value2==null  return 0 <br/>
     *  value1 == null && value2!=null  return -1<br/>
     *  value1 != null && value2==null  return 1<br/>
     *   其它情况：
     *  return value1.compareTo(value2) <br/>
     * @param value1
     * @param value2
     * @return
     */
    public static int compare(Comparable value1,Comparable value2) {
        if(value1 == null && value2==null) {
            return 0;
        }
        if(value1 == null && value2!=null) {
            return -1;
        }
        if(value1 != null && value2==null) {
            return 1;
        }
        return value1.compareTo(value2) ;
    }

    /**
     *返回一个值，该值指示两个给定向量中的任意元素对是否相等
     * @param i
     * @param searchIntegers
     * @return
     */
    public static boolean equalsAny(final Integer i, final Integer... searchIntegers) {
        if ( i!=null && searchIntegers!=null) {
            for (final Integer next : searchIntegers) {
                if (i.equals(next)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 空值判断
     * @param cs
     * @return
     */
    public static boolean isEmpty(final Integer cs) {
        return cs == null ;
    }

}
