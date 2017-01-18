/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.srba.siss.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.srba.siss.R;
import com.srba.siss.base.BaseActivity;
import com.srba.siss.base.BaseMvpActivity;
import com.srba.siss.bean.BuyerEmand;
import com.srba.siss.filter.interfaces.OnFilterDoneListener;
import com.srba.siss.listener.OnAreaPopClickListener;
import com.srba.siss.listener.OnSingleListPopClickListener;
import com.srba.siss.mvp.buyeremand.BuyerEmandContract;
import com.srba.siss.mvp.buyeremand.BuyerEmandPresenter;
import com.srba.siss.util.Timber;
import com.srba.siss.view.BottomPopupWindow;
import com.srba.siss.view.PopupWindowArea;
import com.srba.siss.view.PopupWindowSingleList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
public class AddBuyerResActivity extends BaseMvpActivity<BuyerEmandPresenter> implements View.OnClickListener,BuyerEmandContract.View,OnAreaPopClickListener,OnSingleListPopClickListener {
    @BindView(R.id.tv_choose_area)
    TextView tv_choose_area;
    @BindView(R.id.tv_decoration)
    TextView tv_decoration;
    @BindView(R.id.tv_building_type)
    TextView tv_building_type;
    @BindView(R.id.tv_direction)
    TextView tv_direction;
    @BindView(R.id.tv_purpose)
    TextView tv_purpose;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.et_neighbourhood)
    EditText et_neighbourhood;
    @BindView(R.id.et_nin_house_type)
    EditText et_nin_house_type;
    @BindView(R.id.et_max_house_type)
    EditText et_max_house_type;
    @BindView(R.id.et_min_price)
    EditText et_min_price;
    @BindView(R.id.et_max_price)
    EditText et_max_price;
    @BindView(R.id.et_min_area)
    EditText et_min_area;
    @BindView(R.id.et_max_area)
    EditText et_max_area;
    @BindView(R.id.et_min_floor)
    EditText et_min_floor;
    @BindView(R.id.et_max_floor)
    EditText et_max_floor;
    @BindView(R.id.et_otherdesc)
    EditText et_otherdesc;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_phonenum)
    EditText et_phonenum;
    @BindView(R.id.et_idnumber)
    EditText et_idnumber;
    @BindView(R.id.ll_main)
    LinearLayout ll_main;
    @BindView(R.id.imbtn_back)
    ImageButton imbtn_back;
    private PopupWindowArea popArea;
    private PopupWindowSingleList popSingle;
    private  List<String> lstOfDeco;
    private  List<String> lstOfBuild;
    private  List<String> lstOfDirec;
    private  List<String> lstOfPurpose;
    private static int POP_STEP = -1;
    /**
     * 区域
     */
    private String region = "";
    /**
     * 片区
     */
    private String regionDetail = "";
    /**
     * 装修程度
     */
    private String decoration = "";
    /**
     * 建筑类型
     */
    private String buildingType = "";
    /**
     * 朝向
     */
    private String direction = "";
    /**
     * 购房目的
     */
    private String purpose = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buyeres);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected BuyerEmandPresenter onCreatePresenter() {
        return new BuyerEmandPresenter(this);
    }

    private void initData() {
        lstOfDeco = new ArrayList<>();
        lstOfDeco.add("毛坯");
        lstOfDeco.add("普通 ");
        lstOfDeco.add("精装");
        lstOfDeco.add("中档");
        lstOfDeco.add("高档");
        lstOfDeco.add("豪装");

       lstOfBuild = new ArrayList<>();
        lstOfBuild.add("多层");
        lstOfBuild.add("电梯 ");
        lstOfBuild.add("平房");
        lstOfBuild.add("商住两用");
        lstOfBuild.add("写住两用");
        lstOfBuild.add("独院");
        lstOfBuild.add("其他");

        lstOfDirec = new ArrayList<>();
        lstOfDirec.add("东");
        lstOfDirec.add("南");
        lstOfDirec.add("西");
        lstOfDirec.add("北");
        lstOfDirec.add("东南");
        lstOfDirec.add("西南");
        lstOfDirec.add("其他");

        lstOfPurpose = new ArrayList<>();
        lstOfPurpose.add("不限");
        lstOfPurpose.add("换房");
        lstOfPurpose.add("就业");
        lstOfPurpose.add("求学");
        lstOfPurpose.add("结婚");
        lstOfPurpose.add("养老");
        lstOfPurpose.add("投资");

    }

    private void initView() {


    }

    @OnClick({R.id.tv_choose_area,R.id.tv_decoration,R.id.tv_building_type,R.id.tv_direction,R.id.tv_purpose,R.id.btn_save,R.id.imbtn_back})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_area:
                POP_STEP = 0;
                showPopupWindowArea();
                break;
            case R.id.tv_decoration:
                POP_STEP = 1;
                showPopupWindowSingle(lstOfDeco);
                break;
            case R.id.tv_building_type:
                POP_STEP = 2;
                showPopupWindowSingle(lstOfBuild);
                break;
            case R.id.tv_direction:
                POP_STEP = 3;
                showPopupWindowSingle(lstOfDirec);
                break;
            case R.id.tv_purpose:
                POP_STEP = 4;
                showPopupWindowSingle(lstOfPurpose);

                break;
            case R.id.btn_save:
            /*    mPresenter.insertBuyerEmandOffline(region,regionDetail,et_neighbourhood.getText().toString(),et_nin_house_type.getText().toString(),et_max_house_type.getText().toString(),
                        et_min_price.getText().toString(),et_max_price.getText().toString(),et_min_area.getText().toString(),et_max_area.getText().toString(),
                        et_min_floor.getText().toString(),et_max_floor.getText().toString(),decoration,buildingType,direction,purpose,et_otherdesc.getText().toString(),
                        et_name.getText().toString(),et_idnumber.getText().toString(),et_phonenum.getText().toString());*/
                startActivity(new Intent(AddBuyerResActivity.this,ManageBuyerResActivity.class));
                break;
            case R.id.imbtn_add:
                finish();
                break;
        }
    }

    private void showPopupWindowSingle(List<String> list) {
        Timber.e(POP_STEP+"");
        ll_main.setBackgroundColor(getResources().getColor(R.color.transparent));
        popSingle = new PopupWindowSingleList(this,list,this);
        if(4 == POP_STEP){
            popSingle.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    ll_main.setBackgroundColor(getResources().getColor(R.color.white));
                }
            });
        }
        popSingle.showAtLocation(findViewById(R.id.tv_purpose), Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    /**
     *
     * 展示底部弹出菜单
     */
    private void showPopupWindowArea() {
        ll_main.setBackgroundColor(getResources().getColor(R.color.transparent));
        popArea = new PopupWindowArea(this,this);
        popArea.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ll_main.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        popArea.showAtLocation(findViewById(R.id.tv_purpose), Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    @Override
    public void onPopWindowClick(String region, String regionDetail) {
        Timber.e("onPopWindowClick"+region+regionDetail);
        if(null != popArea&& POP_STEP == 0){
            popArea.dismiss();
            if(regionDetail.equals("")){
                tv_choose_area.setText(region);
            }else {
                tv_choose_area.setText(region+"--"+regionDetail);
            }
            this.region = region;
            this.regionDetail = regionDetail;
        }
    }

    @Override
    public void updateRecyclerView(List<BuyerEmand> models) {

    }

    @Override
    public void updateFailure() {

    }

    @Override
    public void onPopWindowClick(String positionTitle) {
        if (null != popSingle){
            switch (POP_STEP){
                case 1:
                    popSingle.dismiss();
                    tv_decoration.setText(positionTitle);
                    POP_STEP = 2;
                    showPopupWindowSingle(lstOfBuild);

                    break;
                case 2:
                    popSingle.dismiss();
                    tv_building_type.setText(positionTitle);
                    POP_STEP = 3;
                    showPopupWindowSingle(lstOfDirec);

                    break;
                case 3:
                    popSingle.dismiss();
                    tv_direction.setText(positionTitle);
                    POP_STEP = 4;
                    showPopupWindowSingle(lstOfPurpose);
                    break;
                case 4:
                    popSingle.dismiss();
                    tv_purpose.setText(positionTitle);
                    break;
            }
        }
    }

    @Override
    public void onOutClick() {
        ll_main.setBackgroundColor(getResources().getColor(R.color.white));
    }
}
