package com.srba.siss.mvp.buyeremand;


import com.srba.siss.base.BaseModel;
import com.srba.siss.base.BasePresenter;
import com.srba.siss.base.BaseView;
import com.srba.siss.bean.BuyerEmand;
import com.srba.siss.bean.result.BaseResult;

import java.util.List;

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
public interface BuyerEmandContract {

    interface View extends BaseView {
        void updateRecyclerView(List<BuyerEmand> models);
        void updateFailure();
    }

    interface Model extends BaseModel {
        Observable<String> insertBuyerEmandOffline(String region, String regionDetail, String neighbourhood, String min_house_type, String max_house_type, String min_price, String max_price, String min_area, String max_area, String min_floor, String max_floor, String decoration, String buildingType, String direction, String purpose, String otherdesc, String name, String idnumber, String phonenum);
        Observable<BaseResult<BuyerEmand>> getAppBuyerEmand();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         *  @param region
         * @param regionDetail
         * @param neighbourhood
         * @param min_house_type
         * @param max_house_type
         * @param min_price
         * @param max_price
         * @param min_area
         * @param max_area
         * @param min_floor
         * @param max_floor
         * @param decoration
         * @param buildingType
         * @param direction
         * @param purpose
         * @param otherdesc
         * @param name
         * @param idnumber
         * @param phonenum
         */
        public abstract void insertBuyerEmandOffline(String region, String regionDetail, String neighbourhood, String min_house_type, String max_house_type, String min_price, String max_price, String min_area, String max_area, String min_floor, String max_floor, String decoration, String buildingType, String direction, String purpose, String otherdesc, String name, String idnumber, String phonenum);

        public abstract void getAppBuyerEmand();
    }

}
