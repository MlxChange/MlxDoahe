package com.example.mlx.daohe.Acvitity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mlx.daohe.Adapter.ChatAdapter;
import com.example.mlx.daohe.Moudel.UserModel;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.SharedUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.newim.listener.MessagesQueryListener;
import cn.bmob.v3.exception.BmobException;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Acvitity
 * 创建者：MLX
 * 创建时间：2017/2/23 18:53
 * 用途：
 */

public class ChatAcvitity extends BaseAcvitity implements View.OnClickListener {

    private ImageView addmore,voice,send;
    private EditText edit_message;
    private Button voice_button;
    private ListView listView;
    private Toolbar toolbar;
    BmobIMConversation conversation;
    private String friendimg;
    BmobIMConversation c;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlayout);
        c= (BmobIMConversation) getIntent().getBundleExtra("c").getSerializable("c");
        conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), c);
        friendimg=getIntent().getStringExtra("friendimg");
        initView();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
        addmore= (ImageView) findViewById(R.id.chat_addmore);
        addmore.setOnClickListener(this);
        toolbar= (Toolbar) findViewById(R.id.chat_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(conversation.getConversationTitle());
        voice= (ImageView) findViewById(R.id.chat_voice);
        voice.setOnClickListener(this);
        send= (ImageView) findViewById(R.id.chat_send);
        send.setOnClickListener(this);
        edit_message= (EditText) findViewById(R.id.chat_editmessage);
        listView= (ListView) findViewById(R.id.chat_listview);
        adapter=new ChatAdapter(this,conversation);
        conversation.queryMessages(null,20, new MessagesQueryListener() {
            @Override
            public void done(List<BmobIMMessage> list, BmobException e) {
                adapter.addMessages(list);
            }
        });
        listView.setAdapter(adapter);
        //L.i(adapter.getCount()+"");
        listView.smoothScrollToPosition(adapter.getCount());
    }



    public MessageSendListener listener = new MessageSendListener() {
        @Override
        public void done(BmobIMMessage bmobIMMessage, BmobException e) {
            if(e==null){
                edit_message.setText("");
                adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(adapter.getCount());
            }
        }

        @Override
        public void onStart(BmobIMMessage bmobIMMessage) {
            adapter.addMessage(bmobIMMessage);
            //adapter.notifyDataSetChanged();
            listView.smoothScrollToPosition(adapter.getCount());
        }

    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chat_send:{
                sendTextMessage();
                break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        BmobIM.getInstance().updateConversation(conversation);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        BmobIM.getInstance().updateConversation(c);
    }

    @Subscribe(sticky = true)
    public void reciveMessage(MessageEvent event){
        BmobIMConversation c = event.getConversation();
        BmobIMMessage message = event.getMessage();
        adapter.addMessage(message);
        listView.smoothScrollToPosition(adapter.getCount());
    }

    private void sendTextMessage() {
        BmobIMTextMessage message=new BmobIMTextMessage();
        String string = edit_message.getText().toString();
        if(!TextUtils.isEmpty(string)){
            message.setContent(string);
            conversation.sendMessage(message,listener);
        }
    }
}
