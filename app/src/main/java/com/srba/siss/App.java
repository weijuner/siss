package com.srba.siss;

import android.app.Application;

import timber.log.Timber;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  app类
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class App extends Application {

    private static App mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
         //   Timber.plant(new CrashReportingTree());
        }
    }

    public static App getContext() {
        return mContext;
    }
}
