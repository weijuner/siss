package com.srba.siss.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.srba.siss.R;
import com.srba.siss.adapter.HomePageAdapter;
import com.srba.siss.base.BaseMvpFragment;
import com.srba.siss.base.BasePresenter;
import com.srba.siss.bean.HouseResource;
import com.srba.siss.mvp.houseresource.HouseContract;
import com.srba.siss.mvp.houseresource.HousePresenter;
import com.srba.siss.ui.activity.BuyerResourceActivity;
import com.srba.siss.ui.activity.CooperationActivity;
import com.srba.siss.ui.activity.HouseResourceActivity;
import com.srba.siss.ui.activity.SearchActivity;
import com.srba.siss.util.Timber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  首页
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class HomeFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    TabLayout tab;
    ViewPager viewpager;
    /**
     * 找房源
     */
    @BindView(R.id.ll_searchhouse)
    LinearLayout ll_searchhouse;
    /**
     * 找客源
     */
    @BindView(R.id.ll_buyerres)
    LinearLayout ll_buyerres;
    /**
     * 找合作
     */
    @BindView(R.id.ll_cooperation)
    LinearLayout ll_cooperation;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    private List<Fragment> mTabs = new ArrayList<>();
    private List<String> list_title;
    NewestHouseFragment mNewsHouseFragmenr;
    //tab名称列表
    /*
    和activity沟通的监听
     */
    private OnFragmentInteractionListener mListener;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        tab = (TabLayout) view.findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("最新房源"));//添加一个Tab
        tab.addTab(tab.newTab().setText("最新需求"));//添加一个Tab
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);

        mNewsHouseFragmenr = NewestHouseFragment.newInstance();
        mTabs.add(mNewsHouseFragmenr);
        mTabs.add(PageFragment.newInstance(2));
        list_title = new ArrayList<>();
        list_title.add("最新房源");
        list_title.add("最新需求");
        FragmentPagerAdapter mAdapter = new HomePageAdapter(getChildFragmentManager(),mTabs,list_title);

        viewpager.setAdapter(mAdapter);
        tab.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));//让TabLayout成为ViewPager的指示器
        initData();
        initView();
        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        appbar.addOnOffsetChangedListener(this);
    }

    private void initData() {

    }

    @OnClick({R.id.ll_searchhouse,R.id.et_search,R.id.ll_buyerres,R.id.ll_cooperation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_searchhouse:

         //       startActivity(new Intent(getActivity(), HouseResourceActivity.class));
                break;
            case R.id.ll_buyerres:

                startActivity(new Intent(getActivity(), BuyerResourceActivity.class));
                break;
            case R.id.ll_cooperation:

                startActivity(new Intent(getActivity(), CooperationActivity.class));
                break;
            case R.id.et_search:
                    startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            Timber.e("可以下拉");
            mNewsHouseFragmenr.onAppBarExpanded();
        } else {
            Timber.e("不能下拉");
            mNewsHouseFragmenr.onAppBarCollapsed();
        }
    }
    public interface AppBarChangeListner {
        /**
         */
        void onAppBarExpanded();

        /**
         */
        void onAppBarCollapsed();
    }
}
