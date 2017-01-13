package com.srba.siss.filter.typeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.srba.siss.R;
import com.srba.siss.filter.adapter.BaseBaseAdapter;
import com.srba.siss.filter.adapter.SimpleTextAdapter;
import com.srba.siss.filter.util.CommonUtil;

import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  双列listview
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class DoubleAreaListView<LEFTD, RIGHTD> extends LinearLayout implements AdapterView.OnItemClickListener {

    private BaseBaseAdapter<LEFTD> mLeftAdapter;
    private BaseBaseAdapter<RIGHTD> mRightAdapter;
    private ListView lv_left;
    private ListView lv_right;

    public DoubleAreaListView(Context context) {
        this(context, null);
    }

    public DoubleAreaListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DoubleAreaListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.merge_filter_list, this);

        lv_left = (ListView) findViewById(R.id.lv_left);
        lv_right = (ListView) findViewById(R.id.lv_right);
        lv_left.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv_right.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv_left.setOnItemClickListener(this);
        lv_right.setOnItemClickListener(this);
    }


    public DoubleAreaListView<LEFTD, RIGHTD> leftAdapter(SimpleTextAdapter<LEFTD> leftAdapter) {
        mLeftAdapter = leftAdapter;
        lv_left.setAdapter(leftAdapter);
        return this;
    }

    public DoubleAreaListView<LEFTD, RIGHTD> rightAdapter(SimpleTextAdapter<RIGHTD> rightAdapter) {
        mRightAdapter = rightAdapter;
        lv_right.setAdapter(rightAdapter);
        return this;
    }

    public void setLeftList(List<LEFTD> list, int checkedPosition) {
        mLeftAdapter.setList(list);

        if (checkedPosition != -1) {
//            lv_left.performItemClick(mLeftAdapter.getView(checkedPositoin, null, null), checkedPositoin, mLeftAdapter.getItemId(checkedPositoin));//调用此方法相当于点击.第一次进来时会触发重复加载.
            lv_left.setItemChecked(checkedPosition, true);
        }
    }

    public void setRightList(List<RIGHTD> list, int checkedPosition) {
        mRightAdapter.setList(list);
        if (checkedPosition != -1) {
            lv_right.setItemChecked(checkedPosition, true);
        }
    }

    private OnLeftItemClickListener<LEFTD, RIGHTD> mOnLeftItemClickListener;
    private OnRightItemClickListener<LEFTD, RIGHTD> mOnRightItemClickListener;

    public interface OnLeftItemClickListener<LEFTD, RIGHTD> {
        List<RIGHTD> provideRightList(LEFTD leftAdapter, int position);
    }

    public interface OnRightItemClickListener<LEFTD, RIGHTD> {
        void onRightItemClick(LEFTD item, RIGHTD childItem);
    }

    public DoubleAreaListView<LEFTD, RIGHTD> onLeftItemClickListener(OnLeftItemClickListener<LEFTD, RIGHTD> onLeftItemClickListener) {
        this.mOnLeftItemClickListener = onLeftItemClickListener;
        return this;
    }

    public DoubleAreaListView<LEFTD, RIGHTD> onRightItemClickListener(OnRightItemClickListener<LEFTD, RIGHTD> onRightItemClickListener) {
        this.mOnRightItemClickListener = onRightItemClickListener;
        return this;
    }

    public ListView getLeftListView() {
        return lv_left;
    }

    public ListView getRightListView() {
        return lv_right;
    }

    //========================点击事件===================================
    private int mRightLastChecked;
    private int mLeftLastPosition;
    private int mLeftLastCheckedPosition;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (CommonUtil.isFastDoubleClick()) {
            return;
        }

        if (mLeftAdapter == null || mRightAdapter == null) {
            return;
        }

        if (parent == lv_left) {
            mLeftLastPosition = position;

            if (mOnLeftItemClickListener != null) {
                LEFTD item = mLeftAdapter.getItem(position);

                List<RIGHTD> rightds = mOnLeftItemClickListener.provideRightList(item, position);
                mRightAdapter.setList(rightds);

                if (CommonUtil.isEmpty(rightds)) {
                    //当前点的就是这个条目
                    mLeftLastCheckedPosition = -1;
                }
            }

            lv_right.setItemChecked(mRightLastChecked, mLeftLastCheckedPosition == position);
        } else {
            mLeftLastCheckedPosition = mLeftLastPosition;
            mRightLastChecked = position;

            if (mOnRightItemClickListener != null) {
                mOnRightItemClickListener.onRightItemClick(mLeftAdapter.getItem(mLeftLastCheckedPosition), mRightAdapter.getItem(mRightLastChecked));
            }
        }
    }


}
