package com.srba.siss.mvp.houseresource;


import com.srba.siss.mvp.login.LoginContract;
import com.srba.siss.mvp.login.LoginModel;

import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述: 作房源presenter
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class HousePresenter extends HouseContract.Presenter {

    public HousePresenter(HouseContract.View view) {
        mView = view;
        mModel = new HouseModel();
    }




    @Override
    public void getHouseInfo() {
        Subscription subscribe = mModel.getHouseInfo()
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        Timber.d("onStart");
                    }

                    @Override
                    public void onCompleted() {
                        Timber.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("onError"+e.toString());
                        onCompleted();

                    }
                    @Override
                    public void onNext(String str) {
                        Timber.e("onNext"+str);
                   //     mView.startMainActivity();
                    }
                });
        addSubscribe(subscribe);
    }
}