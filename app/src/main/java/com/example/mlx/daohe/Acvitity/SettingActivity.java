package com.example.mlx.daohe.Acvitity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.UI.CustomDialog;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.SharedUtils;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.MyUser;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.view
 * 创建者：MLX
 * 创建时间：2017/1/31 23:29
 * 用途：设置Activity
 */

public class SettingActivity extends BaseAcvitity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private EditText edit_name, edit_age, edit_desc, edit_email;//4个edittext
    private CircleImageView circleImageView;//圆形头像
    private Switch aSwitch;//修改按钮开关
    private ImageView setting_ok, setting_bg;

    private ProgressDialog dialog;
    private String email1 = "";
    private String name1;
    Button camera, pictrue, cancle;
    private Uri uri;
    private CustomDialog cusdialog;
    private File file;

    private static final int cameraRequestCode = 1;
    private static final int imageRequestCode = 2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        //getpression();

        initView();
    }

    private void initView() {
        edit_name = (EditText) findViewById(R.id.setting_name);
        edit_age = (EditText) findViewById(R.id.setting_age);
        edit_desc = (EditText) findViewById(R.id.setting_desc);
        edit_email = (EditText) findViewById(R.id.edit_email);
        setting_ok = (ImageView) findViewById(R.id.setting_ok);
        setting_ok.setOnClickListener(this);
        setting_bg = (ImageView) findViewById(R.id.setting_bg);
        Glide.with(this).load(R.drawable.giude_item3).into(setting_bg);


        isEnable(false);
        MyUser user = MyUser.getCurrentUser(MyUser.class);
        int age = user.getAge();
        if(!TextUtils.isEmpty(user.getEmail())){
            email1 = user.getEmail();
        }
        String desc = user.getDesc();
        name1 = user.getUsername();
        edit_name.setText(name1);
        if (age != 0) {
            edit_age.setText(age + "");
        }
        if (!TextUtils.isEmpty(email1)) {
            edit_email.setText(email1);
        }
        if (!TextUtils.isEmpty(desc)) {
            edit_desc.setText(desc);
        }

        circleImageView = (CircleImageView) findViewById(R.id.setting_cirlImage);
        String img = SharedUtils.getString(this, "img", null);
        if (!TextUtils.isEmpty(img)) {
            circleImageView.setImageBitmap(BitmapFactory.decodeFile(img));
        }
        aSwitch = (Switch) findViewById(R.id.setting_switch);
        aSwitch.setOnCheckedChangeListener(this);

        dialog = new ProgressDialog(this);
        dialog.setTitle("请稍等哒");
        dialog.setMessage("努力加载中");
    }

    public void isEnable(boolean is) {
        edit_name.setEnabled(is);
        edit_age.setEnabled(is);
        edit_desc.setEnabled(is);
        edit_email.setEnabled(is);
    }

    public void setting_ok(View v) {
        final String name = edit_name.getText().toString().trim();
        String age = edit_age.getText().toString().trim();
        String desc = edit_desc.getText().toString().trim();
        final String email = edit_email.getText().toString().trim();
        if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(age) & !TextUtils.isEmpty(email)) {
            if (UtilS.isUsername(name)) {
                if (UtilS.isEmail(email)) {
                    MyUser user = new MyUser();
                    user.setUsername(name);
                    if (!email1.equals(email)) {
                        user.setEmail(email);
                    }
                    user.setAge(Integer.parseInt(age));
                    if (TextUtils.isEmpty(desc)) {
                        desc = "这个人很懒，什么都没有留下";
                    }
                    user.setDesc(desc);
                    dialog.show();
                    BmobUser bmobUser = MyUser.getCurrentUser();
                    user.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            dialog.dismiss();
                            if (e == null) {
                                if (!email1.equals(email)) {
                                    new AlertDialog.Builder(SettingActivity.this).
                                            setPositiveButton("确定", null).setTitle("确认邮件").setMessage("已经给小主邮箱发送确认邮件，请小主尽快前去确认激活哦")
                                            .setCancelable(false).create().show();
                                }
                                if (!name1.equals(name)) {
                                    new AlertDialog.Builder(SettingActivity.this).
                                            setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    BmobUser.logOut();
                                                    startActivity(new Intent(SettingActivity.this, LoginAcvitity.class));
                                                }
                                            }).setTitle("退出登录").setMessage("因为小主更改了用户名，为了安全起见，小主需要重新登录哦")
                                            .setCancelable(false).create().show();
                                } else {
                                    Toast.makeText(SettingActivity.this, "更新数据成功", Toast.LENGTH_SHORT).show();
                                }
                                isEnable(false);
                                aSwitch.setChecked(false);
                                setting_ok.setVisibility(View.INVISIBLE);
                            } else {
                                if (e.toString().contains("202")) {
                                    Toast.makeText(SettingActivity.this, "此用户名已被注册了哦，换一个吧", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SettingActivity.this, "更新失败：" + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "邮箱格式错误", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "用户名输入不合法", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            isEnable(true);
            setting_ok.setVisibility(View.VISIBLE);
        } else {
            isEnable(false);
            setting_ok.setVisibility(View.INVISIBLE);
        }
    }

    public void getpression() {
        int i = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        //L.i("权限未:"+i);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //L.i("申请权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }else{
            file = new File(getExternalCacheDir(), "circle.jpg");
            uri = Uri.fromFile(file);
            Intent inten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            inten.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            cusdialog.dismiss();
            startActivityForResult(inten, cameraRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            file = new File(getExternalCacheDir(), "circle.jpg");
            uri = Uri.fromFile(file);
            Intent inten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            inten.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            cusdialog.dismiss();
            startActivityForResult(inten, cameraRequestCode);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    public void setting_circle(View v) {
        //L.i("进入相机");
        cusdialog = new CustomDialog(this, 0, 0, R.layout.dialog_pop2, R.style.customdialog, Gravity.BOTTOM, R.style.pop);

        cusdialog.show();
        camera = (Button) cusdialog.findViewById(R.id.dialog_camera);
        pictrue = (Button) cusdialog.findViewById(R.id.dialog_picture);
        cancle = (Button) cusdialog.findViewById(R.id.setting_cancel);
        camera.setOnClickListener(this);
        pictrue.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_camera: {
                getpression();
                break;
            }
            case R.id.dialog_picture: {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                cusdialog.dismiss();
                startActivityForResult(intent, imageRequestCode);
                break;
            }
            case R.id.dialog_cancel: {
                cusdialog.dismiss();
                break;
            }
            case R.id.setting_ok:{
                setting_ok(setting_ok);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.i("requestcode:" + requestCode + ",resultcode:" + resultCode + ",data:" + data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case cameraRequestCode: {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        circleImageView.setImageBitmap(bitmap);
                        SharedUtils.putString(this, "img", file.getAbsolutePath());
                        uploadimg(file.getAbsolutePath());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case imageRequestCode: {
                    uri = data.getData();
                    String filepathColun[] = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, filepathColun, null, null, null);
                    cursor.moveToFirst();
                    int coumindex = cursor.getColumnIndex(filepathColun[0]);
                    String imagePhth = cursor.getString(coumindex);
                    circleImageView.setImageBitmap(BitmapFactory.decodeFile(imagePhth));
                    SharedUtils.putString(this, "img", imagePhth);
                    uploadimg(imagePhth);
                    break;
                }
            }
        }
    }

    private void uploadimg(final String filepath) {
        if(!TextUtils.isEmpty(filepath)) {
            final BmobFile bfile = new BmobFile(new File(filepath));
            dialog.show();
            bfile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        MyUser currentUser = BmobUser.getCurrentUser(MyUser.class);
                        currentUser.setImgFileUrl(bfile.getFileUrl());
                        L.i("文件地址："+bfile.getFileUrl());
                        currentUser.update(currentUser.getObjectId(),new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                dialog.dismiss();
                                if (e == null) {
                                    Toast.makeText(SettingActivity.this, "头像上传成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SettingActivity.this, "头像上传失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });
        }
    }


}
