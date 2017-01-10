package com.srba.siss.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2017/1/10
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2017/1/10         zwj               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class CustomRecyclerview extends RecyclerView{
    public CustomRecyclerview(Context context) {
        super(context);
    }

    public CustomRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
