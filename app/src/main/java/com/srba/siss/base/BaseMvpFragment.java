package com.srba.siss.base;

import android.support.v4.app.Fragment;


/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  fragment的mvp基类
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends Fragment {

    protected P mPresenter;

    @Override
    public void onResume() {
        super.onResume();
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    protected abstract P onCreatePresenter();
}
