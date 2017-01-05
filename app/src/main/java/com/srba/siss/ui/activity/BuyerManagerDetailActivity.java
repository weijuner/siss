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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.srba.siss.R;
import com.srba.siss.adapter.TimeLineAdapter;
import com.srba.siss.base.BaseActivity;
import com.srba.siss.entity.TimeLineModel;

import java.util.ArrayList;
import java.util.List;

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
public class BuyerManagerDetailActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<TimeLineModel> mDataList = new ArrayList<>();
    private TimeLineAdapter mTimeLineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyermanagerdetail);
        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        for(int i = 0;i <20;i++) {
            TimeLineModel model = new TimeLineModel();
            model.setName("Random"+i);
            model.setAge(i);
            mDataList.add(model);
        }
        mTimeLineAdapter = new TimeLineAdapter(mDataList);
        mRecyclerView.setAdapter(mTimeLineAdapter);
    }
    private LinearLayoutManager getLinearLayoutManager() {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            return linearLayoutManager;
    }
}
