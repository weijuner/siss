package com.srba.siss.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.igexin.sdk.PushManager;
import com.srba.siss.R;
import com.srba.siss.base.BaseMvpActivity;
import com.srba.siss.mvp.main.MainContract;
import com.srba.siss.mvp.main.MainPresenter;
import com.srba.siss.ui.fragment.BusinessFragment;
import com.srba.siss.ui.fragment.HomeFragment;
import com.srba.siss.ui.fragment.MeFragment;
import com.srba.siss.ui.fragment.MessageFragment;
import com.srba.siss.ui.fragment.OnFragmentInteractionListener;
import com.srba.siss.util.Timber;
import com.srba.siss.view.BottomPopupWindow;
import com.srba.siss.view.GradientIconView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeLinearLayout;
import cn.bingoogolapple.badgeview.BGABadgeable;
import cn.bingoogolapple.badgeview.BGADragDismissDelegate;

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
    @BindView(R.id.ll_message)
    BGABadgeLinearLayout ll_message;
    @BindView(R.id.imbtn_add)
    ImageButton imbtn_add;
    @BindView(R.id.ll_home)
    LinearLayout ll_home;
    @BindView(R.id.ll_business)
    LinearLayout ll_business;
    @BindView(R.id.ll_me)
    LinearLayout ll_me;
    private List<Fragment> mTabs = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    private BottomPopupWindow menuWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initView();
// com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(),com.srba.siss.service.PushService.class);
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(),com.srba.siss.service.DemoIntentService.class);
    }

    private void initView() {
        graiv_home.setIconAlpha(1.0f);
        graiv_business.setIconAlpha(0f);
        graiv_message.setIconAlpha(0f);
        graiv_me.setIconAlpha(0f);
        ll_message.showTextBadge("99+");
        ll_message.setDragDismissDelegage(new BGADragDismissDelegate() {
            @Override
            public void onDismiss(BGABadgeable badgeable) {

            }
        });
    }


    /**
     * 初始化fragment
     */
    private void initFragments() {

        mTabs.add(HomeFragment.newInstance());
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
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
    }
    @OnClick({R.id.ll_home,R.id.ll_business,R.id.ll_message,R.id.ll_me,R.id.imbtn_add})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                mPresenter.switchNavigation(view.getId());
                break;
            case R.id.ll_business:
                mPresenter.switchNavigation(view.getId());
                break;
            case R.id.ll_message:
                mPresenter.switchNavigation(view.getId());
                break;
            case R.id.ll_me:
                mPresenter.switchNavigation(view.getId());
                break;
            case R.id.imbtn_add:
                showBottomPopupWindow();
                break;
            case R.id.ll_add_houseres:
                if (null != menuWindow){
                    menuWindow.dismiss();
                }
                startActivity(new Intent(MainActivity.this,AddHouseResActivity.class));

            case R.id.ll_add_buyer:
                if (null != menuWindow){
                    menuWindow.dismiss();
                }
                startActivity(new Intent(MainActivity.this,AddBuyerResActivity.class));

            case R.id.ll_sell_cooperation:
            case R.id.ll_buy_cooperation:
                break;
        }
    }

    /**
     * 展示底部弹出菜单
     */
    private void showBottomPopupWindow() {
        menuWindow = new BottomPopupWindow(this);
        menuWindow.setOnInnerItemClickListener(this);
        menuWindow.showAtLocation(findViewById(R.id.imbtn_add), Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
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
    public void onFail(String err) {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void switch2HomePage() {
        Timber.e("首页");


        mViewPager.setCurrentItem(0, false);

        graiv_home.setIconAlpha(1.0f);
        graiv_business.setIconAlpha(0f);
        graiv_message.setIconAlpha(0f);
        graiv_me.setIconAlpha(0f);
    }

    @Override
    public void switch2Business() {
        Timber.e("业务");
        mViewPager.setCurrentItem(1, false);
        graiv_home.setIconAlpha(0f);
        graiv_business.setIconAlpha(1.0f);
        graiv_message.setIconAlpha(0f);
        graiv_me.setIconAlpha(0f);
    }


    @Override
    public void switch2Message() {
        Timber.e("消息");
        mViewPager.setCurrentItem(2, false);
        graiv_home.setIconAlpha(0f);
        graiv_business.setIconAlpha(0f);
        graiv_message.setIconAlpha(1.0f);
        graiv_me.setIconAlpha(0f);

    }

    @Override
    public void switch2Me() {
        Timber.e("我的");
        mViewPager.setCurrentItem(3, false);
        graiv_home.setIconAlpha(0f);
        graiv_business.setIconAlpha(0f);
        graiv_message.setIconAlpha(0f);
        graiv_me.setIconAlpha(1.0f);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position){
            case 0:
                graiv_home.setIconAlpha(1 - positionOffset);//左边越来越淡
                graiv_business.setIconAlpha(positionOffset);//右边越来越清晰
                break;
            case 1:
                graiv_business.setIconAlpha(1 - positionOffset);//左边越来越淡
                graiv_message.setIconAlpha(positionOffset);//右边越来越清晰
                break;
            case 2:
                graiv_message.setIconAlpha(1 - positionOffset);//左边越来越淡
                graiv_me.setIconAlpha(positionOffset);//右边越来越清晰
                break;

        }
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
