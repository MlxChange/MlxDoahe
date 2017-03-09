package com.example.mlx.daohe.Acvitity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.mlx.daohe.Adapter.ZhihuAdapter;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.GlideImageLoader;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.entiy.FullyLinearLayoutManager;
import com.example.mlx.daohe.entiy.ZhihuEntiy;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Acvitity
 * 创建者：MLX
 * 创建时间：2017/2/28 2:20
 * 用途：知乎主页
 */

public class ZhihuMainAcivitity extends BaseAcvitity implements OnBannerListener, ZhihuAdapter.onItemClickLinster {

    //第三方轮播图控件
    private Banner zhihumain_banner;

    private RecyclerView zhihumain_recylview;

    private List<String> images,titles;
    private ZhihuAdapter adapter;
    ZhihuEntiy entiy;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihumain);
        initView();
        getdata();
    }

    private void getdata() {
        RxVolley.get(StaticClass.ZhihuUrl, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                parseJson(t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Toast.makeText(ZhihuMainAcivitity.this, "获取数据失败，请联网后重试:"+strMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseJson(String t) {
        entiy = new Gson().fromJson(t, ZhihuEntiy.class);
        List<ZhihuEntiy.TopStoriesBean> topStories = entiy.getTopStories();
        for (int i = 0; i < topStories.size(); i++) {
            images.add(topStories.get(i).getImage());
            titles.add(topStories.get(i).getTitle());
        }
        //轮播图的设置
        zhihumain_banner.setImages(images);
        zhihumain_banner.setBannerTitles(titles);
        zhihumain_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        zhihumain_banner.setOnBannerListener(this);
        zhihumain_banner.setIndicatorGravity(BannerConfig.CENTER);
        zhihumain_banner.start();

        adapter=new ZhihuAdapter(this,entiy.getStories());
        zhihumain_recylview.setAdapter(adapter);
        zhihumain_recylview.setLayoutManager(new FullyLinearLayoutManager(this));
        zhihumain_recylview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        zhihumain_recylview.setItemAnimator(new DefaultItemAnimator());
        adapter.addOnitemClickLinster(this);
    }

    private void initView() {
        zhihumain_banner = (Banner) findViewById(R.id.zhihumain_banner);
        zhihumain_recylview = (RecyclerView) findViewById(R.id.zhihumain_recylview);
        images=new ArrayList<>();
        titles=new ArrayList<>();
        zhihumain_banner.setImageLoader(new GlideImageLoader());
        dialog=new ProgressDialog(this);
        dialog.setTitle("请稍等哒");
        dialog.setMessage("努力加载中");
    }

    @Override
    protected void onStart() {
        super.onStart();
        zhihumain_banner.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止轮播图自动播放
        zhihumain_banner.stopAutoPlay();
    }

    //轮播图点击事件
    @Override
    public void OnBannerClick(int position) {
        if(entiy!=null){
            String s = entiy.getTopStories().get(position).getId() + "";
            Intent intent=new Intent(this,ZhihuContentAcvitity.class);
            intent.putExtra("id",s);
            dialog.show();
            startActivity(intent);
            dialog.dismiss();
        }
    }

    @Override
    public void onClick(View v, int position) {
        if(entiy!=null){
            String s = entiy.getStories().get(position).getId() + "";
            Intent intent=new Intent(this,ZhihuContentAcvitity.class);
            intent.putExtra("id",s);
            dialog.show();
            startActivity(intent);
            dialog.dismiss();
        }
    }
}
