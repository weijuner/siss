package com.srba.siss;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;


import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.hyphenate.chat.EMMessage.Type;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.easeui.model.EaseNotifier.EaseNotificationInfoProvider;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.util.EMLog;
import com.srba.siss.ui.activity.ChatActivity;

import java.util.List;
import java.util.Map;

import static com.hyphenate.easeui.utils.EaseUserUtils.getUserInfo;
/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/12/26 11:44
 * 描述:  环信消息帮助类
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/12/26       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class MessageHelper {


    protected static final String TAG = "MessageHelper";
    /**
     * ui管理
     */
	private EaseUI easeUI;
    /**
     * 消息监听
     */
    protected EMMessageListener messageListener = null;


	private static MessageHelper instance = null;

	private String username;
    /**
     * 上下文
     */
    private Context appContext;




	private MessageHelper() {
	}

	public synchronized static MessageHelper getInstance() {
		if (instance == null) {
			instance = new MessageHelper();
		}
		return instance;
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 *
	 */
	public void init(Context context) {

	    EMOptions options = initChatOptions();
	    //options为空就使用默认的
		if (EaseUI.getInstance().init(context, options)) {
		    appContext = context;


		    //debug mode, you'd better set it to false, if you want release your App officially.
		    EMClient.getInstance().setDebugMode(true);

		    //获取ui实例
		    easeUI = EaseUI.getInstance();
            //设置自己的内容提供者
		    setEaseUIProviders();

			//initialize profile manager
	//		getUserProfileManager().init(context);

            setGlobalListeners();;
		}
	}

	
	private EMOptions initChatOptions(){
        Log.d(TAG, "init HuanXin Options");
        
        EMOptions options = new EMOptions();
        // 设置自动登录
        options.setAutoLogin(true);
        // 设置是否需要发送已读回执
        options.setRequireAck(true);
        // 设置是否需要发送回执，
        options.setRequireDeliveryAck(true);
        // 设置是否需要服务器收到消息确认
        options.setRequireServerAck(true);
        // 设置是否根据服务器时间排序，默认是true
        options.setSortMessageByServerTime(false);
        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
        options.setAcceptInvitationAlways(false);
        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
        options.allowChatroomOwnerLeave(true);



        //you need apply & set your own id if you want to use Mi push notification
       // options.setMipushConfig("2882303761517426801", "5381742660801");
        //you need apply & set your own id if you want to use Huawei push notification
    //    options.setHuaweiPushAppId("10492024");

        return options;
    }
    EMConnectionListener connectionListener;

    /**
     * 设置全局的监听
     */
    protected void setGlobalListeners(){


        //register connection listener
     //   EMClient.getInstance().addConnectionListener(connectionListener);

        //register message event listener
        registerMessageListener();

    }

    /**
     * 设置自己的内容提供者
     */
    protected void setEaseUIProviders() {
    	// set profile provider if you want easeUI to handle avatar and nickname
     /*   easeUI.setUserProfileProvider(new EaseUserProfileProvider() {
            
            @Override
            public EaseUser getUser(String username) {
                return getUserInfo(username);
            }
        });*/

      /*  //set options
        easeUI.setSettingsProvider(new EaseSettingsProvider() {
            
            @Override
            public boolean isSpeakerOpened() {
                return demoModel.getSettingMsgSpeaker();
            }
            
            @Override
            public boolean isMsgVibrateAllowed(EMMessage message) {
                return demoModel.getSettingMsgVibrate();
            }
            
            @Override
            public boolean isMsgSoundAllowed(EMMessage message) {
                return demoModel.getSettingMsgSound();
            }
            
            @Override
            public boolean isMsgNotifyAllowed(EMMessage message) {
                if(message == null){
                    return demoModel.getSettingMsgNotification();
                }
                if(!demoModel.getSettingMsgNotification()){
                    return false;
                }else{
                    String chatUsename = null;
                    List<String> notNotifyIds = null;
                    // get user or group id which was blocked to show message notifications
                    if (message.getChatType() == ChatType.Chat) {
                        chatUsename = message.getFrom();
                        notNotifyIds = demoModel.getDisabledIds();
                    } else {
                        chatUsename = message.getTo();
                        notNotifyIds = demoModel.getDisabledGroups();
                    }

                    if (notNotifyIds == null || !notNotifyIds.contains(chatUsename)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        });*/
        //set emoji icon provider
     /*   easeUI.setEmojiconInfoProvider(new EaseEmojiconInfoProvider() {
            
            @Override
            public EaseEmojicon getEmojiconInfo(String emojiconIdentityCode) {
                EaseEmojiconGroupEntity data = EmojiconExampleGroupData.getData();
                for(EaseEmojicon emojicon : data.getEmojiconList()){
                    if(emojicon.getIdentityCode().equals(emojiconIdentityCode)){
                        return emojicon;
                    }
                }
                return null;
            }

            @Override
            public Map<String, Object> getTextEmojiconMapping() {
                return null;
            }
        });*/
        
        //set notification options, will use default if you don't set it
        easeUI.getNotifier().setNotificationInfoProvider(new EaseNotificationInfoProvider() {
            
            @Override
            public String getTitle(EMMessage message) {
              //you can update title here
                return null;
            }
            
            @Override
            public int getSmallIcon(EMMessage message) {
              //you can update icon here
                return 0;
            }
            
            @Override
            public String getDisplayedText(EMMessage message) {
            	// be used on notification bar, different text according the message type.
                String ticker = EaseCommonUtils.getMessageDigest(message, appContext);
                if(message.getType() == Type.TXT){
                    ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
                }
                EaseUser user = getUserInfo(message.getFrom());
                if(user != null){
                    if(EaseAtMessageHelper.get().isAtMeMsg(message)){
                        return String.format(appContext.getString(R.string.at_your_in_group), user.getNick());
                    }
                    return user.getNick() + ": " + ticker;
                }else{
                    if(EaseAtMessageHelper.get().isAtMeMsg(message)){
                        return String.format(appContext.getString(R.string.at_your_in_group), message.getFrom());
                    }
                    return message.getFrom() + ": " + ticker;
                }
            }
            
            @Override
            public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
                // here you can customize the text.
                // return fromUsersNum + "contacts send " + messageNum + "messages to you";
            	return null;
            }
            
            @Override
            public Intent getLaunchIntent(EMMessage message) {
            	// you can set what activity you want display when user click the notification
                Intent intent = new Intent(appContext, ChatActivity.class);
                // open calling activity if there is call
                if(false){
                  //  intent = new Intent(appContext, VideoCallActivity.class);
                }else if(false){
               //     intent = new Intent(appContext, VoiceCallActivity.class);
                }else{
                    ChatType chatType = message.getChatType();
                    if (chatType == ChatType.Chat) { // single chat message
                        intent.putExtra("userId", message.getFrom());
                        intent.putExtra("chatType", Constant.CHATTYPE_SINGLE);
                    } else { // group chat message
                        // message.getTo() is the group id
                        intent.putExtra("userId", message.getTo());
                        if(chatType == ChatType.GroupChat){
                            intent.putExtra("chatType", Constant.CHATTYPE_GROUP);
                        }else{
                            intent.putExtra("chatType", Constant.CHATTYPE_CHATROOM);
                        }
                        
                    }
                }
                return intent;
            }
        });
    }




	
	 /**
     * 注册全局监听
     * If this event already handled by an activity, you don't need handle it again
     * activityList.size() <= 0 means all activities already in background or not in Activity Stack
     */
    protected void registerMessageListener() {
    	messageListener = new EMMessageListener() {
            private BroadcastReceiver broadCastReceiver = null;

			@Override
			public void onMessageReceived(List<EMMessage> messages) {
			    for (EMMessage message : messages) {
			        EMLog.d(TAG, "onMessageReceived id : " + message.getMsgId());
			        // in background, do not refresh UI, notify it in notification bar
			        if(!easeUI.hasForegroundActivies()){
			            getNotifier().onNewMsg(message);
			        }
			    }
			}
			
			@Override
			public void onCmdMessageReceived(List<EMMessage> messages) {
			    for (EMMessage message : messages) {
                    EMLog.d(TAG, "receive command message");
                    //get message body
                    EMCmdMessageBody cmdMsgBody = (EMCmdMessageBody) message.getBody();
                    final String action = cmdMsgBody.action();//获取自定义action
                    //red packet code : 处理红包回执透传消息

                    if (action.equals("__Call_ReqP2P_ConferencePattern")) {
                        String title = message.getStringAttribute("em_apns_ext", "conference call");
                        Toast.makeText(appContext, title, Toast.LENGTH_LONG).show();
                    }
                    //end of red packet code
                    //获取扩展属性 此处省略
                    //maybe you need get extension of your message
                    //message.getStringAttribute("");
                    EMLog.d(TAG, String.format("Command：action:%s,message:%s", action,message.toString()));
                }
			}

			@Override
			public void onMessageReadAckReceived(List<EMMessage> messages) {
			}
			
			@Override
			public void onMessageDeliveryAckReceived(List<EMMessage> message) {
			}
			
			@Override
			public void onMessageChanged(EMMessage message, Object change) {
				
			}
		};
		
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

	/**
	 *是否登录过
	 * 
	 * @return
	 */
	public boolean isLoggedIn() {
		return EMClient.getInstance().isLoggedInBefore();
	}

	/**
	 * 登出
	 * 
	 * @param unbindDeviceToken
	 *            whether you need unbind your device token
	 * @param callback
	 *            callback
	 */
	public void logout(boolean unbindDeviceToken, final EMCallBack callback) {
		//endCall();
		Log.d(TAG, "logout: " + unbindDeviceToken);
		EMClient.getInstance().logout(unbindDeviceToken, new EMCallBack() {

			@Override
			public void onSuccess() {
				Log.d(TAG, "logout: onSuccess");
			//    reset();
				if (callback != null) {
					callback.onSuccess();
				}

			}

			@Override
			public void onProgress(int progress, String status) {
				if (callback != null) {
					callback.onProgress(progress, status);
				}
			}

			@Override
			public void onError(int code, String error) {
				Log.d(TAG, "logout: onSuccess");
        //        reset();
				if (callback != null) {
					callback.onError(code, error);
				}
			}
		});
	}
	
	/**
	 * 获取EaseNotifier的实例
	 * @return
	 */
	public EaseNotifier getNotifier(){
	    return easeUI.getNotifier();
	}
}
