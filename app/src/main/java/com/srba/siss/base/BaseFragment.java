package com.srba.siss.base;

import android.support.v4.app.Fragment;



public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

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