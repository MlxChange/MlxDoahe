package com.example.mlx.daohe.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Utils
 * 创建者：MLX
 * 创建时间：2017/2/28 2:46
 * 用途：
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
