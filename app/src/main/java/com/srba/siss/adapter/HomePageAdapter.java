package com.srba.siss.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/12/22 11:09
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/12/22       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class HomePageAdapter  extends FragmentPagerAdapter {

    private List<Fragment> list_fragment;                         //fragment列表
    private List<String> list_Title;                              //tab名的列表


    public HomePageAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_Title = list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {

        return list_Title.get(position % list_Title.size());
    }
}