package com.example.mlx.daohe.Acvitity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mlx.daohe.Adapter.Movies_Adapter;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.FullyLinearLayoutManager;
import com.example.mlx.daohe.entiy.Movies;
import com.example.mlx.daohe.entiy.MoviesThink;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Acvitity
 * 创建者：MLX
 * 创建时间：2017/2/27 17:21
 * 用途：电影详情的acvitity
 */

public class Movies_desc extends BaseAcvitity {

    //第三放播放器控件
    private JCVideoPlayerStandard item_movies_player;
   //电影详情
    private TextView item_desc;
    //展示评论的recycleview
    private RecyclerView item_movies_recycleView;
    private int moviesId;
    private String json;
    Movies_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_movies);
        initView();
        //获得电影的id
        moviesId = getIntent().getIntExtra("id", -1);
        if (moviesId != -1) {
            getdata();
        }
    }

    private void initView() {
        item_movies_player = (JCVideoPlayerStandard) findViewById(R.id.item_movies_player);
        item_desc = (TextView) findViewById(R.id.item_desc);
        item_movies_recycleView = (RecyclerView) findViewById(R.id.item_movies_recycleView);

    }

    public Object getdata() {
        //获得电影详情
        RxVolley.get(UtilS.getMoviesURL(moviesId + ""), new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                json = t;
                if (!TextUtils.isEmpty(json)) {
                    preseJson();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Toast.makeText(Movies_desc.this, "读取数据失败，请联网重试:" + errorNo, Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    //解析获得到的json字符串
    private void preseJson() {
        //解析为movies实体类
        Movies movies = new Gson().fromJson(json, Movies.class);

        Movies.DataBean.MovieDetailModelBean model = movies.getData().getMovieDetailModel();
        item_movies_player.setUp(model.getVd(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);

        String dra = model.getDra();
        //获得电影详细介绍，并且去掉<p>标签
        String valueOf = String.copyValueOf(dra.toCharArray(), 3, dra.length() - 7);
        item_desc.setText(valueOf);

        getThink();
    }

    @Override
    public void onBackPressed() {
        if (item_movies_player.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        item_movies_player.releaseAllVideos();
    }


    //获得评论详情
    public Object getThink() {
        String thinkURl = UtilS.getThinkURl(moviesId + "");
        //获得评论json字符串
        RxVolley.get(thinkURl, new HttpCallback() {
            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
            }

            @Override
            public void onSuccess(String t) {
                //解析为评论实体类
                MoviesThink think = new Gson().fromJson(t, MoviesThink.class);
                //设置评论
                preseThinkJson(think);
            }


        });
        return null;
    }

    //设置评论recycleview
    private void preseThinkJson(MoviesThink think) {
        adapter=new Movies_Adapter(this,think.getHcmts());
        item_movies_recycleView.setAdapter(adapter);
        item_movies_recycleView.setLayoutManager(new FullyLinearLayoutManager(this));
        item_movies_recycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        item_movies_recycleView.setItemAnimator(new DefaultItemAnimator());
    }


}
