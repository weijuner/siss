package com.srba.siss.mvp.main;


import com.srba.siss.base.BaseModel;
import com.srba.siss.base.BasePresenter;
import com.srba.siss.base.BaseView;
import com.srba.siss.bean.Gank;

import rx.Observable;

/**
 * Created by Nicholas on 2016/10/30.
 */

public interface MainContract {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(Gank data);

        void onFail(String err);

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<Gank> getGank();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getGank();
    }
}
