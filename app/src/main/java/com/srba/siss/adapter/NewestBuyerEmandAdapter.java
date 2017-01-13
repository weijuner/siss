package com.srba.siss.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.srba.siss.R;
import com.srba.siss.bean.HouseResource;

import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/12/21 17:19
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/12/21       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */

public class NewestBuyerEmandAdapter extends RecyclerView.Adapter<NewestBuyerEmandAdapter.ViewHolder> {
    // 数据集
    private List<HouseResource> mHouses;

    public NewestBuyerEmandAdapter(List<HouseResource> houses) {
        super();
        mHouses = houses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView

        View view = View.inflate(viewGroup.getContext(), R.layout.item_newesthouse, null);

        // 创建一个ViewHolder

        ViewHolder holder = new ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        // 绑定数据到ViewHolder上

        viewHolder.tv_neighbourhood.setText(mHouses.get(i).getNeighbourhood());
        viewHolder.tv_houseType.setText(mHouses.get(i).getRoom()+"室"+mHouses.get(i).getHall()+"厅");
        viewHolder.tv_price.setText(mHouses.get(i).getPrice()+"万");
        viewHolder.tv_area.setText(mHouses.get(i).getArea()+"m²");
        viewHolder.tv_region.setText(mHouses.get(i).getRegion()+"");

    }

    @Override
    public int getItemCount() {

        return mHouses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_region;
        public TextView tv_houseType;
        public TextView tv_neighbourhood;
        public TextView tv_price;
        public TextView tv_area;

        public ViewHolder(View itemView) {

            super(itemView);

            tv_region = (TextView) itemView.findViewById(R.id.tv_region);
            tv_houseType = (TextView) itemView.findViewById(R.id.tv_houseType);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_area = (TextView) itemView.findViewById(R.id.tv_area);
            tv_neighbourhood = (TextView) itemView.findViewById(R.id.tv_neighbourhood);
        }

    }

}
