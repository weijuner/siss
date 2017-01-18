package com.srba.siss.mvp.main;


import com.srba.siss.R;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述: 作为连接Model和View的桥梁
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class MainPresenter extends MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }



    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.ll_home:
                mView.switch2HomePage();
                break;
            case R.id.ll_business:
                mView.switch2Business();
                break;
            case R.id.ll_message:
                mView.switch2Message();
                break;
            case R.id.ll_me:
                mView.switch2Me();
                break;
        }
    }
}
