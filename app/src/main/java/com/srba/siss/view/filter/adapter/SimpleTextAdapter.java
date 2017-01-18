package com.srba.siss.view.filter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srba.siss.R;
import com.srba.siss.view.filter.util.UIUtil;
import com.srba.siss.view.filter.view.FilterCheckedTextView;

import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  菜单适配器
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public abstract class SimpleTextAdapter<T> extends BaseBaseAdapter<T> {

    private final LayoutInflater inflater;

    public SimpleTextAdapter(List<T> list, Context context) {
        super(list, context);
        inflater = LayoutInflater.from(context);
    }

    public static class FilterItemHolder {
        FilterCheckedTextView checkedTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FilterItemHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lv_item_filter, parent, false);

            holder = new FilterItemHolder();
            holder.checkedTextView = (FilterCheckedTextView) convertView;
            holder.checkedTextView.setPadding(0, UIUtil.dp(context, 15), 0, UIUtil.dp(context, 15));
            initCheckedTextView(holder.checkedTextView);

            convertView.setTag(holder);
        } else {
            holder = (FilterItemHolder) convertView.getTag();
        }

        T t = list.get(position);
        holder.checkedTextView.setText(provideText(t));

        return convertView;
    }

    public abstract String provideText(T t);

    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {

    }


}
