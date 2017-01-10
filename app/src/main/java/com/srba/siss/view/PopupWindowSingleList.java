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
import com.srba.siss.filter.util.UIUtil;
import com.srba.siss.filter.view.FilterCheckedTextView;
import com.srba.siss.util.Timber;

import java.util.ArrayList;
import java.util.List;

public class PopupWindowSingleList extends PopupWindow {

	private View mMenuView;
	private OnFilterDoneListener onFilterDoneListener;
	public PopupWindowSingleList( Context context,List<String> list,OnFilterDoneListener onFilterDoneListener) {
		this(context,null,list);
		this.onFilterDoneListener = onFilterDoneListener;
	}

	public PopupWindowSingleList(Context context, AttributeSet attrs,List<String> list) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popup_window_area, null);
		// 设置SelectPicPopupWindow的View
		this.setContentView(createSingleListView(list,context));
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(800);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimBottom);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

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
	 * 创建单独的列表
	 * @return
	 */
	private View createSingleListView(List<String> list, final Context mContext) {
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
						Timber.e("onItemClick");
						if (onFilterDoneListener != null) {
							onFilterDoneListener.onFilterDone(0, "", "");
						}

					}
				});

		singleListView.setList(list, -1);
		return singleListView;
	}
	public void setOnInnerItemClickListener(OnClickListener l) {
		// 设置按钮监听

	}
}
