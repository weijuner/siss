package com.srba.siss.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srba.siss.R;
import com.srba.siss.adapter.NewestBuyerEmandAdapter;
import com.srba.siss.adapter.NewestHouseAdapter;
import com.srba.siss.base.BaseMvpFragment;
import com.srba.siss.bean.BuyerEmand;
import com.srba.siss.bean.HouseResource;
import com.srba.siss.mvp.buyeremand.BuyerEmandContract;
import com.srba.siss.mvp.buyeremand.BuyerEmandPresenter;
import com.srba.siss.mvp.houseresource.HouseContract;
import com.srba.siss.mvp.houseresource.HousePresenter;
import com.srba.siss.util.Timber;
import com.srba.siss.widget.ItemDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;


/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/12/21 17:18
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/12/21       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class NewestEmandFragment extends BaseMvpFragment<BuyerEmandPresenter> implements BuyerEmandContract.View, BGARefreshLayout.BGARefreshLayoutDelegate,HomeFragment.AppBarChangeListner {

    private int mPage;
    @BindView(R.id.rlv_newestemand)
    RecyclerView rlv_newestemand;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;

    public static boolean isCanRefresh = true;

    private NewestBuyerEmandAdapter mAdapter;
    private List<BuyerEmand> datas;
    public static NewestEmandFragment newInstance() {


        NewestEmandFragment pageFragment = new NewestEmandFragment();

        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newesemand, null);
        ButterKnife.bind(this, view);
        Timber.e("onCreateView");
        initData();
        initView();

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        datas = new ArrayList<>();
        mAdapter = new NewestBuyerEmandAdapter(datas);

    }

    private void initView() {
        // 创建一个线性布局管理器

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        // 设置布局管理器

        rlv_newestemand.setLayoutManager(layoutManager);
        //添加装饰类
        rlv_newestemand.addItemDecoration(new ItemDivider(getActivity(), R.drawable.divider_rvitem));
        rlv_newestemand.setAdapter(mAdapter);
        initRefresh();
        rlv_newestemand.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int state) {
                super.onScrollStateChanged(recyclerView, state);
                Timber.e("onScrollStateChanged"+state);
            }
        });
    }

    /**
     * 初始化刷新
     */
    private void initRefresh() {
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), false);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);


        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
         mRefreshLayout.setIsShowLoadingMoreView(true);
       // 设置正在加载更多时的文本
        refreshViewHolder.setLoadingMoreText("正在加载...");
  /*      // 设置整个加载更多控件的背景颜色资源 id
        refreshViewHolder.setLoadMoreBackgroundColorRes(loadMoreBackgroundColorRes);
        // 设置整个加载更多控件的背景 drawable 资源 id
        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes);
        // 设置下拉刷新控件的背景颜色资源 id
        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes);
        // 设置下拉刷新控件的背景 drawable 资源 id
        refreshViewHolder.setRefreshViewBackgroundDrawableRes(refreshViewBackgroundDrawableRes);
        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
        mRefreshLayout.setCustomHeaderView(mBanner, false);*/
        // 可选配置  -------------END
    }


    @Override
    public void onResume() {

       // mPresenter.getHouseInfo();
        super.onResume();
    }

    @Override
    protected BuyerEmandPresenter onCreatePresenter() {
        return new BuyerEmandPresenter(this);
    }



    @Override
    public void updateRecyclerView(List<BuyerEmand> modes) {
        mRefreshLayout.endRefreshing();
        datas.clear();
        datas.addAll(modes);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateFailure() {
        mRefreshLayout.endRefreshing();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.getAppBuyerEmand();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mRefreshLayout.endLoadingMore();
            }
        }).start();

        return true;
    }

    @Override
    public void onAppBarExpanded() {
        if(null != mRefreshLayout){
            mRefreshLayout.setPullDownRefreshEnable(true);
      //      mRefreshLayout.setIsShowLoadingMoreView(false);
        }
    }

    @Override
    public void onAppBarCollapsed() {
        if(null != mRefreshLayout){
            mRefreshLayout.setPullDownRefreshEnable(false);
       //     mRefreshLayout.setIsShowLoadingMoreView(true);
        }
    }
}