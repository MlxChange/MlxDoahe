package com.example.mlx.daohe.Acvitity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.UtilS;

/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.view
 * 创建者：MLX
 * 创建时间：2017/2/1 21:47
 * 用途：
 */

public class GuideActivity extends BaseAcvitity {

    private ViewPager pager;//ViewPager
    private List<View> mview;//View集合，用于装pager里的View
    private ImageView point1,point2,point3;//三个小圆点
    private View view1,view2,view3;//三个引导页的View
    private ImageView tiaoguo;//跳过图片
    private Button button;//第三页中的进入按钮

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initView();
    }

    //初始化View
    private void initView() {
        pager= (ViewPager) findViewById(R.id.giude_viewpager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //监听ViewPager选中事件
            @Override
            public void onPageSelected(int position) {
                setPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        point1= (ImageView) findViewById(R.id.giude_cirl1);
        point2= (ImageView) findViewById(R.id.giude_cirl2);
        point3= (ImageView) findViewById(R.id.giude_cirl3);

        //装载ViewPager中的三个引导View
        view1=View.inflate(this,R.layout.giude_item1,null);
        view2=View.inflate(this,R.layout.giude_item2,null);
        view3=View.inflate(this,R.layout.giude_item3,null);
        Glide.with(this).load(R.drawable.giude_item1).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                view1.setBackground(resource);
            }
        });
        Glide.with(this).load(R.drawable.giude_item2).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                view2.setBackground(resource);
            }
        });
        Glide.with(this).load(R.drawable.giude_item3).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                view3.setBackground(resource);
            }
        });
        UtilS.setFont(this,(TextView)view1.findViewById(R.id.giude_item1_tv));
        UtilS.setFont(this,(TextView)view2.findViewById(R.id.giude_item2_tv));
        UtilS.setFont(this,(TextView)view3.findViewById(R.id.giude_item3_tv));
        mview=new ArrayList<>();
        mview.add(view1);
        mview.add(view2);
        mview.add(view3);

        tiaoguo= (ImageView) findViewById(R.id.giude_tiaoguo);
        button= (Button) view3.findViewById(R.id.guide_item3_startButton);

        pager.setAdapter(new MyAdapter());
        setPoint(0);//设置ViewPager中进入默认显示第一页
    }

    //跳过图片和进入主页的按钮监听事件
    public void GoMain(View v){
        startActivity(new Intent(GuideActivity.this, LoginAcvitity.class));
        finish();
    }

    //设置小圆点图片方法
    private void setPoint(int position){
        switch (position){
            case 0:{
                tiaoguo.setVisibility(View.VISIBLE);
                point1.setImageResource(R.drawable.point_on);
                point2.setImageResource(R.drawable.point_off);
                point3.setImageResource(R.drawable.point_off);
                break;
            }
            case 1:{
                tiaoguo.setVisibility(View.VISIBLE);
                point1.setImageResource(R.drawable.point_off);
                point2.setImageResource(R.drawable.point_on);
                point3.setImageResource(R.drawable.point_off);
                break;
            }
            case 2:{
                tiaoguo.setVisibility(View.GONE);
                point1.setImageResource(R.drawable.point_off);
                point2.setImageResource(R.drawable.point_off);
                point3.setImageResource(R.drawable.point_on);
                break;
            }
        }
    }

    //Viewpager的自定义适配器类
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mview.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //ViewPager用来装载View
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(mview.get(position));
            return mview.get(position);
        }

        //ViewPager用来remove掉View
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(mview.get(position));
        }
    }


}
