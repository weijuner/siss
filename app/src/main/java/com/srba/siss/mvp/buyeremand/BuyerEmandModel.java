package com.srba.siss.mvp.buyeremand;


import com.google.gson.Gson;
import com.srba.siss.api.ApiEngine;
import com.srba.siss.bean.BuyerEmand;
import com.srba.siss.bean.result.BaseResult;
import com.srba.siss.rx.RxSchedulers;

import java.util.HashMap;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  主model
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class BuyerEmandModel implements BuyerEmandContract.Model {


    @Override
    public Observable<String> insertBuyerEmandOffline(String region, String regionDetail, String neighbourhood, String min_house_type, String max_house_type, String min_price, String max_price, String min_area, String max_area, String min_floor, String max_floor, String decoration, String buildingType, String direction, String purpose, String otherdesc, String name, String idnumber, String phonenum) {

        Gson gson=new Gson();

        HashMap<String,String> paramsMap=new HashMap<>();

        paramsMap.put("region",region);
        paramsMap.put("regionDetail",regionDetail);

        String strEntity = gson.toJson(paramsMap);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);

        return ApiEngine.getInstance().getApiService()
                .insertBuyerEmandOffline(body)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<BaseResult<BuyerEmand>> getAppBuyerEmand() {
        Gson gson=new Gson();

        HashMap<String,String> paramsMap=new HashMap<>();


        String strEntity = gson.toJson(paramsMap);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);


        return ApiEngine.getInstance().getApiService()
                .getAppBuyerEmand(body)
                .compose(RxSchedulers.<BaseResult<BuyerEmand>>switchThread());
    }
}
