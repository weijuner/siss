package com.srba.siss.ui.activity;

import android.os.Bundle;

import com.srba.siss.R;
import com.srba.siss.base.BaseActivity;
import com.srba.siss.bean.Gank;
import com.srba.siss.mvp.main.MainContract;
import com.srba.siss.mvp.main.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.getGank();
    }

    /**
     * 基类onCreate调用
     * @return presenter
     */
    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(Gank data) {

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void hideDialog() {

    }
}
