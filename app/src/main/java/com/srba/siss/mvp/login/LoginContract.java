package com.srba.siss.mvp.login;


import com.srba.siss.base.BaseModel;
import com.srba.siss.base.BasePresenter;
import com.srba.siss.base.BaseView;

import rx.Observable;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  主契约类
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public interface LoginContract {

    interface View extends BaseView {
        void startMainActivity();

    }

    interface Model extends BaseModel {
        Observable<String> login(String username,String password);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void login(String username,String password);
    }
}
