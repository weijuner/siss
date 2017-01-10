package com.srba.siss.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            Log.e("tag","unSubscribe");
            mPresenter.unSubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    protected abstract P onCreatePresenter();
}
