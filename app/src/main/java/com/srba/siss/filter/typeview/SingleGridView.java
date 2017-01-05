package com.srba.siss.filter.typeview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.srba.siss.filter.adapter.BaseBaseAdapter;
import com.srba.siss.filter.interfaces.OnFilterItemClickListener;
import com.srba.siss.filter.util.CommonUtil;
import com.srba.siss.filter.util.UIUtil;

import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  单个gridview
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class SingleGridView<DATA> extends GridView implements AdapterView.OnItemClickListener {

    private BaseBaseAdapter<DATA> mAdapter;
    private OnFilterItemClickListener<DATA> mOnItemClickListener;

    public SingleGridView(Context context) {
        this(context, null);
    }

    public SingleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SingleGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setSelector(new ColorDrawable(Color.TRANSPARENT));
        setNumColumns(3);
        setBackgroundColor(Color.WHITE);
        setSmoothScrollbarEnabled(false);


        int dp = UIUtil.dp(context, 15);

        setVerticalSpacing(dp);
        setHorizontalSpacing(dp);
        setPadding(dp, dp, dp, dp);

        setOnItemClickListener(this);
    }

    public SingleGridView<DATA> adapter(BaseBaseAdapter<DATA> adapter) {
        this.mAdapter = adapter;
        setAdapter(adapter);
        return this;
    }

    public SingleGridView<DATA> onItemClick(OnFilterItemClickListener<DATA> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }


    public void setList(List<DATA> list, int checkedPositoin) {
        mAdapter.setList(list);

        if (checkedPositoin != -1) {
            setItemChecked(checkedPositoin, true);
        }
    }

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
