package com.srba.siss.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.srba.siss.R;
import com.srba.siss.entity.FilterType;
import com.srba.siss.entity.FilterUrl;
import com.srba.siss.filter.adapter.MenuAdapter;
import com.srba.siss.filter.adapter.SimpleTextAdapter;
import com.srba.siss.filter.interfaces.OnFilterDoneListener;
import com.srba.siss.filter.interfaces.OnFilterItemClickListener;
import com.srba.siss.filter.typeview.DoubleListView;
import com.srba.siss.filter.typeview.SingleGridView;
import com.srba.siss.filter.typeview.SingleListView;
import com.srba.siss.filter.util.CommonUtil;
import com.srba.siss.filter.util.UIUtil;
import com.srba.siss.filter.view.FilterCheckedTextView;
import com.srba.siss.view.betterDoubleGrid.BetterDoubleGridView;
import com.srba.siss.view.doubleGrid.DoubleGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  下拉菜单适配器
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;

    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 3) {
            return 0;
        }

        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 0:
                view = createDoubleListView();
                break;
            case 1:
                List<String> list = new ArrayList<>();
                list.add("不限");
                list.add("200万以下");
                list.add("200-300万");
                list.add("300-400万");
                list.add("400-600万");
                list.add("600-800万");
                list.add("800-1000万");
                list.add("1000万以上");
                view = createSingleListView(list,1);
                break;
            case 2:
                List<String> list2 = new ArrayList<>();
                list2.add("不限");
                list2.add("一室");
                list2.add("二室");
                list2.add("三室");
                list2.add("四室");
                list2.add("五室");
                list2.add("五室以上");
                view = createSingleListView(list2,2);
        //      view = createSingleGridView();
                break;
            case 3:
        //      view = createDoubleGrid();
                view = createBetterDoubleGrid();
                break;
        }

        return view;
    }

    /**
     * 创建单独的列表
     * @return
     */
    private View createSingleListView(List<String> list, final int position) {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
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
                        FilterUrl.instance().singleListPosition = item;

                        FilterUrl.instance().position = position;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone();
                    }
                });

        singleListView.setList(list, -1);
        return singleListView;
    }

    /**
     * 创建双列listview
     * @return
     */
    private View createDoubleListView() {
        DoubleListView<FilterType, String> comTypeDoubleListView = new DoubleListView<FilterType, String>(mContext)
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
                        checkedTextView.setBackgroundResource(android.R.color.white);
                    }
                })
                .onLeftItemClickListener(new DoubleListView.OnLeftItemClickListener<FilterType, String>() {
                    @Override
                    public List<String> provideRightList(FilterType item, int position) {
                        List<String> child = item.child;
                        if (CommonUtil.isEmpty(child)) {
                            FilterUrl.instance().doubleListLeft = item.desc;
                            FilterUrl.instance().doubleListRight = "";

                            FilterUrl.instance().position = 0;
                           // FilterUrl.instance().positionTitle = item.desc;
                            FilterUrl.instance().positionTitle = "区域";

                            onFilterDone();
                        }

                        return child;
                    }
                })
                .onRightItemClickListener(new DoubleListView.OnRightItemClickListener<FilterType, String>() {
                    @Override
                    public void onRightItemClick(FilterType item, String string) {
                        FilterUrl.instance().doubleListLeft = item.desc;
                        FilterUrl.instance().doubleListRight = string;

                        FilterUrl.instance().position = 0;
                        FilterUrl.instance().positionTitle = string;

                        onFilterDone();
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
        comTypeDoubleListView.setLeftList(list, 1);
        comTypeDoubleListView.setRightList(list.get(1).child, -1);
        comTypeDoubleListView.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.b_c_fafafa));

        return comTypeDoubleListView;
    }


    private View createSingleGridView() {
        SingleGridView<String> singleGridView = new SingleGridView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(0, UIUtil.dp(context, 3), 0, UIUtil.dp(context, 3));
                        checkedTextView.setGravity(Gravity.CENTER);
                        checkedTextView.setBackgroundResource(R.drawable.selector_filter_grid);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleGridPosition = item;

                        FilterUrl.instance().position = 2;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone();

                    }
                });

        List<String> list = new ArrayList<>();
        for (int i = 20; i < 39; ++i) {
            list.add(String.valueOf(i));
        }
        singleGridView.setList(list, -1);


        return singleGridView;
    }


    private View createBetterDoubleGrid() {

        List<String> phases = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            phases.add("3top" + i);
        }
        List<String> areas = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            areas.add("3bottom" + i);
        }


        return new BetterDoubleGridView(mContext)
                .setmTopGridData(phases)
                .setmBottomGridList(areas)
                .setOnFilterDoneListener(onFilterDoneListener)
                .build();
    }


    private View createDoubleGrid() {
        DoubleGridView doubleGridView = new DoubleGridView(mContext);
        doubleGridView.setOnFilterDoneListener(onFilterDoneListener);


        List<String> phases = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            phases.add("3top" + i);
        }
        doubleGridView.setTopGridData(phases);

        List<String> areas = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            areas.add("3bottom" + i);
        }
        doubleGridView.setBottomGridList(areas);

        return doubleGridView;
    }


    private void onFilterDone() {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(0, "", "");
        }
    }

}
