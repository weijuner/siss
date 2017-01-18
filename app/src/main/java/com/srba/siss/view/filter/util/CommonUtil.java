package com.srba.siss.view.filter.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  常用工具类
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class CommonUtil {
    /**
     * 列表是否为空
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 列表不空
     * @param list
     * @return
     */
    public static boolean notEmpty(List list) {
        return !isEmpty(list);
    }

    /**
     * 列表是否全为空
     * @param lists
     * @return
     */
    public static boolean isAllEmpty(List... lists) {
        List<Boolean> booleans = new ArrayList<>();

        for (List list : lists) {
            if (isEmpty(list)) {
                booleans.add(Boolean.TRUE);
            }
        }

        return notEmpty(booleans) && booleans.contains(Boolean.FALSE);
    }

    /**
     * 列表只有一个为空
     * @param lists
     * @return
     */
    public static boolean isOneEmpty(List... lists) {
        for (List list : lists) {
            if (isEmpty(list)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 字符串是否为空
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || TextUtils.isEmpty(s);
    }
    /**
     * 字符串是否为不空
     * @param s
     * @return
     */
    public static boolean notEmpty(String s) {
        return s != null && !TextUtils.isEmpty(s.trim());
    }

    /**
     * 字符串数组是否为空
     * @param strings
     * @return
     */
    public static boolean isAllEmpty(String... strings) {
        List<Boolean> booleans = new ArrayList<>();

        for (String s : strings) {
            booleans.add(CommonUtil.isEmpty(s));
        }

        return notEmpty(booleans) && booleans.contains(Boolean.FALSE);
    }

    /**
     * 字符串数组只有一个为空
     * @param strings
     * @return
     */
    public static boolean isOneEmpty(String... strings) {
        for (String s : strings) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    public static long mLastClickTime;

    /**
     * 是否快速双击
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        mLastClickTime = time;
        return false;
    }
}
