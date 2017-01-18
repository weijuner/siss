package com.srba.siss.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.srba.siss.R;
import com.srba.siss.filter.adapter.BaseBaseAdapter;
import com.srba.siss.filter.interfaces.OnFilterItemClickListener;
import com.srba.siss.filter.util.CommonUtil;

import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  单列listview
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class BottomListView<DATA> extends LinearLayout implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private BaseBaseAdapter<DATA> mAdapter;
    private OnFilterItemClickListener<DATA> mOnItemClickListener;

    public BottomListView(Context context) {
        this(context, null);
    }

    public BottomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.popup_single_list, this);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
    }

    /**
     * 设置适配器
     * @param adapter
     * @return
     */
    public BottomListView<DATA> adapter(BaseBaseAdapter<DATA> adapter) {
        this.mAdapter = adapter;
        mListView.setAdapter(adapter);
        return this;
    }

    /**
     * 设置监听
     * @param onItemClickListener
     * @return
     */
    public BottomListView<DATA> onItemClick(OnFilterItemClickListener<DATA> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    public void setList(List<DATA> list, int checkedPositoin) {
        mAdapter.setList(list);
        if (checkedPositoin != -1) {
            mListView.setItemChecked(checkedPositoin, true);
        }
    }

    /**
     * 点击监听
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (CommonUtil.isFastDoubleClick()) {
            return;
        }

        DATA item = mAdapter.getItem(position);
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(item);
        }
    }
}
