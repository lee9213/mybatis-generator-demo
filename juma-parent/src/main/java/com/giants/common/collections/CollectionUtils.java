package com.giants.common.collections;

import java.util.Collection;

/**
 * @author libo
 * @date 2018/11/12 14:45
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }
}
