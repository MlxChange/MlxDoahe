package com.example.mlx.daohe.entiy;

import android.text.TextUtils;

import com.example.mlx.daohe.Utils.StaticClass;

import org.json.JSONObject;

import cn.bmob.newim.bean.BmobIMExtraMessage;
import cn.bmob.newim.bean.BmobIMMessage;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/21 20:42
 * 用途：添加好友邀请message
 */

public class AddFriendMessage extends BmobIMExtraMessage {

    public AddFriendMessage(){}

    @Override
    public String getMsgType() {
        //自定义一个`add`的消息类型
        return "add";
    }

    public static NewFriend convert(BmobIMMessage msg){
        NewFriend add =new NewFriend();
        String content = msg.getContent();
        add.setMsg(content);
        add.setTime(msg.getCreateTime());
        add.setStatus(StaticClass.STATUS_VERIFY_NONE);
        try {
            String extra = msg.getExtra();
            if(!TextUtils.isEmpty(extra)){
                JSONObject json =new JSONObject(extra);
                String name = json.getString("name");
                add.setName(name);
                String avatar = json.getString("avatar");
                add.setAvatar(avatar);
                add.setUid(json.getString("uid"));
            }else{
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add;
    }

    @Override
    public boolean isTransient() {
        //设置为true,表明为暂态消息，那么这条消息并不会保存到本地db中，SDK只负责发送出去
        //设置为false,则会保存到指定会话的数据库中
        return true;
    }

}
