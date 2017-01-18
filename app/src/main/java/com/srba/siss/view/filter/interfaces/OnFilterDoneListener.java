package com.srba.siss.view.filter.interfaces;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  筛选菜单处理完成接口
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public interface OnFilterDoneListener {
    /**
     * 过滤器处理完成
     * @param position
     * @param positionTitle
     * @param urlValue
     */
    void onFilterDone(int position, String positionTitle, String urlValue);
}