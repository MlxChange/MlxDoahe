package com.example.mlx.daohe.Acvitity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.SharedUtils;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.Utils.UtilS;


/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.view
 * 创建者：MLX
 * 创建时间：2017/2/1 21:43
 * 用途：闪屏页
 */

public class SplashAcvitity extends BaseAcvitity {


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case StaticClass.HAND_SPLASH:{
                    if (isFirst()){
                        startActivity(new Intent(SplashAcvitity.this,GuideActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(SplashAcvitity.this,LoginAcvitity.class));
                        finish();
                    }
                    break;
                }
            }
        }
    };
    private RelativeLayout layout;
    private TextView xtv;
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        initView();
    }

    private void initView() {
        //延时2s后判断是否第一次打开应用然后根据结果进入引导页或主页
        layout= (RelativeLayout) findViewById(R.id.splash);
        Glide.with(this).load(R.drawable.bg).fitCenter().into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                layout.setBackground(resource);
            }
        });
        handler.sendEmptyMessageDelayed(StaticClass.HAND_SPLASH,2000);
        textview= (TextView) findViewById(R.id.splash_tv);
        xtv= (TextView) findViewById(R.id.splash_txt);
        UtilS.setFont(this,textview);
        UtilS.setFont(this,xtv);

    }


    //查看是否是第一次打开应用
    public boolean isFirst() {
        boolean bool = SharedUtils.getBool(this, StaticClass.SPLASH_ISFIRST, true);
        if(bool){
            //如果是第一次打开应用的话就返回true，并同时使用SharedPreferences存储变量
            //下一次打开将不是第一次打开应用，返回false
            SharedUtils.putBool(this,StaticClass.SPLASH_ISFIRST,false);
            return true;
        }else{
            return false;
        }
    }

    //设置无法按下返回键
    @Override
    public void onBackPressed() {
    }
}
