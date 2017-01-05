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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.srba.siss.R;
import com.srba.siss.base.BaseActivity;
import com.srba.siss.ui.fragment.MessageFragment;
import com.srba.siss.ui.fragment.OnFragmentInteractionListener;
import com.srba.siss.ui.fragment.PageFragment;
import com.srba.siss.ui.fragment.UserMessageFragment;
import com.yolanda.nohttp.PosterHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  找合作
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class CooperationActivity extends BaseActivity implements OnFragmentInteractionListener {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_houseres)
    TextView tv_houseres;
    @BindView(R.id.tv_buyerres)
    TextView tv_buyerres;

    /**
     * fragment集合
     */
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperation);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        fragments.add(PageFragment.newInstance(1));
        fragments.add(PageFragment.newInstance(2));
        FragmentPagerAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
//设置viewpager的缓存页面的个数
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(mAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    tv_houseres.setBackgroundResource(R.drawable.selector_left_tab_bar_select);
                    tv_houseres.setTextColor(getResources().getColor(R.color.btn_white_normal));
                    tv_buyerres.setBackgroundResource(R.drawable.selector_right_tab_bar_normal);
                    tv_buyerres.setTextColor(getResources().getColor(R.color.gray));
                }else if (position == 1){
                    tv_houseres.setBackgroundResource(R.drawable.selector_left_tab_bar_normal);
                    tv_houseres.setTextColor(getResources().getColor(R.color.gray));
                    tv_buyerres.setBackgroundResource(R.drawable.selector_right_tab_bar_select);
                    tv_buyerres.setTextColor(getResources().getColor(R.color.btn_white_normal));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * 功能：主页引导栏的三个Fragment页面设置适配器
     */
    public class FragmentAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragments;

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        public Fragment getItem(int fragment) {
            return fragments.get(fragment);
        }

        public int getCount() {
            return fragments.size();
        }

    }
    @OnClick({R.id.tv_houseres,R.id.tv_buyerres})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_houseres:
                viewpager.setCurrentItem(0);
                break;
            case R.id.tv_buyerres:
                viewpager.setCurrentItem(1);
                break;
        }
    }
}
