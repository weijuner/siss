package provider;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hyphenate.exceptions.HyphenateException;
import com.srba.siss.widget.EaseChatRowShare;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/12/26 11:44
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/12/26       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class CustomChatRowProvider implements EaseCustomChatRowProvider {

    private static final int MESSAGE_TYPE_SENDSHAREPIC = 1;//发送
    private static final int MESSAGE_TYPE_RECEIVEDSHAREPIC = 2;//接收

    private Context mContext;

    public CustomChatRowProvider(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 创建自定义的布局类型数量（返回值必须是类型的数量*2.包括接收布局和发送布局）
     * @return
     */
    @Override
    public int getCustomChatRowTypeCount() {
        return 2;
    }

    /**
     * 根据message的接收和发送  返回自定义的布局类型
     * @param message
     * @return
     */
    @Override
    public int getCustomChatRowType(EMMessage message) {
        //这里做个判断    如果能取到扩展字段    就返回消息类型
        try {
            message.getStringAttribute("title");
            return message.direct() == EMMessage.Direct.RECEIVE ?MESSAGE_TYPE_RECEIVEDSHAREPIC:MESSAGE_TYPE_SENDSHAREPIC;
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 返回chatrow   每种文件的类型（图片、音频）  这里返回自定义的分享布局
     * @param message
     * @param position
     * @param adapter
     * @return
     */
    @Override
    public EaseChatRow getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
        //同上    如果能取到扩展字段    就返回消息类型
        try {
            message.getStringAttribute("title");
            return new EaseChatRowShare(mContext, message, position, adapter);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
