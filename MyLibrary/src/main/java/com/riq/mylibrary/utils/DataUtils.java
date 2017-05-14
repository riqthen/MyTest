package com.riq.mylibrary.utils;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by 锐 on 2017/5/14.
 */

public class DataUtils {
    /**
     * TODO ArrayList去除重复
     *
     * @param list
     * @return
     */
    public static ArrayList removeSame(ArrayList list) {
        if (null == list) {
            return null;
        }
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
