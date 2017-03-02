package com.example.mlx.daohe.Moudel;

import android.text.TextUtils;

import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.entiy.Friend;
import com.example.mlx.daohe.entiy.MyUser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import rx.Subscription;

import static android.R.attr.id;

/**
 * @author :smile
 * @project:UserModel
 * @date :2016-01-22-18:09
 */
public class UserModel extends BaseModel {

    private static UserModel ourInstance = new UserModel();
    private MyUser user;
    public static UserModel getInstance() {
        return ourInstance;
    }

    private UserModel() {
    }

    /**
     * 同意添加好友：1、发送同意添加的请求，2、添加对方到自己的好友列表中
     */
    public void agreeAddFriend(MyUser friend, SaveListener listener) {
        Friend f = new Friend();
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        f.setUser(user);
        f.setFriend(friend);
        f.save(listener);
    }

    /**
     * 更新用户资料和会话资料
     *
     *
     */
    public void updateUserInfo(MessageEvent event) {
        final BmobIMConversation conversation = event.getConversation();
        final BmobIMUserInfo info = event.getFromUserInfo();
        final BmobIMMessage msg = event.getMessage();
        String username = info.getName();
        String title = conversation.getConversationTitle();
        //sdk内部，将新会话的会话标题用objectId表示，因此需要比对用户名和会话标题--单聊，后续会根据会话类型进行判断
        if (!username.equals(title)) {
            BmobQuery<MyUser> query=new BmobQuery<>();
            query.addWhereEqualTo("objectId",info.getUserId());
            query.findObjects(new FindListener<MyUser>() {
                @Override
                public void done(List<MyUser> list, BmobException e) {
                    if(e==null){
                        user=list.get(0);
                    }
                }
            });
            String fileUrl = user.getImgFileUrl();
            if(!TextUtils.isEmpty(fileUrl)){
                conversation.setConversationIcon(fileUrl);
            }else{
                conversation.setConversationIcon("");
            }
            conversation.setConversationTitle(user.getUsername());
            BmobIM.getInstance().updateUserInfo(info);
            if(!msg.isTransient()){
                BmobIM.getInstance().updateConversation(conversation);
            }
        }
    }




    public MyUser queryUserInfo(String id){
        BmobQuery<MyUser> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId",id);
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if(e==null){
                    user = list.get(0);
                    L.i(user.getUsername());
                }else{
                    L.i(e.getErrorCode()+","+e.getMessage());
                }
            }
        });
        return user;
    }



}
