package com.example.mlx.daohe.Acvitity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.Adapter.NewFriendAdapter;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.db.NewFriendManager;
import com.example.mlx.daohe.entiy.MyUser;
import com.example.mlx.daohe.entiy.NewFriend;

import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Acvitity
 * 创建者：MLX
 * 创建时间：2017/2/20 20:08
 * 用途：查找好友界面
 */

public class FindFriends extends AppCompatActivity{


    //用于显示好友资料的linearlayout,当查找到好友时显示，平常为不显示
    private LinearLayout layout;
    //姓名框
    private EditText edit_name;
    //查找到的用户
    private MyUser myUser;
    //好友资料头像
    private ImageView icon;
    //好友姓名
    private TextView username;
    private ListView mlistview;//用来显示所有的添加好友请求
    //listviw
    private NewFriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findfriends);
        initView();
        MyUser myUser1= BmobUser.getCurrentUser(MyUser.class);
        BmobIM.connect(myUser1.getObjectId(), new ConnectListener() {
            @Override
            public void done(String s, BmobException e) {
            }
        });
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.findfriend_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layout = (LinearLayout) findViewById(R.id.findfriend_finded);
        edit_name = (EditText) findViewById(R.id.findFriend_serceh);
        edit_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //当清空输入框的时候，让下面资料隐藏
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    layout.setVisibility(View.GONE);
                }
            }
        });
        icon = (ImageView) findViewById(R.id.findfriend_circle);
        username = (TextView) findViewById(R.id.findfriend_name);
        mlistview= (ListView) findViewById(R.id.newfriend_listview);

        List<NewFriend> newFriends = NewFriendManager.getInstance(this).getAllNewFriend();
        adapter=new NewFriendAdapter(this,newFriends);
        mlistview.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //
    public void addNewFriend(View v) {
        switch (v.getId()) {
            case R.id.findfriend_find: {
                //每次查找都让资料隐藏，然后在gatuser中在显示
                layout.setVisibility(View.GONE);
                String name = edit_name.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    getUser(name);
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.findfriend_add: {
                //跳转到添加好友界面
                Intent intent = new Intent(FindFriends.this, RequestFriend.class);
                intent.putExtra("id",myUser.getObjectId());
                intent.putExtra("name",myUser.getUsername());
                if(!TextUtils.isEmpty(myUser.getImgFileUrl())){
                    intent.putExtra("img",myUser.getImgFileUrl());
                }else{
                    intent.putExtra("img","");
                }
                startActivity(intent);
                break;
            }
        }
    }

    //查询好友
    public void getUser(String name) {
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.addWhereEqualTo("username", name);
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (e == null) {
                    myUser = list.get(0);
                    Glide.with(FindFriends.this).load(myUser.getImgFileUrl()).error(R.drawable.error).into(icon);
                    username.setText(edit_name.getText().toString());
                    layout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(FindFriends.this, "查询用户失败，请检查用户名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}
