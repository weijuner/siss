package com.srba.siss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.srba.siss.R;
import com.srba.siss.adapter.viewholder.TimeLineViewHolder;
import com.srba.siss.entity.TimeLineModel;
import com.srba.siss.widget.timeline.TimelineView;

import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  时间线适配器
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<TimeLineModel> mFeedList;
    private Context mContext;
    public TimeLineAdapter(List<TimeLineModel> feedList) {
        mFeedList = feedList;

    }

    @Override
    public int getItemViewType(int position) {
        if(position>7){
            return TimelineView.getTimeLineViewType(position, getItemCount(),TimelineView.LINE_TYPE_DASH);
        }else {
            return TimelineView.getTimeLineViewType(position, getItemCount(),TimelineView.LINE_TYPE_SOLID);
        }
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        View view;

        view = View.inflate(parent.getContext(), R.layout.item_timeline, null);

        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        TimeLineModel timeLineModel = mFeedList.get(position);

        holder.name.setText("name：" + timeLineModel.getName() + "    age：" + timeLineModel.getAge());


    /*    if(position>7){
            holder.mTimelineView.setLineType(TimelineView.LINE_TYPE_DASH);
        }else if(position <= 7){
            holder.mTimelineView.setLineType(TimelineView.LINE_TYPE_SOLID);
        }*/
    }

    @Override
    public int getItemCount() {
        return (mFeedList != null ? mFeedList.size() : 0);
    }

}
