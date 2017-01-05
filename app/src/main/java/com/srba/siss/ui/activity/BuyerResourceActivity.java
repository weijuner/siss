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

import android.os.Bundle;
import android.widget.TextView;

import com.srba.siss.R;
import com.srba.siss.adapter.DropMenuAdapter;
import com.srba.siss.base.BaseActivity;
import com.srba.siss.entity.FilterUrl;
import com.srba.siss.filter.DropDownMenu;
import com.srba.siss.filter.interfaces.OnFilterDoneListener;

import butterknife.BindView;


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
public class BuyerResourceActivity extends BaseActivity implements OnFilterDoneListener {
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;

    @BindView(R.id.mFilterContentView)
    TextView mFilterContentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyerresource);
        initFilterDropDownView();
    }
    private void initFilterDropDownView() {
        String[] titleList = new String[]{"区域", "价格", "房型", "更多"};
        dropDownMenu.setMenuAdapter(new DropMenuAdapter(this, titleList, this));
    }

    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {
        if (position != 3) {
            dropDownMenu.setPositionIndicatorText(FilterUrl.instance().position, FilterUrl.instance().positionTitle);
        }

        dropDownMenu.close();
        mFilterContentView.setText(FilterUrl.instance().toString());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        FilterUrl.instance().clear();
    }
}
