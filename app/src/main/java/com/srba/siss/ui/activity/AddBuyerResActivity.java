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
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.srba.siss.R;
import com.srba.siss.base.BaseActivity;
import com.srba.siss.filter.interfaces.OnFilterDoneListener;
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
public class AddBuyerResActivity extends BaseActivity  implements View.OnClickListener, OnFilterDoneListener {
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
    private PopupWindowArea popArea;
    private PopupWindowSingleList popSingle;
    private  List<String> lstOfDeco;
    private  List<String> lstOfBuild;
    private  List<String> lstOfDirec;
    private  List<String> lstOfPurpose;
    private static int POP_STEP = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buyeres);
        ButterKnife.bind(this);
        initView();
        initData();
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

    @OnClick({R.id.tv_choose_area,R.id.tv_decoration,R.id.tv_building_type,R.id.tv_direction,R.id.tv_purpose})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_area:
                showPopupWindowArea();
                break;
            case R.id.tv_decoration:
                showPopupWindowSingle(lstOfDeco);
                POP_STEP = 1;
                break;
            case R.id.tv_building_type:
                showPopupWindowSingle(lstOfBuild);
                POP_STEP = 2;
                break;
            case R.id.tv_direction:

                showPopupWindowSingle(lstOfDirec);
                POP_STEP = 3;
                break;
            case R.id.tv_purpose:
                showPopupWindowSingle(lstOfPurpose);
                POP_STEP = 4;
            case R.id.btn_save:
                POP_STEP = 4;
                break;
        }
    }

    private void showPopupWindowSingle(List<String> list) {
        popSingle = new PopupWindowSingleList(this,list,this);
        popSingle.showAtLocation(findViewById(R.id.tv_purpose), Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     *
     * 展示底部弹出菜单
     */
    private void showPopupWindowArea() {
        popArea = new PopupWindowArea(this);
        popArea.showAtLocation(findViewById(R.id.tv_purpose), Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {
        Timber.e("onFilterDone");
        if (null != popSingle){



        switch (POP_STEP){
            case 1:
                popSingle.dismiss();
                showPopupWindowSingle(lstOfBuild);
                POP_STEP = 2;
                break;
            case 2:
                popSingle.dismiss();
                showPopupWindowSingle(lstOfDirec);
                POP_STEP = 3;
                break;
            case 3:
                popSingle.dismiss();
                showPopupWindowSingle(lstOfPurpose);
                POP_STEP = 4;
                break;
            case 4:
                popSingle.dismiss();
                break;
        }
        }
    }
}
