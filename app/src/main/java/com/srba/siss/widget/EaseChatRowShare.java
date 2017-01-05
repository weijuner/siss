package com.srba.siss.widget;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.exceptions.HyphenateException;
import com.srba.siss.R;
import com.srba.siss.util.ImageLoaderUtils;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/12/26 11:31
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/12/26       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class EaseChatRowShare extends EaseChatRow {

    ImageView img;//图片
    TextView title;//title

    public EaseChatRowShare(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    /**
     * 加载布局
     */
    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                R.layout.ease_row_received_share : R.layout.ease_row_sent_share, this);
    }

    /**
     * 实例化控件
     */
    @Override
    protected void onFindViewById() {
        title = (TextView) findViewById(R.id.tv_title_name);
        img = (ImageView) findViewById(R.id.ease_chat_item_share_img);
    }


    /**
     * 更新适配器
     */
    @Override
    protected void onUpdateView() {
        adapter.notifyDataSetChanged();
    }

    /**
     * 设置内容
     */
    @Override
    protected void onSetUpView() {
        try {
            String titleString = message.getStringAttribute("title");
            String iconString = message.getStringAttribute("icon");
//            String activityIDString = message.getStringAttribute("activityID");
            title.setText(titleString);
            ImageLoaderUtils.display(context,img,iconString);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onBubbleClick() {

    }
}