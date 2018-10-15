package com.lee9213.mybatis.generator.util;

import java.util.Collection;
import java.util.Map;

/**
 * Collection工具类
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 23:32
 */
public class CollectionUtils {

    /**
     * <p>
     * 校验集合是否为空
     * </p>
     *
     * @param coll 入参
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> coll) {
        return (coll == null || coll.isEmpty());
    }

    /**
     * <p>
     * 校验集合是否不为空
     * </p>
     *
     * @param coll 入参
     * @return boolean
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断Map是否为空
     *
     * @param map 入参
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判断Map是否不为空
     *
     * @param map 入参
     * @return boolean
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}
