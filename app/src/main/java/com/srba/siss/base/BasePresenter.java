package com.srba.siss.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class BasePresenter<V extends BaseView, M extends BaseModel> {

    /**
     * 视图层
     */
    protected V mView;
    /**
     * model层
     */
    protected M mModel;
    /**
     * 所有的 Subscription 对象可以添加到 CompositeSubscription，然后可以使用 CompositeSubscription.unsubscribe() 方法在同一时间进行退订(unsubscribed)
     */
    private CompositeSubscription mCompositeSubscription;

    /**
     * 添加订阅者
     * @param subscription Subscription接口,可以用来取消订阅.
     */
    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * 取消订阅者
     */
    public void unSubscribe() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }
}
