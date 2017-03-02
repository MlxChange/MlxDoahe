package com.example.mlx.daohe.Acvitity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.MyUser;


/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.view
 * 创建者：MLX
 * 创建时间：2017/2/3 17:12
 * 用途：注册 Acvitity
 */

public class SignUpActivity extends BaseAcvitity {


    private EditText name, pass, pass2, phone;
    private int success = 0;
    private String code = "";
    private ProgressDialog prodialog;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Glide.with(this).load(R.drawable.login_bg).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                getWindow().setBackgroundDrawable(resource);
            }
        });

        initview();
    }

    private void initview() {
        title = (TextView) findViewById(R.id.sign_title);
        UtilS.setFont(this, title);
        name = (EditText) findViewById(R.id.sign_name);
        pass = (EditText) findViewById(R.id.sign_pass1);
        pass2 = (EditText) findViewById(R.id.sign_pass2);
        phone = (EditText) findViewById(R.id.sign_phone);
        prodialog = new ProgressDialog(this);
        prodialog.setTitle("请稍等哒");
        prodialog.setMessage("努力加载中(ง •̀_•́)ง");
    }

    public void sign(View v) {
        if (v.getId() == R.id.sign_sign) {
            final String username = name.getText().toString().trim();
            final String password = pass.getText().toString().trim();
            String password2 = pass2.getText().toString().trim();
            final String phonenumber = phone.getText().toString().trim();
            if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(password) & !
                    TextUtils.isEmpty(password2) & !TextUtils.isEmpty(phonenumber)) {
                if (password.equals(password2)) {
                    final MyUser user = new MyUser();
                    if (UtilS.isUsername(username)) {
                        if (UtilS.isPass(password)) {
                            if (UtilS.isPhone(phonenumber)) {
                                prodialog.show();
                                user.setUsername(username);
                                user.setPassword(password);
                                user.setMypass(password);
                                user.setMobilePhoneNumber(phonenumber);
                                user.signUp(new SaveListener<MyUser>() {
                                    @Override
                                    public void done(MyUser myUser, BmobException e) {
                                        if (e == null) {
                                            BmobIMUserInfo userInfo = new BmobIMUserInfo();
                                            userInfo.setUserId(myUser.getObjectId());
                                            userInfo.setName(myUser.getUsername());
                                            if(!TextUtils.isEmpty(myUser.getImgFileUrl())){
                                                userInfo.setAvatar(myUser.getImgFileUrl());
                                            }
                                            yanzhengPhone(phonenumber,username,password);
                                        } else {
                                            prodialog.dismiss();
                                            Toast.makeText(SignUpActivity.this, "注册失败：" + e.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "密码必须为6-12位任意字符组合", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "账号必须为6-15位字母数字", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    pass.setText("");
                    pass2.setText("");
                    phone.setText("");
                }
            } else {
                Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void back(View v) {
        finish();
    }

    private void yanzhengPhone(final String number, final String username, final String password) {
        BmobSMS.requestSMSCode(number, "lxchange", new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    prodialog.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    final EditText editText = new EditText(SignUpActivity.this);
                    final AlertDialog alertDialog = builder.setTitle("验证短信").setView(editText).setCancelable(false).setPositiveButton("确定", null).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(SignUpActivity.this, "注册成功，请小主尽快绑定手机号哦", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).create();
                    alertDialog.show();
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prodialog.show();
                            code = editText.getText().toString().trim();
                            if (!TextUtils.isEmpty(code)) {
                                BmobSMS.verifySmsCode(number, code, new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                            Toast.makeText(SignUpActivity.this, "小主注册成功", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(SignUpActivity.this, "验证码输入错误,请重试", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(SignUpActivity.this, "输入码不能为空", Toast.LENGTH_SHORT).show();
                                success = 2;
                            }
                        }
                    });
                } else {
                    prodialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "短信验证码发送失败，请重试", Toast.LENGTH_SHORT).show();
                    L.i("错误：" + e.toString());
                }
            }
        });
    }
}
