package com.srba.siss.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.srba.siss.R;
import com.srba.siss.base.BaseMvpActivity;
import com.srba.siss.bean.Gank;
import com.srba.siss.mvp.main.MainContract;
import com.srba.siss.mvp.main.MainPresenter;
import com.srba.siss.ui.fragment.BusinessFragment;
import com.srba.siss.ui.fragment.HomeFragment;
import com.srba.siss.ui.fragment.MeFragment;
import com.srba.siss.ui.fragment.MessageFragment;
import com.srba.siss.ui.fragment.OnFragmentInteractionListener;
import com.srba.siss.view.GradientIconView;
import com.srba.siss.view.GradientTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  主activity
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class MainActivity extends BaseMvpActivity<MainPresenter>
        implements MainContract.View, ViewPager.OnPageChangeListener,OnFragmentInteractionListener,View.OnClickListener {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.graiv_home)
    GradientIconView graiv_home;
    @BindView(R.id.graiv_business)
    GradientIconView graiv_business;
    @BindView(R.id.graiv_message)
    GradientIconView graiv_message;
    @BindView(R.id.graiv_me)
    GradientIconView graiv_me;
    @BindView(R.id.gratv_home)
    GradientTextView gratv_home;
    @BindView(R.id.gratv_business)
    GradientTextView gratv_business;
    @BindView(R.id.gratv_message)
    GradientTextView gratv_message;
    @BindView(R.id.gratv_me)
    GradientTextView gratv_me;
    private List<Fragment> mTabs = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.getGank();
        initFragments();
    }

    /**
     * 初始化fragment
     */
    private void initFragments() {

        mTabs.add(HomeFragment.newInstance("", ""));
        mTabs.add(BusinessFragment.newInstance("", ""));
        mTabs.add(MessageFragment.newInstance("", ""));
        mTabs.add(MeFragment.newInstance("", ""));

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mTabs.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
    }
    @OnClick({R.id.graiv_home,R.id.graiv_business,R.id.graiv_message,R.id.graiv_me})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.graiv_home:
            case R.id.graiv_business:
            case R.id.graiv_message:
            case R.id.graiv_me:
                mPresenter.switchNavigation(view.getId());
                break;
        }
    }

    /**
     * 基类onCreate调用
     * @return presenter
     */
    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(Gank data) {

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void switch2HomePage() {
        mViewPager.setCurrentItem(0, false);
    }

    @Override
    public void switch2Business() {
        mViewPager.setCurrentItem(1, false);
    }

    @Override
    public void switch2Message() {
        mViewPager.setCurrentItem(2, false);
    }

    @Override
    public void switch2Me() {
        mViewPager.setCurrentItem(3, false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * fragment回调
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
