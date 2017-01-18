package com.srba.siss.listener;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  区域选择点击监听
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public interface OnAreaPopClickListener {
    /**
     * 过滤器处理完成
     */
    void onPopWindowClick(String region, String regionDetail);
}