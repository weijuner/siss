package com.srba.siss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.srba.siss.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/12/22 11:09
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/12/22       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */

public class MessageListAdapter extends BaseExpandableListAdapter {
    private List<String> groupArray;
    private List<List<String>> childArray;
    private Context mContext;

    public MessageListAdapter(Context context, List<String> groupArray, List<List<String>> childArray){
        mContext = context;
        this.groupArray = groupArray;
        this.childArray = childArray;
    }

    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        GroupHolder holder = null;
        if(view == null){
            holder = new GroupHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.expandlist_group, null);
            holder.groupName = (TextView)view.findViewById(R.id.tv_group_name);
            holder.number = (TextView) view.findViewById(R.id.iv_arrow);
            view.setTag(holder);
        }else{
            holder = (GroupHolder)view.getTag();
        }

        holder.groupName.setText(groupArray.get(groupPosition));

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder holder = null;
        if(view == null){
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.expandlist_item, null);
            holder.childName = (TextView)view.findViewById(R.id.tv_child_name);
            holder.sound = (CircleImageView)view.findViewById(R.id.iv_avatar);
            holder.divider = (ImageView)view.findViewById(R.id.iv_divider);
            view.setTag(holder);
        }else{
            holder = (ChildHolder)view.getTag();
        }

        if(childPosition == 0){
            holder.divider.setVisibility(View.GONE);
        }

        holder.sound.setBackgroundResource(R.drawable.ic_launcher);
        holder.childName.setText(childArray.get(groupPosition).get(childPosition));

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder{
        public TextView groupName;
        public TextView number;
    }

    class ChildHolder{
        public TextView childName;
        public CircleImageView sound;
        public ImageView divider;
    }

}