package com.example.mlx.daohe.entiy;

import android.widget.ImageView;

import java.io.File;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/19 17:44
 * 用途：
 */

public class MessageEntiy {

    private String imageView;
    private String name,message,time;

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
