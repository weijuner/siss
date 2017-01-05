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
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.srba.siss.R;

public class BottomPopupWindow extends PopupWindow {

	private LinearLayout menu1, menu2, menu3, menu4;
	private View mMenuView;

	public BottomPopupWindow(Context context) {
		this(context, null);
	}

	public BottomPopupWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.bottom_popup_window, null);
		menu1 = (LinearLayout) mMenuView.findViewById(R.id.ll_add_houseres);
		menu2 = (LinearLayout) mMenuView.findViewById(R.id.ll_add_buyer);
		menu3 = (LinearLayout) mMenuView.findViewById(R.id.ll_sell_cooperation);
		menu4 = (LinearLayout) mMenuView.findViewById(R.id.ll_buy_cooperation);

		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
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

	public void setOnInnerItemClickListener(OnClickListener l) {
		// 设置按钮监听
		menu1.setOnClickListener(l);
		menu2.setOnClickListener(l);
		menu3.setOnClickListener(l);
		menu4.setOnClickListener(l);
	}
}
