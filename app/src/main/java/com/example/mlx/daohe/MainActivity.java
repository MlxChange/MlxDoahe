package com.example.mlx.daohe;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mlx.daohe.Acvitity.BaseAcvitity;
import com.example.mlx.daohe.Acvitity.LoginAcvitity;
import com.example.mlx.daohe.Acvitity.SettingActivity;
import com.example.mlx.daohe.Acvitity.ZhihuMainAcivitity;
import com.example.mlx.daohe.Fragment.Hot_Fragment;
import com.example.mlx.daohe.Fragment.IM_Fragment;
import com.example.mlx.daohe.Fragment.fragment_near;
import com.example.mlx.daohe.MessageEvent.MyEvent;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.entiy.MyUser;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseAcvitity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RelativeLayout nav_head_layout;
    private CircleImageView circleImageView;
    private ImageView hot, add, message;
    private Fragment hot_fragment, im_fragment,near_fragment;
    private FragmentManager manager;
    private List<Fragment> mfragment;
    private BadgeView badgeView;
    FragmentTransaction transaction;
    private MessageEvent mevent;

    private int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getpression();
        initView();

    }

    public void getpression() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("退出确认").setMessage("你确定要退出吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("取消",null).create().show();
    }

    private void initView() {
        MyUser myUser1 = BmobUser.getCurrentUser(MyUser.class);
        BmobIM.connect(myUser1.getObjectId(), new ConnectListener() {
            @Override
            public void done(String s, BmobException e) {
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        navigationView = (NavigationView) findViewById(R.id.main_navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawlayout);
        navigationView.setNavigationItemSelectedListener(this);
        CircleImageView imageView= (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_head_circle);
        imageView.setOnClickListener(this);
        hot = (ImageView) findViewById(R.id.main_img_hot);
        add = (ImageView) findViewById(R.id.main_img_add);
        message = (ImageView) findViewById(R.id.main_img_message);

        nav_head_layout = (RelativeLayout) navigationView.getHeaderView(0).findViewById(R.id.nav_head_layout);
        circleImageView = (CircleImageView) nav_head_layout.findViewById(R.id.nav_head_circle);
        toolbar.setNavigationIcon(R.mipmap.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });
        Glide.with(this).load(R.drawable.head_img).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                nav_head_layout.setBackground(resource);
            }
        });
        Glide.with(this).load(R.drawable.giude_item2).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                circleImageView.setImageDrawable(resource);
            }
        });

        manager = getSupportFragmentManager();
        hot_fragment = new Hot_Fragment();
        im_fragment = new IM_Fragment();
        near_fragment=new fragment_near();
        mfragment = new ArrayList<>();
        mfragment.add(hot_fragment);
        mfragment.add(near_fragment);
        mfragment.add(im_fragment);
        transaction = manager.beginTransaction();
        transaction.add(R.id.main_fragment, mfragment.get(0));
        transaction.add(R.id.main_fragment, mfragment.get(1));
        transaction.add(R.id.main_fragment, mfragment.get(2));
        transaction.show(mfragment.get(0));
        transaction.hide(mfragment.get(1));
        transaction.hide(mfragment.get(2));
        transaction.commit();


    }

    private void setTitleIcon(boolean hotb, boolean addb, boolean messageb) {
        if (hotb) {
            hot.setImageResource(R.mipmap.hot);
            add.setImageResource(R.mipmap.addressof);
            message.setImageResource(R.mipmap.messageof);
            qiehuanFragment(0);
        } else if (addb) {
            hot.setImageResource(R.mipmap.hotof);
            add.setImageResource(R.mipmap.address);
            message.setImageResource(R.mipmap.messageof);
            qiehuanFragment(1);
        } else if (messageb) {
            hot.setImageResource(R.mipmap.hotof);
            add.setImageResource(R.mipmap.addressof);
            message.setImageResource(R.mipmap.message);
            if (badgeView != null) {
                badgeView.unbind();
            }
            qiehuanFragment(2);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position=savedInstanceState.getInt("position");
        qiehuanFragment(position);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("position",position);

    }

    private void qiehuanFragment(int i) {
        FragmentTransaction transaction = manager.beginTransaction();
        for(int k=0;k<3;k++){
            L.i("i="+i+",k="+k);
            if(k==i){
                transaction.show(mfragment.get(i));
            }else{
                transaction.hide(mfragment.get(k));
            }
        }
        transaction.commit();
    }



    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void qiehuan(View v) {
        switch (v.getId()) {
            case R.id.main_img_hot: {
                setTitleIcon(true, false, false);
                break;
            }
            case R.id.main_img_add: {
                setTitleIcon(false, true, false);
                break;
            }
            case R.id.main_img_message: {
                setTitleIcon(false, false, true);
                break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showRedPoint(MyEvent event) {
        badgeView = BadgeFactory.create(this).
                setWidthAndHeight(6, 6).setBadgeBackground(Color.WHITE).
                setBadgeGravity(Gravity.END | Gravity.TOP).setSpace(-10, 0).setShape(BadgeView.SHAPE_CIRCLE).bind(message);
        badgeView.setId(StaticClass.MESSAGE_ID);

    }

    @Subscribe
    public void showRedpoint(MessageEvent event) {
        mevent = event;
        badgeView = BadgeFactory.create(this).
                setWidthAndHeight(6, 6).setBadgeBackground(Color.WHITE).
                setBadgeGravity(Gravity.END | Gravity.TOP).setSpace(-10, 0).setShape(BadgeView.SHAPE_CIRCLE).bind(message);
        badgeView.setId(StaticClass.MESSAGE_ID);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:{
                BmobUser.getCurrentUser().logOut();
                startActivity(new Intent(this, LoginAcvitity.class));
                finish();
                break;
            }
            case R.id.menu_setting:{
                startActivity(new Intent(this, SettingActivity.class));
                break;
            }
            case R.id.menu_zhihu:{
                startActivity(new Intent(this, ZhihuMainAcivitity.class));
                break;
            }
            case R.id.menu_news:{
                break;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.nav_head_circle){
            startActivity(new Intent(this, SettingActivity.class));
        }
    }

}
