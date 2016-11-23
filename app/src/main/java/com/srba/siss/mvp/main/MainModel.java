package com.srba.siss.mvp.main;


import com.srba.siss.api.ApiEngine;
import com.srba.siss.bean.Gank;
import com.srba.siss.rx.RxSchedulers;

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
public class MainModel implements MainContract.Model {

    @Override
    public Observable<Gank> getGank() {
        return ApiEngine.getInstance().getApiService()
                .getGank("1")
                .compose(RxSchedulers.<Gank>switchThread());
    }
}
