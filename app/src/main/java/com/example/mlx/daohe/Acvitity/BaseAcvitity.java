package com.example.mlx.daohe.Acvitity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;

/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.Ui
 * 创建者：MLX
 * 创建时间：2017/1/31
 * 用途：Acvitity类的基类
 */

public class BaseAcvitity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
        if(getSupportActionBar()!=null){
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void back(View v) {
        finish();
    }
}
