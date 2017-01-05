package com.srba.siss.view.doubleGrid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ScrollView;

import com.srba.siss.R;
import com.srba.siss.entity.FilterUrl;
import com.srba.siss.filter.adapter.SimpleTextAdapter;
import com.srba.siss.filter.interfaces.OnFilterDoneListener;
import com.srba.siss.filter.util.CommonUtil;
import com.srba.siss.filter.util.UIUtil;
import com.srba.siss.filter.view.FilterCheckedTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  两个gridview嵌套在listview里
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class DoubleGridView extends ScrollView implements View.OnClickListener {

    @BindView(R.id.grid_top)
    GridViewInScrollView mTopGrid;

    @BindView(R.id.grid_bottom)
    GridViewInScrollView mBottomGrid;

    @BindView(R.id.bt_confirm)
    Button bt_confirm;

    private SimpleTextAdapter<String> mTopAdapter;
    private SimpleTextAdapter<String> mBottomAdapter;

    private OnFilterDoneListener onFilterDoneListener;

    public DoubleGridView(Context context) {
        this(context, null);
    }

    public DoubleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.merge_filter_third, this);
        setBackgroundResource(android.R.color.white);

        ButterKnife.bind(this, this);

        bt_confirm.setOnClickListener(this);

        initAdapter(context);

        mTopGrid.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        mBottomGrid.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        mTopGrid.setAdapter(mTopAdapter);
        mBottomGrid.setAdapter(mBottomAdapter);
    }

    private void initAdapter(Context context) {

        mTopAdapter = new SimpleTextAdapter<String>(null, context) {
            @Override
            public String provideText(String phase) {
                return phase;
            }

            @Override
            protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                checkedTextView.setPadding(0, UIUtil.dp(context, 3), 0, UIUtil.dp(context, 3));
                checkedTextView.setGravity(Gravity.CENTER);
                checkedTextView.setBackgroundResource(R.drawable.selector_filter_grid);
            }
        };

        mBottomAdapter = new SimpleTextAdapter<String>(null, context) {
            @Override
            public String provideText(String area) {
                return area;
            }

            @Override
            protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                checkedTextView.setPadding(0, UIUtil.dp(context, 3), 0, UIUtil.dp(context, 3));
                checkedTextView.setBackgroundResource(R.drawable.selector_filter_grid);
                checkedTextView.setGravity(Gravity.CENTER);
            }
        };

        mTopGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    public void setTopGridData(List<String> list) {
        if (CommonUtil.isEmpty(list)) {
            return;
        }

        mTopAdapter.setList(list);
    }

    public void setBottomGridList(List<String> list) {
        if (CommonUtil.isEmpty(list)) {
            return;
        }

        mBottomAdapter.setList(list);
    }

    @Override
    public void onClick(View v) {
        int topPosition = mTopGrid.getCheckedItemPosition();
        topPosition = topPosition == -1 ? 0 : topPosition;

        int bottomPosition = mBottomGrid.getCheckedItemPosition();
        bottomPosition = bottomPosition == -1 ? 0 : bottomPosition;

        String financePhase = mTopAdapter.getItem(topPosition);
        String area = mBottomAdapter.getItem(bottomPosition);

        FilterUrl.instance().doubleGridTop = financePhase;
        FilterUrl.instance().doubleGridBottom = area;

        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(3, "", "");
        }
    }

    public void setOnFilterDoneListener(OnFilterDoneListener listener) {
        onFilterDoneListener = listener;
    }
}
