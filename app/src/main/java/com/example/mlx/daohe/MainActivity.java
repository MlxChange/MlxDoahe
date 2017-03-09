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

    private NavigationView navigationView;//左侧菜单栏
    private DrawerLayout drawerLayout;//MainAcvitity根布局
    private RelativeLayout nav_head_layout;//菜单栏头布局
    private CircleImageView circleImageView;//菜单栏头像
    private ImageView hot, add, message;//主界面三个icon，用于切换fragment
    private Fragment hot_fragment, im_fragment,near_fragment;//三个主要的fragment，分别为最火，附近，消息
    private FragmentManager manager;//fragmetManager
    private List<Fragment> mfragment;//用于装在fragemnt的list
    private BadgeView badgeView;//小圆点
    FragmentTransaction transaction;//fragment开启事务
    private MessageEvent mevent;//messageevent是bomb即时通讯服务器发来的事件类

    private int position=0;//选择的fragment索引，用于解决fragment重影问题
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getpression();//先获得定位权限，用于美食界面的获取数据
        initView();//初始化所有的View

    }

    public void getpression() {
        //检查是否授予了定位权限，如果没有就申请权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    //当申请权限对话框弹出时，回调此方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }


    //设置当按返回键的时候，用于确认退出
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
        //bmob即时通讯连接服务器
        MyUser myUser1 = BmobUser.getCurrentUser(MyUser.class);
        BmobIM.connect(myUser1.getObjectId(), new ConnectListener() {
            @Override
            public void done(String s, BmobException e) {
            }
        });
        //获得toolbar对象，并且设置为actionbar
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
        //设置左侧菜单的图标
        toolbar.setNavigationIcon(R.mipmap.menu);
        //设置当点击时显示菜单
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });
        //用Glide加载背景图片，减少性能开销
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
        //初始化所有的fragment
        hot_fragment = new Hot_Fragment();
        im_fragment = new IM_Fragment();
        near_fragment=new fragment_near();
        //初始fragment的容器，并将已经初始化好的fragment装在进去
        mfragment = new ArrayList<>();
        mfragment.add(hot_fragment);
        mfragment.add(near_fragment);
        mfragment.add(im_fragment);
        //开启事务，并将所有的fragment添加进去，此处不采用replace方式
        //因为此方法会导致fragment为空白，所以采用hide+show方式
        transaction = manager.beginTransaction();
        transaction.add(R.id.main_fragment, mfragment.get(0));
        transaction.add(R.id.main_fragment, mfragment.get(1));
        transaction.add(R.id.main_fragment, mfragment.get(2));
        //show第一个页面
        transaction.show(mfragment.get(0));
        transaction.hide(mfragment.get(1));
        transaction.hide(mfragment.get(2));
        transaction.commit();//提交事务


    }

    private void setTitleIcon(boolean hotb, boolean addb, boolean messageb) {
        //根据boolean来确定点击事件，并同时切换图标与fragment
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

    //当acvitity被回收重现以后，这里记录的选择的值，重新显示fragment
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position=savedInstanceState.getInt("position");
        qiehuanFragment(position);
        super.onRestoreInstanceState(savedInstanceState);
    }

    //当Acvitity被回收的时候 把当前选择的position放到bundle里
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("position",position);

    }

    //切换fragment
    private void qiehuanFragment(int i) {
        FragmentTransaction transaction = manager.beginTransaction();
        //根据传入的数值进行hide或show
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



    //绑定EventBus
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    //解绑EventBus
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

    //接收EventBus发来的消息，显示小红点，已废弃
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showRedPoint(MyEvent event) {
        badgeView = BadgeFactory.create(this).
                setWidthAndHeight(6, 6).setBadgeBackground(Color.WHITE).
                setBadgeGravity(Gravity.END | Gravity.TOP).setSpace(-10, 0).setShape(BadgeView.SHAPE_CIRCLE).bind(message);
        badgeView.setId(StaticClass.MESSAGE_ID);

    }

    //接收EventBus发来的消息，显示小红点
    @Subscribe
    public void showRedpoint(MessageEvent event) {
        mevent = event;
        badgeView = BadgeFactory.create(this).
                setWidthAndHeight(6, 6).setBadgeBackground(Color.WHITE).
                setBadgeGravity(Gravity.END | Gravity.TOP).setSpace(-10, 0).setShape(BadgeView.SHAPE_CIRCLE).bind(message);
        badgeView.setId(StaticClass.MESSAGE_ID);
    }

    //菜单栏点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //退出登录
            case R.id.menu_logout:{
                BmobUser.getCurrentUser().logOut();
                startActivity(new Intent(this, LoginAcvitity.class));
                finish();
                break;
            }
            //个人设置
            case R.id.menu_setting:{
                startActivity(new Intent(this, SettingActivity.class));
                break;
            }
            //不许无聊
            case R.id.menu_zhihu:{
                startActivity(new Intent(this, ZhihuMainAcivitity.class));
                break;
            }
            //新闻
            case R.id.menu_news:{
                break;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.nav_head_circle){
            //点击头像进入设置界面
            startActivity(new Intent(this, SettingActivity.class));
        }
    }

}
