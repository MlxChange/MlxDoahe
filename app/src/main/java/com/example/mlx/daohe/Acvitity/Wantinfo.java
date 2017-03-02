package com.example.mlx.daohe.Acvitity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.UI.CustomDialog;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.db.NewFriendManager;
import com.example.mlx.daohe.entiy.AddFriendMessage;
import com.example.mlx.daohe.entiy.MyUser;
import com.example.mlx.daohe.entiy.NewFriend;
import com.example.mlx.daohe.entiy.Want;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Acvitity
 * 创建者：MLX
 * 创建时间：2017/2/25 20:03
 * 用途：
 */

public class Wantinfo extends BaseAcvitity implements View.OnClickListener {

    private ImageView mywant_bg, mywant_circle;
    private Toolbar mywant_toolbar;
    private AppBarLayout near_bar;
    private MaterialSpinner mywant_type;
    private MaterialSpinner mywant_content;
    private MaterialSpinner mywant_type2;
    private LinearLayout setting_phone_layout;
    private EditText mywant_desc;
    private LinearLayout setting_desc_layout;
    private TextView mywant_phone, mywant_time;
    private LinearLayout mywant_phone_layout;
    String name;
    private CustomDialog cusdialog;
    private Want want;
    private Button dialog_addfriend;
    private Button dialog_seeinfo;
    private Button dialog_cancel;
    private MyUser myUser;
    private long time;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywant);
        name = getIntent().getStringExtra("username");
        time=getIntent().getLongExtra("time",0);
        initView();
        getwantinfo(name,time);
    }

    private void getwantinfo(String wantid,long time2) {
        BmobQuery<Want> wantBmobQuery = new BmobQuery<>();
        wantBmobQuery.addWhereEqualTo("username", wantid);
        BmobQuery<Want> wantBmobQuery1 = new BmobQuery<>();
        wantBmobQuery1.addWhereEqualTo("requestTime", time2);
        BmobQuery<Want> Query = new BmobQuery<>();
        List<BmobQuery<Want>> list=new ArrayList<>();
        list.add(wantBmobQuery);
        list.add(wantBmobQuery1);
        Query.and(list);
        dialog.show();
        Query.findObjects(new FindListener<Want>() {
            @Override
            public void done(List<Want> list, BmobException e) {
                dialog.dismiss();
                if (e == null) {
                    want = list.get(0);
                    L.i("找到的数量为:"+list.size());
                    init();
                } else {
                    L.i(e.getErrorCode() + "," + e.getMessage());
                    Toast.makeText(Wantinfo.this, "查询邀请信息失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        mywant_bg = (ImageView) findViewById(R.id.mywant_bg);
        mywant_toolbar = (Toolbar) findViewById(R.id.mywant_toolbar);
        setSupportActionBar(mywant_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("邀请详情");
        getSupportActionBar().setTitle("");
        near_bar = (AppBarLayout) findViewById(R.id.near_bar);
        mywant_type = (MaterialSpinner) findViewById(R.id.mywant_name);
        mywant_content = (MaterialSpinner) findViewById(R.id.mywant_content);
        mywant_type2 = (MaterialSpinner) findViewById(R.id.mywant_type2);
        setting_phone_layout = (LinearLayout) findViewById(R.id.setting_phone_layout);
        mywant_desc = (EditText) findViewById(R.id.mywant_desc);
        setting_desc_layout = (LinearLayout) findViewById(R.id.setting_desc_layout);
        mywant_phone = (TextView) findViewById(R.id.mywant_phone);
        mywant_phone_layout = (LinearLayout) findViewById(R.id.mywant_phone_layout);
        mywant_circle = (ImageView) findViewById(R.id.mywant_circle);
        mywant_time = (TextView) findViewById(R.id.mywant_time);
        dialog=new ProgressDialog(this);
        dialog.setTitle("请稍等哒");
        dialog.setMessage("努力加载中");
    }

    private void init() {
        Glide.with(this).load(R.drawable.giude_item3).into(mywant_bg);
        Glide.with(this).load(want.getImgurl()).error(R.drawable.error).into(mywant_circle);
        mywant_type.setItems(want.getUsername());
        mywant_type.setEnabled(false);
        mywant_content.setItems(want.getContent());
        mywant_content.setEnabled(false);
        mywant_type2.setItems(want.getType());
        mywant_type2.setEnabled(false);
        mywant_phone.setText(want.getPhonenumber());
        mywant_desc.setText(want.getMessage());
        mywant_time.setText(UtilS.getTime(want.getRequestTime()));


        mywant_circle.setOnClickListener(this);
        cusdialog = new CustomDialog(this, 0, 0, R.layout.dialog_pop,R.style.customdialog, Gravity.BOTTOM, R.style.pop);
        dialog_addfriend = (Button) cusdialog.findViewById(R.id.dialog_addfriend);
        dialog_addfriend.setOnClickListener(this);
        dialog_seeinfo = (Button) cusdialog.findViewById(R.id.dialog_seeinfo);
        dialog_seeinfo.setOnClickListener(this);
        dialog_cancel = (Button)cusdialog.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mywant_circle:{
                cusdialog.show();
                break;
            }
            case R.id.dialog_addfriend:{
                getUser(want.getUsername());
                if(myUser!=null){
                    Intent intent = new Intent(Wantinfo.this, RequestFriend.class);
                    intent.putExtra("id",myUser.getObjectId());
                    intent.putExtra("name",myUser.getUsername());
                    if(!TextUtils.isEmpty(myUser.getImgFileUrl())){
                        intent.putExtra("img",myUser.getImgFileUrl());
                    }else{
                        intent.putExtra("img","");
                    }
                    startActivity(intent);
                }
                break;
            }
            case R.id.dialog_seeinfo:{

                break;
            }
            case R.id.dialog_cancel:{
                cusdialog.dismiss();
                break;
            }
        }
    }

    public void getUser(String name) {
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.addWhereEqualTo("username", name);
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (e == null) {
                    myUser = list.get(0);
                } else {
                    Toast.makeText(Wantinfo.this, "查询用户失败，请检查用户名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
