package com.example.mlx.daohe.Acvitity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.entiy.ZhihuContentEntiy;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.List;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Acvitity
 * 创建者：MLX
 * 创建时间：2017/2/26 3:10
 * 用途：
 */

public class TestAcvitity extends AppCompatActivity {

    private double jingdu, weidu;
    private WebView mwebview;
    //private WebView test_webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        initView();
        test();
    }

    private void test() {

    }

    private void getdata1() {
        String url = "http://news-at.zhihu.com/api/4/news/9253411";
        ZhihuContentEntiy entiy;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                perserJson(t);
            }
        });
    }

    private String perserJson(String t) {
        ZhihuContentEntiy entiy = new Gson().fromJson(t, ZhihuContentEntiy.class);
        final String body = entiy.getBody();
        final List<String> css = entiy.getCss();
        RxVolley.get(css.get(0), new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Load(t, body);
            }
        });
//        test_webview.loadDataWithBaseURL(null,body,"text/html","utf-8",null);
//        test_webview.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                test_webview.loadData(css.get(0),"text/html","utf-8");
//                return true;
//            }
////        });
//        mwebview.loadDataWithBaseURL(null,body,"text/html","utf-8",null);
//        mwebview.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
//                mwebview.loadData(css.get(0),"text/html","utf-8");
//                return true;
//            }
//        });
        return body;
    }

    private void Load(final String t, String body) {
        L.i(t);
        //mwebview.loadDataWithBaseURL(null,body,"text/html","utf-8",null);
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + body + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        mwebview.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    private void initView() {
        //test_webview = (WebView) findViewById(R.id.test_webview);
        mwebview = (WebView) findViewById(R.id.webview);
        WebSettings settings = mwebview.getSettings();
        settings.setJavaScriptEnabled(true);
       //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
        getdata1();

    }
}
