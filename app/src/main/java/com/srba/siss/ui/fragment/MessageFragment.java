package com.srba.siss.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.srba.siss.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  消息
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class MessageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_sys_message)
    TextView tv_sys_message;
    @BindView(R.id.tv_user_message)
    TextView tv_user_message;
    private String mParam1;
    private String mParam2;
    /**
     * fragment集合
     */
    private List<Fragment> fragments = new ArrayList<>();
    private OnFragmentInteractionListener mListener;


    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
     /*   EMClient.getInstance().login("zwj", "123456", new EMCallBack(){
            @Override
            public void onSuccess() {
                Timber.e("onSuccess");
                // 跳转到聊天界面，开始聊天
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                // EaseUI封装的聊天界面需要这两个参数，聊天者的username，以及聊天类型，单聊还是群聊
                intent.putExtra("userId", "zwj2");
                intent.putExtra("chatType", EMMessage.ChatType.Chat);
                startActivity(intent);
            }

            @Override
            public void onError(int i, String s) {
                Timber.e("onError"+s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });*/
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messge, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        fragments.add(PageFragment.newInstance(1));
        fragments.add(UserMessageFragment.newInstance("", ""));
        FragmentPagerAdapter mAdapter = new FragmentAdapter(getChildFragmentManager(),fragments);
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
                    tv_sys_message.setBackgroundResource(R.drawable.selector_left_tab_bar_select);
                    tv_sys_message.setTextColor(getActivity().getResources().getColor(R.color.btn_white_normal));
                    tv_user_message.setBackgroundResource(R.drawable.selector_right_tab_bar_normal);
                    tv_user_message.setTextColor(getActivity().getResources().getColor(R.color.gray));
                }else if (position == 1){
                    tv_sys_message.setBackgroundResource(R.drawable.selector_left_tab_bar_normal);
                    tv_sys_message.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    tv_user_message.setBackgroundResource(R.drawable.selector_right_tab_bar_select);
                    tv_user_message.setTextColor(getActivity().getResources().getColor(R.color.btn_white_normal));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /**
     * 功能：主页引导栏的三个Fragment页面设置适配器
     */
    public class FragmentAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragments;

        public FragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
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
        } catch (ClassCastException e) {//没有实现接口抛出异常
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @OnClick({R.id.tv_sys_message,R.id.tv_user_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sys_message:
               viewpager.setCurrentItem(0);
                break;
            case R.id.tv_user_message:
                viewpager.setCurrentItem(1);
                break;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
