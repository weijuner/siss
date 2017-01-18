package com.srba.siss.mvp.login;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.srba.siss.bean.User;
import com.srba.siss.bean.result.BaseResult;
import com.srba.siss.util.Timber;

import org.json.JSONObject;

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
                        mView.loginFailure(e.getMessage());
                        onCompleted();

                    }
                    @Override
                    public void onNext(String str) {
                        JSONObject json = null;//转换为JSONObject
                        Timber.e("onNext"+str);
                        try {
                            json = new JSONObject(str);
                           String result =  json.getString("result");
                            Timber.e("onNext"+result);
                            if(result.equals("200")){
                                BaseResult<User> user = new Gson().fromJson(str,new TypeToken<BaseResult<User>>(){}.getType());
                                mView.loginSuccess(user.getData().get(0).getUserName(),user.getData().get(0).getName());
                            }else if(result.equals("45000")) {
                                Timber.e("onNext" + 45000);
                                BaseResult<String> user = new Gson().fromJson(str,new TypeToken<BaseResult<String>>(){}.getType());
                                mView.loginFailure(user.getData().get(0));
                                //   mView.loginFailure(result.getMsg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                      //  Timber.e("onNext"+str);
                    }
                });
        addSubscribe(subscribe);
    }
}
