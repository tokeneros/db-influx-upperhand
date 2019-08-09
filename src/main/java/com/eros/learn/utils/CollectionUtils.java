package com.eros.learn.utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: eros
 * @Description:
 * @Date: Created in 2019/8/8 16:15
 * @Version: 1.0
 * @Modified By:
 */
public class CollectionUtils {

    public static boolean isEmpty(List list){
        return Objects.isNull(list) || list.isEmpty();
    }

    public static boolean isNotEmpty(List list){
        return !isEmpty(list);
    }

    public static boolean isEmpty(Map tags){
        return Objects.isNull(tags) || tags.isEmpty();
    }

    public static boolean isNotEmpty(Map tags) {
        return !isEmpty(tags);
    }
}
