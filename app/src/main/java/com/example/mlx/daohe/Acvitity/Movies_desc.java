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
 * 用途：
 */

public class Movies_desc extends BaseAcvitity {

    private JCVideoPlayerStandard item_movies_player;
    //    private TextView item_movies_MoviesName;
//    private TextView item_movies_star;
//    private TextView item_movies_wish;
//    private TextView item_movies_type;
//    private TextView item_movies_area;
//    private TextView item_movies_time;
//    private LinearLayout _item_movies_head_bg;
    private TextView item_desc;
    private RecyclerView item_movies_recycleView;
    private int moviesId;
    private String json;
    Movies_Adapter adapter;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_movies);
        initView();
        moviesId = getIntent().getIntExtra("id", -1);
        if (moviesId != -1) {
            getdata();
        }
    }

    private void initView() {
        item_movies_player = (JCVideoPlayerStandard) findViewById(R.id.item_movies_player);
//        item_movies_MoviesName = (TextView) findViewById(R.id.item_movies_MoviesName);
//        item_movies_star = (TextView) findViewById(R.id.item_movies_star);
//        item_movies_wish = (TextView) findViewById(R.id.item_movies_wish);
//        item_movies_type = (TextView) findViewById(R.id.item_movies_type);
//        item_movies_area = (TextView) findViewById(R.id.item_movies_area);
//        item_movies_time = (TextView) findViewById(R.id.item_movies_time);
//        _item_movies_head_bg = (LinearLayout) findViewById(R.id._item_movies_head_bg);
        item_desc = (TextView) findViewById(R.id.item_desc);
        item_movies_recycleView = (RecyclerView) findViewById(R.id.item_movies_recycleView);

    }

    public Object getdata() {
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

    private void preseJson() {
        Movies movies = new Gson().fromJson(json, Movies.class);
        Movies.DataBean.MovieDetailModelBean model = movies.getData().getMovieDetailModel();
        item_movies_player.setUp(model.getVd(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
//        item_movies_MoviesName.setText(model.getNm());
//        item_movies_star.setText(model.getSc()+"");
//        item_movies_wish.setText(model.getWish()+"人");
//        item_movies_type.setText(model.getCat());
//        item_movies_area.setText(model.getSrc()+"/"+model.getDur()+"分钟");
//        item_movies_time.setText(model.getRt());
        String dra = model.getDra();
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




    @Override
    public void onStop() {
        super.onStop();

    }

    public Object getThink() {
        String thinkURl = UtilS.getThinkURl(moviesId + "");
        RxVolley.get(thinkURl, new HttpCallback() {
            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
            }

            @Override
            public void onSuccess(String t) {
                MoviesThink think = new Gson().fromJson(t, MoviesThink.class);
                preseThinkJson(think);
            }


        });
        return null;
    }

    private void preseThinkJson(MoviesThink think) {
        adapter=new Movies_Adapter(this,think.getHcmts());
        item_movies_recycleView.setAdapter(adapter);
        item_movies_recycleView.setLayoutManager(new FullyLinearLayoutManager(this));
        item_movies_recycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        item_movies_recycleView.setItemAnimator(new DefaultItemAnimator());
    }


}
