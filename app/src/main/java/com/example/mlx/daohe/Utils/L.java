package com.example.mlx.daohe.Utils;

import android.util.Log;

/**
 * 项目名：Test2
 * 包名：com.example.administrator.lxchange.Utils
 * 创建者：MLX
 * 创建时间：2017/2/1 20:41
 * 用途：LOG封装类
 */

public class L {

    public static final String TAG="Daohe";
    public static final boolean DEBUG=false;


    public static void i(String text){
        if(DEBUG){
            Log.i(TAG, text);
        }
    }



}
