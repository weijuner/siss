package com.srba.siss.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.srba.siss.R;
import com.srba.siss.entity.FilterType;
import com.srba.siss.entity.FilterUrl;
import com.srba.siss.filter.adapter.SimpleTextAdapter;
import com.srba.siss.filter.interfaces.OnFilterDoneListener;
import com.srba.siss.filter.interfaces.OnFilterItemClickListener;
import com.srba.siss.filter.typeview.DoubleListView;
import com.srba.siss.filter.typeview.SingleListView;
import com.srba.siss.filter.util.CommonUtil;
import com.srba.siss.filter.util.SimpleAnimationListener;
import com.srba.siss.filter.util.UIUtil;
import com.srba.siss.filter.view.FilterCheckedTextView;
import com.srba.siss.listener.OnSingleListPopClickListener;
import com.srba.siss.util.Timber;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/12/26 11:31
 * 描述:	底部弹出单列表选择菜单
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/12/26       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class PopupWindowSingleList extends PopupWindow {


    private View mMenuView;
    private OnSingleListPopClickListener onFilterDoneListener;


    public PopupWindowSingleList(Context context, List<String> list, OnSingleListPopClickListener onFilterDoneListener) {
        this(context, null, list);
        this.onFilterDoneListener = onFilterDoneListener;

    }



    public PopupWindowSingleList(Context context, AttributeSet attrs, List<String> list) {
        super(context, attrs);

        mMenuView = createSingleListView(list, context);




        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.tv_title).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                        onFilterDoneListener.onOutClick();
                    }
                }
                return true;
            }
        });
    }


    /**
     * 创建单独的列表
     *
     * @return
     */
    private View createSingleListView(List<String> list, final Context mContext) {
        BottomListView<String> singleListView = new BottomListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        Timber.e("onItemClick");
                        if (onFilterDoneListener != null) {
                            onFilterDoneListener.onPopWindowClick(item);
                        }

                    }
                });

        singleListView.setList(list, -1);
        return singleListView;
    }
}
