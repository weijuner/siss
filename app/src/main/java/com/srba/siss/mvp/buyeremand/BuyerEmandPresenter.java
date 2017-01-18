package com.srba.siss.mvp.buyeremand;


import com.srba.siss.bean.BuyerEmand;
import com.srba.siss.bean.result.BaseResult;
import com.srba.siss.util.Timber;

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
public class BuyerEmandPresenter<T> extends BuyerEmandContract.Presenter {

    public BuyerEmandPresenter(BuyerEmandContract.View view) {
        mView = view;
        mModel = new BuyerEmandModel();
    }


    @Override
    public void insertBuyerEmandOffline(String region, String regionDetail, String neighbourhood, String min_house_type, String max_house_type, String min_price, String max_price, String min_area, String max_area, String min_floor, String max_floor, String decoration, String buildingType, String direction, String purpose, String otherdesc, String name, String idnumber, String phonenum) {
        Subscription subscribe = mModel.insertBuyerEmandOffline(region,regionDetail,neighbourhood,min_house_type,max_house_type,min_price,max_price,min_area,max_area,min_floor,max_floor,decoration,buildingType,direction,purpose,otherdesc,name,idnumber,phonenum)
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
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getAppBuyerEmand() {
        Subscription subscribe = mModel.getAppBuyerEmand()
                .subscribe(new Subscriber<BaseResult<BuyerEmand>>() {

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
                    public void onNext(BaseResult<BuyerEmand> result) {
                        mView.updateRecyclerView(result.getData());
                        Timber.e("onNext");
                    }
                });
        addSubscribe(subscribe);
    }
}
