package com.srba.siss.api;

import com.srba.siss.bean.Gank;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  Api服务类
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public interface ApiService {

    String BASE_URL="http://gank.io/";
    String HOST = "http://10.168.1.201:8888/";
    @GET("api/data/Android/10/{page}")
    Observable<Gank> getGank(@Path("page") String page);

    /**
     * 登录
     * @param route
     * @return  Observable被观察者，它决定什么时候触发事件以及触发怎样的事件
     */
    @POST("so/srba/auth/login")
    Observable<String> login(@Body RequestBody route);
}
