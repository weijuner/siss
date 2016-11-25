package com.srba.siss.mvp.login;


import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;

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
public class LoginPresenter extends LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mModel = new LoginModel();
    }


    @Override
    public void login(String username,String password) {
        Subscription subscribe = mModel.login(username,password)
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
                        mView.startMainActivity();
                    }
                });
        addSubscribe(subscribe);
    }
}
