package com.example.mlx.daohe.Acvitity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.ZhihuContentEntiy;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Acvitity
 * 创建者：MLX
 * 创建时间：2017/2/28 3:05
 * 用途：知乎详情页面
 */

public class ZhihuContentAcvitity extends BaseAcvitity {

    private ImageView zhihucontent_bg;

    private Toolbar zhihucontent_toolbar;
    private AppBarLayout zhihucontent_bar;
    //x5内核的webview
    private WebView zhihucontent_webview;
    private String id;
    private CollapsingToolbarLayout toolbarLayout;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_content);
        id = getIntent().getStringExtra("id");
        initView();
        getdata();
    }

    private void getdata() {
        dialog.show();
        if(!TextUtils.isEmpty(id)){
            String contentURL = UtilS.getZhihuContentURL(id);
            //L.i(contentURL);
            RxVolley.get(contentURL, new HttpCallback() {
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    Toast.makeText(ZhihuContentAcvitity.this, "请求数据失败，请联网后重试", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(String t) {
                    parseJson(t);
                }
            });
        }
    }

    private void parseJson(String t) {
        //解析json字符串为实体类
        ZhihuContentEntiy entiy = new Gson().fromJson(t, ZhihuContentEntiy.class);
        String imgurl = entiy.getImages().get(0);
        Glide.with(this).load(imgurl).into(zhihucontent_bg);
        toolbarLayout.setTitle(entiy.getTitle());

        WebSettings settings = zhihucontent_webview.getSettings();
        settings.setJavaScriptEnabled(true);
        //加载本地css文件
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + entiy.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        zhihucontent_webview.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
        dialog.dismiss();
    }

    private void initView() {
        zhihucontent_bg = (ImageView) findViewById(R.id.zhihucontent_bg);
        zhihucontent_toolbar = (Toolbar) findViewById(R.id.zhihucontent_toolbar);
        zhihucontent_bar = (AppBarLayout) findViewById(R.id.zhihucontent_bar);
        zhihucontent_webview = (WebView) findViewById(R.id.zhihucontent_webview);
        toolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.coolbar);
        setSupportActionBar(zhihucontent_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dialog=new ProgressDialog(this);
        dialog.setTitle("请稍等哒");
        dialog.setMessage("努力加载中");
    }
}
