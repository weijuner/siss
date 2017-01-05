package com.srba.siss.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.srba.siss.R;
import com.srba.siss.widget.timeline.TimelineView;


/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  时间线viewholder
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tx_name);
        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
        mTimelineView.initLine(viewType);
    }
}
