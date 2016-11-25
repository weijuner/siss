package com.srba.siss.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  线程调度器
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class RxSchedulers {

    /**
     * 线程切换器
     * @param <T>
     * @return ransformer继承自Func1<Observable<T>, Observable<R>>，可以通过它将一种类型的Observable转换成另一种类型的Observable
     */
    public static <T> Observable.Transformer<T, T> switchThread() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())/// 指定 subscribe() 发生在 IO 线程
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());// // 指定 Subscriber 的回调发生在主线程
            }
        };
    }
}
