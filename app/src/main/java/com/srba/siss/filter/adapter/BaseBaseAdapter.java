package com.srba.siss.filter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  适配器基类
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public abstract class BaseBaseAdapter<T> extends BaseAdapter {

    public List<T> list;

    protected Context context;

    public BaseBaseAdapter(List<T> list, Context context) {
        super();
        setList(list);
        this.context = context;
    }

    /**
     * 设置数据。<BR>
     * 会清空原集合所有数据,后添加。
     *
     * @param list
     */
    public void setList(List<T> list) {

        if (list == null) {
            list = new ArrayList<T>(0);
        }

        this.list = list;

        notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }

    /**
     * 添加集合数据到原集合首位，下拉刷新时使用
     *
     * @param list
     */
    public void addToFirst(List<T> list) {
        if (list == null)
            return;
        this.list.addAll(0, list);
        notifyDataSetChanged();
    }

    public void addToFirst(T t) {
        if (t == null)
            return;
        this.list.add(0, t);
        notifyDataSetChanged();
    }

    /**
     * 添加数据到末尾，用于上拉加载等情况。<BR>
     * 不清楚原集合，添加到末尾。
     *
     * @param list
     */
    public void addToLast(List<T> list) {
        if (list == null)
            return;
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加元素到集合末尾
     *
     * @param t
     */
    public void addToLast(T t) {
        if (t == null)
            return;
        this.list.add(t);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView,
                                 ViewGroup parent);

}
