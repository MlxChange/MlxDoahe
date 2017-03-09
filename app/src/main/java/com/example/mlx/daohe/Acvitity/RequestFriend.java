package com.example.mlx.daohe.Acvitity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.db.NewFriendManager;
import com.example.mlx.daohe.entiy.AddFriendMessage;
import com.example.mlx.daohe.entiy.MyUser;
import com.example.mlx.daohe.entiy.NewFriend;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Acvitity
 * 创建者：MLX
 * 创建时间：2017/2/20 22:05
 * 用途：添加好友acvitity
 */

public class RequestFriend extends AppCompatActivity {


    private EditText edit_message;
    private String id;
    private String name;
    private String imgurl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend);
        initView();
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        imgurl=intent.getStringExtra("img");
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.requestfriend_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edit_message= (EditText) findViewById(R.id.request_message);
    }

    public void requestFriend(View v){
        //L.i("sssss");
        BmobIMUserInfo info=new BmobIMUserInfo(id,name,imgurl);
        BmobIMConversation conversation = BmobIM.getInstance().startPrivateConversation(info, true, null);
        BmobIMConversation obtain = BmobIMConversation.obtain(BmobIMClient.getInstance(), conversation);
        AddFriendMessage message = new AddFriendMessage();
        MyUser user= BmobUser.getCurrentUser(MyUser.class);
        String trim = edit_message.getText().toString().trim();
        if(TextUtils.isEmpty(trim)){
            trim="你好";
        }
        message.setContent(trim);
        Map<String,Object> map =new HashMap<>();
        map.put("name", user.getUsername());//发送者姓名
        map.put("avatar",user.getImgFileUrl());//发送者的头像
        map.put("uid",user.getObjectId());//发送者的ID
        message.setExtraMap(map);
        obtain.sendMessage(message, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage bmobIMMessage, BmobException e) {
                if (e == null) {//发送成功
                    Toast.makeText(RequestFriend.this, "好友请求发送成功，等待验证", Toast.LENGTH_SHORT).show();
                    NewFriend newFriend = new NewFriend(id,edit_message.getText().toString().trim(),name,imgurl, StaticClass.STATUS_VERIFY_ME_SEND,System.currentTimeMillis());
                    NewFriendManager.getInstance(RequestFriend.this).insertNreFriends(newFriend);
                    finish();
                } else {//发送失败
                    L.i(e.getErrorCode()+","+e.getMessage());
                    Toast.makeText(RequestFriend.this, "发送失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
