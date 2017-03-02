package com.example.mlx.daohe.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名：Test2
 * 包名：com.example.administrator.lxchange.Utils
 * 创建者：MLX
 * 创建时间：2017/2/1 20:57
 * 用途：SharedPreferences工具类
 */

public class SharedUtils {

    public static final String Name="Daohe";

    public static void putString(Context context,String key,String value){
        SharedPreferences preferences = context.getSharedPreferences(Name, Context.MODE_PRIVATE);
        preferences.edit().putString(key,value).commit();
    }
    public static String getString(Context context,String key,String devalue){
        SharedPreferences preferences = context.getSharedPreferences(Name, Context.MODE_PRIVATE);
        return preferences.getString(key,devalue);
    }
    public static void putInt(Context context,String key,int value){
        SharedPreferences preferences = context.getSharedPreferences(Name, Context.MODE_PRIVATE);
        preferences.edit().putInt(key,value).commit();
    }
    public static int getInt(Context context,String key,int devalue){
        SharedPreferences preferences = context.getSharedPreferences(Name, Context.MODE_PRIVATE);
        return preferences.getInt(key,devalue);
    }
    public static void putBool(Context context,String key,boolean value){
        SharedPreferences preferences = context.getSharedPreferences(Name, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key,value).commit();
    }
    public static boolean getBool(Context context,String key,boolean devalue){
        SharedPreferences preferences = context.getSharedPreferences(Name, Context.MODE_PRIVATE);
        return preferences.getBoolean(key,devalue);
    }

}
