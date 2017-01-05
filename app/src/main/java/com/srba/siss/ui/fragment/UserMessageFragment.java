package com.srba.siss.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.srba.siss.R;
import com.srba.siss.adapter.MessageListAdapter;
import com.srba.siss.ui.activity.ChatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  系统消息
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class UserMessageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.expandablelistview)
    ExpandableListView expandablelistview;
    private List<String> groupArray;
    private List<List<String>> childArray;
    private String mParam1;
    private String mParam2;
    /*
    和activity沟通的监听
     */
    private OnFragmentInteractionListener mListener;


    public static UserMessageFragment newInstance(String param1, String param2) {
        UserMessageFragment fragment = new UserMessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public UserMessageFragment() {
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
        View view = inflater.inflate(R.layout.fragment_usermessage, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initData() {
        groupArray = new ArrayList<>();
        groupArray.add("业主");
        groupArray.add("客户");
        groupArray.add("经纪人");

        childArray = new ArrayList<>();
        List<String> arr1 = new ArrayList<>();
        arr1.add("业主1");
        arr1.add("业主2");
        childArray.add(arr1);
        List<String> arr2 = new ArrayList<>();
        arr2.add("客户1");
        childArray.add(arr2);
        List<String> arr3 = new ArrayList<>();
        arr3.add("经纪人1");
        childArray.add(arr3);
        expandablelistview.setAdapter(new MessageListAdapter(getActivity(),groupArray,childArray));
        expandablelistview.setOnChildClickListener(new MyChildClickListener());

    }

    private void initView() {

    }
    class MyChildClickListener implements ExpandableListView.OnChildClickListener{

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            EMClient.getInstance().login("zwj", "123456", new EMCallBack(){
                @Override
                public void onSuccess() {
                    // 跳转到聊天界面，开始聊天
                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    // EaseUI封装的聊天界面需要这两个参数，聊天者的username，以及聊天类型，单聊还是群聊
                    intent.putExtra("userId", "zwj2");
                    intent.putExtra("chatType", EMMessage.ChatType.Chat);
                    startActivity(intent);
                }

                @Override
                public void onError(int i, String s) {
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
            return false;
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
