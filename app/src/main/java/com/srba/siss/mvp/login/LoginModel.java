package com.srba.siss.mvp.login;

import com.google.gson.Gson;
import com.srba.siss.api.ApiEngine;
import com.srba.siss.rx.RxSchedulers;
import java.util.HashMap;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  登录model
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class LoginModel implements LoginContract.Model {


    @Override
    public Observable<String> login(String username,String password) {
        Gson gson=new Gson();

        HashMap<String,String> paramsMap=new HashMap<>();

        paramsMap.put("username",username);
        paramsMap.put("password",password);

        String strEntity = gson.toJson(paramsMap);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);


        return ApiEngine.getInstance().getApiService()
                .login(body)
                .compose(RxSchedulers.<String>switchThread());
    }
}
