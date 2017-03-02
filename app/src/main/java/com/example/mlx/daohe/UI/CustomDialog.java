package com.example.mlx.daohe.UI;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mlx.daohe.R;


/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.Ui
 * 创建者：MLX
 * 创建时间：2017/2/6 19:31
 * 用途：
 */

public class CustomDialog extends Dialog {

    //定义模板
    public CustomDialog(Context context, int layout, int style) {
        this(context,WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,layout,style,Gravity.CENTER);
    }
    //定义属性
    public CustomDialog(Context context,int width, int height, int layout, int style, int gravity, int anim){
        super(context,style);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity=gravity;
        window.setAttributes(params);
        window.setWindowAnimations(anim);
    }
    //定义实例
    public CustomDialog(Context context,int width, int height, int layout, int style,int gravity){
       this(context,width,height,layout,style,gravity, R.style.pop);

    }
}
