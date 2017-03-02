package com.example.mlx.daohe.entiy;

import android.text.TextUtils;

import org.json.JSONObject;

import cn.bmob.newim.bean.BmobIMExtraMessage;
import cn.bmob.newim.bean.BmobIMMessage;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/21 21:45
 * 用途：
 */

public class AgreeAddFriendMessage extends BmobIMExtraMessage {

    //以下均是从extra里面抽离出来的字段，方便获取
    private String uid;//最初的发送方
    private Long time;
    private String msg;//用于通知栏显示的内容

    public AgreeAddFriendMessage(){}

    /**
     * 继承BmobIMMessage的属性
     * @param msg
     */
    public AgreeAddFriendMessage(BmobIMMessage msg){
        super.parse(msg);
    }

    @Override
    public String getMsgType() {
        return "agree";
    }

    @Override
    public boolean isTransient() {
        //如果需要在对方的会话表中新增一条该类型的消息，则设置为false，表明是非暂态会话
        //此处将同意添加好友的请求设置为false，为了演示怎样向会话表和消息表中新增一个类型，在对方的会话列表中增加`我通过了你的好友验证请求，我们可以开始聊天了!`这样的类型
        return false;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**将BmobIMMessage转成AgreeAddFriendMessage
     * @param msg 消息
     * @return
     */
    public static AgreeAddFriendMessage convert(BmobIMMessage msg){
        AgreeAddFriendMessage agree =new AgreeAddFriendMessage(msg);
        try {
            String extra = msg.getExtra();
            if(!TextUtils.isEmpty(extra)){
                JSONObject json =new JSONObject(extra);
                Long time = json.getLong("time");
                String uid =json.getString("uid");
                String m =json.getString("msg");
                agree.setMsg(m);
                agree.setUid(uid);
                agree.setTime(time);
            }else{
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agree;
    }
}
