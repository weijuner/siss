package com.srba.siss.ui.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.srba.siss.R;
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
 * 描述:  业务
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class BusinessFragment extends Fragment {
    private View mContentView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_buyer_buss)
    TextView tv_buyer_buss;
    @BindView(R.id.tv_seller_buss)
    TextView tv_seller_buss;
    @BindView(R.id.tv_cooperation_buss)
    TextView tv_cooperation_buss;
    private String mParam1;
    private String mParam2;
    /**
     * 和activity沟通的监听
     */
    private OnFragmentInteractionListener mListener;
    /**
     * fragment集合
     */
    private List<Fragment> fragments = new ArrayList<>();

    public static BusinessFragment newInstance(String param1, String param2) {
        BusinessFragment fragment = new BusinessFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BusinessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.e("onCreateView");
        /**
         * 防止Fragment多次切换时调用onCreateView重新加载View
         */
        if (null == mContentView)
        {
            mContentView = inflater.inflate(R.layout.fragment_business, container, false);
            ButterKnife.bind(this, mContentView);
            initView();

            /**
             * 为了保证一开始加载Fragment的时候判断是否需要加载数据
             */
            if (getUserVisibleHint())
            {
                Timber.e("数据加载");
                //todo:数据加载
            }
        } else {
            /**
             * 缓存的rootView需要判断是否已经被加过parent，
             * 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
             */
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null)
            {
                parent.removeView(mContentView);
            }
            if (getUserVisibleHint())
            {
                Timber.e("数据加载下面");
                //todo:数据加载
            }
        }
        return mContentView;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        fragments.add(PageFragment.newInstance(1));
        fragments.add(PageFragment.newInstance(1));
        fragments.add(PageFragment.newInstance(1));
        FragmentPagerAdapter mAdapter = new FragmentAdapter(getChildFragmentManager(),fragments);
        //设置viewpager的缓存页面的个数
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(mAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    tv_buyer_buss.setBackgroundResource(R.drawable.selector_left_tab_bar_select);
                    tv_buyer_buss.setTextColor(getActivity().getResources().getColor(R.color.btn_white_normal));
                    tv_seller_buss.setBackgroundResource(R.drawable.selector_center_tab_bar_normal);
                    tv_seller_buss.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    tv_cooperation_buss.setBackgroundResource(R.drawable.selector_right_tab_bar_normal);
                    tv_cooperation_buss.setTextColor(getActivity().getResources().getColor(R.color.gray));
                }else if (position == 1){
                    tv_buyer_buss.setBackgroundResource(R.drawable.selector_left_tab_bar_normal);
                    tv_buyer_buss.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    tv_seller_buss.setBackgroundResource(R.drawable.selector_center_tab_bar_select);
                    tv_seller_buss.setTextColor(getActivity().getResources().getColor(R.color.btn_white_normal));
                    tv_cooperation_buss.setBackgroundResource(R.drawable.selector_right_tab_bar_normal);
                    tv_cooperation_buss.setTextColor(getActivity().getResources().getColor(R.color.gray));
                }else if(position == 2){
                    tv_buyer_buss.setBackgroundResource(R.drawable.selector_left_tab_bar_normal);
                    tv_buyer_buss.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    tv_seller_buss.setBackgroundResource(R.drawable.selector_center_tab_bar_normal);
                    tv_seller_buss.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    tv_cooperation_buss.setBackgroundResource(R.drawable.selector_right_tab_bar_select);
                    tv_cooperation_buss.setTextColor(getActivity().getResources().getColor(R.color.btn_white_normal));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @OnClick({R.id.tv_buyer_buss,R.id.tv_seller_buss,R.id.tv_cooperation_buss})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_buyer_buss:
                viewpager.setCurrentItem(0);
                break;
            case R.id.tv_seller_buss:
                viewpager.setCurrentItem(1);
                break;
            case R.id.tv_cooperation_buss:
                viewpager.setCurrentItem(2);
                break;
        }
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

}
