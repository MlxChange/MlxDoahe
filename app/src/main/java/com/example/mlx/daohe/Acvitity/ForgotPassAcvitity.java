package com.example.mlx.daohe.Acvitity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.MyUser;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;



/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.view
 * 创建者：MLX
 * 创建时间：2017/2/4 21:58
 * 用途：找回密码Acvitity
 */

public class ForgotPassAcvitity extends BaseAcvitity {


    private TextView title;//title
    private Button send, forgot_ok;
    //手机号，验证码，密码编辑框
    private EditText edit_phone, edit_code, edit_pass;
    //新密码编辑框
    private TextInputLayout edit_pass_layout;

    private MyUser user;
    private ProgressDialog prodialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass);
        //加载背景图片
        Glide.with(this).load(R.drawable.login_bg).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                getWindow().setBackgroundDrawable(resource);
            }
        });


        initView();
    }

    public void back(View v) {
        finish();
    }

    //初始化所有的View
    private void initView() {
        title = (TextView) findViewById(R.id.forgot_title);
        UtilS.setFont(this, title);
        send = (Button) findViewById(R.id.forgot_send);
        forgot_ok = (Button) findViewById(R.id.forgot_ok);
        edit_code = (EditText) findViewById(R.id.forgot_code);
        edit_phone = (EditText) findViewById(R.id.forgot_phone);
        edit_pass_layout = (TextInputLayout) findViewById(R.id.forgot_pass_layout);
        edit_pass = (EditText) findViewById(R.id.forgot_pass);

        prodialog = new ProgressDialog(this);
        prodialog.setTitle("请稍等哒");
        prodialog.setMessage("努力加载中");

    }

    public void forgot(View v) {
        switch (v.getId()) {
            //确定按钮
            case R.id.forgot_ok: {
                String code = edit_code.getText().toString().trim();
                final String pass = edit_pass.getText().toString().trim();
                if (!TextUtils.isEmpty(code) & !TextUtils.isEmpty(pass)) {
                    if (UtilS.isPass(pass)) {
                        prodialog.show();
                        //重置密码
                        BmobUser.resetPasswordBySMSCode(code, pass, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                prodialog.dismiss();
                                if (e == null) {
                                    Toast.makeText(ForgotPassAcvitity.this, "恭喜你，现在可以用新密码登陆啦", Toast.LENGTH_SHORT).show();
                                    edit_pass.setText("");
                                    edit_code.setText("");
                                    edit_phone.setText("");
                                } else {
                                    Toast.makeText(ForgotPassAcvitity.this, "重置密码失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(this, "密码应为6-12为任意字符组合", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.forgot_send: {
                //发送验证码按钮
                final String phoneNumber = edit_phone.getText().toString().trim();
                if (!TextUtils.isEmpty(phoneNumber)) {
                    if (UtilS.isPhone(phoneNumber)) {
                        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
                        query.addWhereEqualTo("mobilePhoneNumber", phoneNumber);
                        prodialog.show();
                        //发送验证码
                        query.findObjects(new FindListener<MyUser>() {
                            @Override
                            public void done(final List<MyUser> object, BmobException e) {
                                if (e == null) {
                                    BmobSMS.requestSMSCode(phoneNumber, "lxchange", new QueryListener<Integer>() {
                                        @Override
                                        public void done(Integer integer, BmobException e) {
                                            prodialog.dismiss();
                                            if (e == null) {
                                                user=object.get(0);
                                                Toast.makeText(ForgotPassAcvitity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                                                edit_pass_layout.setVisibility(View.VISIBLE);
                                            } else {
                                                Toast.makeText(ForgotPassAcvitity.this, "验证码发送失败" + e.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(ForgotPassAcvitity.this, "此手机号未注册", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
