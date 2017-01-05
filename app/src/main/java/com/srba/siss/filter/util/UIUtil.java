package com.srba.siss.filter.util;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  ui工具类
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class UIUtil {
    /**
     * 将尺寸转换成dp
     * @param context
     * @param dp
     * @return
     */
    public static int dp(Context context, int dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics()) + 0.5F);
    }

    /**
     * 构造布局
     * @param context
     * @param layoutId
     * @param parent
     * @return  根据布局构造的view
     */
    public static View infalte(Context context, @LayoutRes int layoutId, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layoutId, parent, false);

    }


}
