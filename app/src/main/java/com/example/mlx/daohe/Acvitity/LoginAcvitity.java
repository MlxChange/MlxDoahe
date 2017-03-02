package com.example.mlx.daohe.Acvitity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import com.example.mlx.daohe.MainActivity;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.MyUser;


/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.view
 * 创建者：MLX
 * 创建时间：2017/2/3 17:14
 * 用途：登陆Acvitity
 */

public class LoginAcvitity extends BaseAcvitity {


    private TextView title;
    private EditText edit_name,edit_pass;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(BmobUser.getCurrentUser()!=null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        setContentView(R.layout.login);
        Glide.with(this).load(R.drawable.login_bg).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                getWindow().setBackgroundDrawable(resource);
            }
        });
        initView();
    }

    private void initView() {
        title= (TextView) findViewById(R.id.login_title);
        UtilS.setFont(this,title);
        edit_name= (EditText) findViewById(R.id.login_name);
        edit_pass= (EditText) findViewById(R.id.login_pass);
        dialog=new ProgressDialog(this);
        dialog.setTitle("请稍等哒");
        dialog.setMessage("努力加载中");
    }

    public void login(View v){
        final String name = edit_name.getText().toString().trim();
        final String pass = edit_pass.getText().toString().trim();
        if(!TextUtils.isEmpty(name)&!TextUtils.isEmpty(pass)){
            final MyUser user=new MyUser();
            user.setUsername(name);
            user.setPassword(pass);
            dialog.show();
            user.login(new SaveListener<MyUser>() {
                @Override
                public void done(MyUser myUser, BmobException e) {
                    dialog.dismiss();
                    if(e==null){
                        BmobIMUserInfo userInfo = new BmobIMUserInfo();
                        userInfo.setUserId(myUser.getObjectId());
                        userInfo.setName(myUser.getUsername());
                        if(!TextUtils.isEmpty(myUser.getImgFileUrl())){
                            userInfo.setAvatar(myUser.getImgFileUrl());
                        }
                        startActivity(new Intent(LoginAcvitity.this,MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginAcvitity.this, "登录失败，请检查用户名或密码", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void login_loginAndsign(View v){
        switch (v.getId()){
            case R.id.login_forgot:{
                startActivity(new Intent(this,ForgotPassAcvitity.class));
                break;
            }
            case R.id.login_signup:{
                startActivity(new Intent(this,SignUpActivity.class));
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialog!=null){
            dialog.dismiss();
        }
    }


}
