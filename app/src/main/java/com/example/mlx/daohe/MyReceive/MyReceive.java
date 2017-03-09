package com.example.mlx.daohe.MyReceive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mlx.daohe.Acvitity.FindFriends;
import com.example.mlx.daohe.MainActivity;
import com.example.mlx.daohe.MessageEvent.MyEvent;
import com.example.mlx.daohe.Moudel.UserModel;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.db.NewFriendManager;
import com.example.mlx.daohe.entiy.AddFriendMessage;
import com.example.mlx.daohe.entiy.AgreeAddFriendMessage;
import com.example.mlx.daohe.entiy.DisArgeeAddFriendMessage;
import com.example.mlx.daohe.entiy.MyUser;
import com.example.mlx.daohe.entiy.NewFriend;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMMessageType;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;
import cn.bmob.newim.notification.BmobNotificationManager;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.MyReceive
 * 创建者：MLX
 * 创建时间：2017/2/21 19:38
 * 用途：用于接收服务器接受的消息
 */

public class MyReceive extends BmobIMMessageHandler {

    private Context context;

    public MyReceive(Context context) {
        this.context = context;
    }

    //当接收到服务器发来的消息时，此方法被调用
    @Override
    public void onMessageReceive(final MessageEvent event) {
        //UserModel.getInstance().updateUserInfo(event);
        exectume(event, false);

    }

    private void exectume(MessageEvent event, boolean isUN) {
        UtilS.updateUserInfo(event);
        BmobIMMessage msg = event.getMessage();
        if (BmobIMMessageType.getMessageTypeValue(msg.getMsgType()) == 0) {//用户自定义的消息类型，其类型值均为0
            processCustomMessage(msg, event.getFromUserInfo());
        } else {//SDK内部内部支持的消息类型
            if (BmobNotificationManager.getInstance(context).isShowNotification()) {//如果需要显示通知栏，SDK提供以下两种显示方式：
                Intent pendingIntent = new Intent(context, MainActivity.class);
                pendingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                //1、多个用户的多条消息合并成一条通知：有XX个联系人发来了XX条消息
                BmobNotificationManager.getInstance(context).showNotification(event, pendingIntent);
            } else {

                EventBus.getDefault().post(event);

            }
        }
    }

    /**
     * 处理自定义消息类型
     *
     * @param msg
     */
    private void processCustomMessage(BmobIMMessage msg, BmobIMUserInfo info) {
        //自行处理自定义消息类型
        String type = msg.getMsgType();
        EventBus.getDefault().post(new MyEvent());
        //处理消息
        if (type.equals("add")) {//接收到的添加好友的请求
            NewFriend friend = AddFriendMessage.convert(msg);
            //本地好友请求表做下校验，本地没有的才允许显示通知栏--有可能离线消息会有些重复
            boolean b = NewFriendManager.getInstance(context).isNewFriends(friend);
            if (b) {
                showAddNotify(friend);
                NewFriendManager.getInstance(context).insertNreFriends(friend);
            }
        } else if (type.equals("agree")) {//接收到的对方同意添加自己为好友,此时需要做的事情：1、添加对方为好友，2、显示通知
            AgreeAddFriendMessage agree = AgreeAddFriendMessage.convert(msg);
            addFriend(agree.getFromId());//添加消息的发送方为好友
            showAgreeNotify(info, agree);
            NewFriend friend = new NewFriend(agree.getFromId(), agree.getMsg(), info.getName(), info.getAvatar(), msg.getReceiveStatus(), msg.getUpdateTime());
            NewFriendManager.getInstance(context).updateNewFriend(friend, StaticClass.STATUS_VERIFIED);
        } else if (type.equals("disagree")) {
            DisArgeeAddFriendMessage disagree = DisArgeeAddFriendMessage.convert(msg);
            showdisAgreeNotify(info, disagree);
            NewFriend friend = new NewFriend(disagree.getFromId(), disagree.getMsg(), info.getName(), info.getAvatar(), msg.getReceiveStatus(), msg.getUpdateTime());
            NewFriendManager.getInstance(context).updateNewFriend(friend, StaticClass.STATUS_VERIFY_REFUSE);
        }
    }

    private void showAddNotify(NewFriend friend) {
        Intent pendingIntent = new Intent(context, FindFriends.class);
        pendingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        //这里可以是应用图标，也可以将聊天头像转成bitmap
        final Bitmap[] largetIcon = {null};
        Glide.with(context).load(friend.getAvatar()).asBitmap().error(R.drawable.error).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                largetIcon[0] = resource;
            }
        });
        BmobNotificationManager.getInstance(context).showNotification(largetIcon[0],
                friend.getName(), friend.getMsg(), friend.getName() + "请求添加你为朋友", pendingIntent);
    }

    /**
     * 显示对方同意添加自己为好友的通知
     *
     * @param info
     * @param agree
     */
    private void showAgreeNotify(BmobIMUserInfo info, AgreeAddFriendMessage agree) {
        Intent pendingIntent = new Intent(context, MainActivity.class);
        pendingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        //这里可以是应用图标，也可以将聊天头像转成bitmap
        final Bitmap[] largetIcon = {null};
        Glide.with(context).load(info.getAvatar()).asBitmap().error(R.drawable.error).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                largetIcon[0] = resource;
            }
        });
        BmobNotificationManager.getInstance(context).showNotification(largetIcon[0], info.getName(), agree.getMsg(), agree.getMsg(), pendingIntent);
    }

    /**
     * 显示对方拒绝添加自己为好友的通知
     *
     * @param info
     * @param agree
     */
    private void showdisAgreeNotify(BmobIMUserInfo info, DisArgeeAddFriendMessage agree) {
        Intent pendingIntent = new Intent(context, FindFriends.class);
        pendingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        //这里可以是应用图标，也可以将聊天头像转成bitmap
        final Bitmap[] largetIcon = {null};
        Glide.with(context).load(info.getAvatar()).asBitmap().error(R.drawable.error).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                largetIcon[0] = resource;
            }
        });
        BmobNotificationManager.getInstance(context).showNotification(largetIcon[0], info.getName(), agree.getMsg(), agree.getMsg(), pendingIntent);
    }

    @Override
    public void onOfflineReceive(final OfflineMessageEvent event) {
        //每次调用connect方法时会查询一次离线消息，如果有，此方法会被调用
        Map<String, List<MessageEvent>> map = event.getEventMap();
        //挨个检测下离线消息所属的用户的信息是否需要更新
        for (Map.Entry<String, List<MessageEvent>> entry : map.entrySet()) {
            List<MessageEvent> list = entry.getValue();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                exectume(list.get(i), true);
            }
        }
    }

    /**
     * 添加对方为自己的好友
     *
     * @param uid
     */
    private void addFriend(String uid) {
        MyUser user = new MyUser();
        user.setObjectId(uid);
        UserModel.getInstance().agreeAddFriend(user, new SaveListener() {
            @Override
            public void done(Object o, Object o2) {
            }

            @Override
            public void done(Object o, BmobException e) {
            }
        });
    }
}
