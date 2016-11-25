package com.srba.siss.base;

import android.os.Bundle;


/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  activity的mvp基类
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    /**
     * view和model的桥梁
     */
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建activity时创建presenter
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁activity同时取消订阅
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    protected abstract P onCreatePresenter();
}
