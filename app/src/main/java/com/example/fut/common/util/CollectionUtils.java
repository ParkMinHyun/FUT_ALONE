package com.example.fut.common.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static <T> T getItemByIndexSafety(List<T> items, int index) {
        if (isNotEmpty(items) && items.size() > index) {
            return items.get(index);
        }
        return null;
    }

    public static int getSizeSafety(Collection collection) {
        if (isEmpty(collection)) {
            return 0;
        }
        return collection.size();
    }
}
