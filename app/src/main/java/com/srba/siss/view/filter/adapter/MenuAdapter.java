package com.srba.siss.view.filter.adapter;

import android.view.View;
import android.widget.FrameLayout;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  菜单适配器接口
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public interface MenuAdapter {

    /**
     * 设置筛选条目个数
     */
    int getMenuCount();

    /**
     * 设置每个筛选器默认Title
     */
    String getMenuTitle(int position);

    /**
     * 设置每个筛选条目距离底部距离
     */
    int getBottomMargin(int position);


    /**
     * 设置每个筛选条目的View
     */
    View getView(int position, FrameLayout parentContainer);
}
