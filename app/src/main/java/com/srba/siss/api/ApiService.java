package com.srba.siss.api;

import com.srba.siss.bean.BuyerEmand;
import com.srba.siss.bean.result.BaseResult;
import com.srba.siss.bean.result.HouseResult;


import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
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

  //  String HOST = "http://10.168.8.196:8080/srba_siss_main/";
    String HOST = "http://10.168.8.196:8080/";


    /**
     * 登录
     * @param route
     * @return  Observable被观察者，它决定什么时候触发事件以及触发怎样的事件
     */
    @POST("srba_siss_main/auth/login")
    Observable<String> login(@Body RequestBody route);

    /**
     * 获取房源信息
     * @return  Observable被观察者
     */
    @POST("srba_siss_app/appHouseResouse/getAppHouseResource")
    Observable<HouseResult> getHouseInfo(@Body RequestBody model);


    /**
     * 插入线下需求
     * @return  Observable被观察者
     */
    @POST("srba_siss_app/appBuyerEmandOffline/insertBuyerEmandOffline")
    Observable<String> insertBuyerEmandOffline(@Body RequestBody model);

    /**
     * 获取房源信息
     * @return  Observable被观察者
     */
    @POST("srba_siss_app/appBuyerEmand/getAppBuyerEmand")
    Observable<BaseResult<BuyerEmand>> getAppBuyerEmand(@Body RequestBody model);

}
