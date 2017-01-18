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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.srba.siss.R;
import com.srba.siss.entity.FilterType;
import com.srba.siss.entity.FilterUrl;
import com.srba.siss.filter.adapter.SimpleTextAdapter;
import com.srba.siss.filter.interfaces.OnFilterDoneListener;
import com.srba.siss.filter.typeview.DoubleAreaListView;
import com.srba.siss.filter.typeview.DoubleListView;
import com.srba.siss.filter.util.CommonUtil;
import com.srba.siss.filter.util.SimpleAnimationListener;
import com.srba.siss.filter.util.UIUtil;
import com.srba.siss.filter.view.FilterCheckedTextView;
import com.srba.siss.listener.OnAreaPopClickListener;
import com.srba.siss.util.Timber;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/12/26 11:31
 * 描述:	底部弹出区域选择菜单
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/12/26       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class PopupWindowArea extends PopupWindow {
    /**
     * 总布局
     */
    private FrameLayout frameLayoutContainer;
    /**
     * 列表布局
     */
    private View mMenuView;
    /**
     * 点击监听
     */
    private OnAreaPopClickListener onAreaPopClickListener;

    public PopupWindowArea(OnAreaPopClickListener onAreaPopClickListener, Context context) {
        this(context, null);
        this.onAreaPopClickListener = onAreaPopClickListener;

    }


    public PopupWindowArea(Context context, AttributeSet attrs) {
        super(context, attrs);

        mMenuView = createDoubleListView(context);
           /*
         * 3.添加展开页面,装载筛选器list
         */
   /*     FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        frameLayoutContainer = new FrameLayout(context);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //	frameLayoutContainer.setBackground(context.getResources().getColor(R.color.black_p50));
        frameLayoutContainer.setBackgroundDrawable(dw);
        frameLayoutContainer.addView(mMenuView, params);*/


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
        setTouchable(true);
        setFocusable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                Timber.e("onTouch");
                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {

                        dismiss();

                    }
                }
                return true;
            }
        });
    }

    /**
     * 创建双列listview
     *
     * @return
     */
    private View createDoubleListView(final Context mContext) {
        DoubleAreaListView<FilterType, String> comTypeDoubleListView = new DoubleAreaListView<FilterType, String>(mContext)
                .leftAdapter(new SimpleTextAdapter<FilterType>(null, mContext) {
                    @Override
                    public String provideText(FilterType filterType) {
                        return filterType.desc;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 44), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                    }
                })
                .rightAdapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 30), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                        //		checkedTextView.setBackgroundResource(android.R.color.white);
                    }
                })
                .onLeftItemClickListener(new DoubleAreaListView.OnLeftItemClickListener<FilterType, String>() {
                    @Override
                    public List<String> provideRightList(FilterType item, int position) {
                        List<String> child = item.child;
                        if (CommonUtil.isEmpty(child)) {
                            if (onAreaPopClickListener != null) {
                                onAreaPopClickListener.onPopWindowClick("不限", "");
                            }
                        }
                        Timber.e("onLeftItemClickListener");
                        return child;
                    }
                })
                .onRightItemClickListener(new DoubleAreaListView.OnRightItemClickListener<FilterType, String>() {
                    @Override
                    public void onRightItemClick(FilterType item, String string) {
                        Timber.e("onRightItemClickListener");
                        if (onAreaPopClickListener != null) {
                            onAreaPopClickListener.onPopWindowClick(item.desc, string);
                        }
                    }
                });


        List<FilterType> list = new ArrayList<>();

        //第一项
        FilterType filterType = new FilterType();
        filterType.desc = "不限";
        list.add(filterType);

        //第二项
        filterType = new FilterType();
        filterType.desc = "罗湖";
        List<String> childList = new ArrayList<>();
        childList.add("不限");
        childList.add("百仕达");
        childList.add("布心");
        childList.add("春风路");
        childList.add("翠竹");
        childList.add("地王");
        childList.add("东门");
        childList.add("洪湖");
        childList.add("黄贝岭");
        childList.add("莲塘");
        childList.add("罗湖口岸");
        childList.add("清水河");
        childList.add("笋岗");
        childList.add("万象城");
        childList.add("新秀");
        childList.add("银湖");

        filterType.child = childList;
        list.add(filterType);

        //第三项
        filterType = new FilterType();
        filterType.desc = "福田";
        childList = new ArrayList<>();
        childList.add("不限");
        childList.add("华强南");
        childList.add("华侨城");
        childList.add("景田");
        childList.add("莲花");
        childList.add("梅林");
        childList.add("上步");
        childList.add("上下沙");
        childList.add("石厦");
        childList.add("香蜜湖");
        childList.add("新洲");
        childList.add("银湖");
        childList.add("园岭");
        childList.add("竹子林");

        filterType.child = childList;
        list.add(filterType);

        //初始化选中.
        comTypeDoubleListView.setLeftList(list, 0);
        comTypeDoubleListView.setRightList(list.get(0).child, -1);
        comTypeDoubleListView.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.b_c_fafafa));

        return comTypeDoubleListView;
    }
}
