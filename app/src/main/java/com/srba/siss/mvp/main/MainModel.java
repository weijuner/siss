package com.srba.siss.mvp.main;


import com.srba.siss.api.ApiEngine;
import com.srba.siss.bean.Gank;
import com.srba.siss.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by Nicholas on 2016/10/30.
 */

public class MainModel implements MainContract.Model {

    @Override
    public Observable<Gank> getGank() {
        return ApiEngine.getInstance().getApiService()
                .getGank("1")
                .compose(RxSchedulers.<Gank>switchThread());
    }
}
