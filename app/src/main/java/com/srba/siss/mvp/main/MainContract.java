package com.srba.siss.mvp.main;


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
public interface MainContract {

    interface View extends BaseView {

        void showDialog();


        void onFail(String err);

        void hideDialog();

        void switch2HomePage();

        void switch2Business();

        void switch2Message();

        void switch2Me();

    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<View, Model> {


        public abstract void switchNavigation(int id);
    }
}
