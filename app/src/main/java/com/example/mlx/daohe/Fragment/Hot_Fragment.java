package com.example.mlx.daohe.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.example.mlx.daohe.Acvitity.AddNewWant;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：LxChange
 * 包名：com.example.administrator.lxchange.Fragment
 * 创建者：MLX
 * 创建时间：2017/2/11 17:01
 * 用途：热门的fragment
 */

public class Hot_Fragment extends Fragment implements View.OnClickListener {


    private TabLayout tabLayout;//tableyout 用来和ViewPager结合起来切换fragment
    private ViewPager pager;//Viewpager容器，用来装载电影和美食
    private List<Fragment> mfragment;//list容器，装载frament
    private List<String> title;//fragemtn的title，为电影和美食
    private Fragment fragment_movies,fragment_meishi;//电影fragment，美食fragment
    private FloatingActionButton button;//添加邀请按钮

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view = inflater.inflate(R.layout.hot_fragment, container, false);
        //获得权限
        getpression();
        tabLayout= (TabLayout) view.findViewById(R.id.hot_movies_tab);
        pager= (ViewPager) view.findViewById(R.id.hot_movies_viewpager);
        button= (FloatingActionButton) view.findViewById(R.id.hot_add);
        //为添加按钮添加点击事件
        button.setOnClickListener(this);
        //初始化容器并且装载到list中
        mfragment=new ArrayList<>();
        fragment_movies=new fragment_movies();
        fragment_meishi=new fragment_meishi();
        mfragment.add(fragment_movies);
        mfragment.add(fragment_meishi);
        title=new ArrayList<>();
        title.add("电影");
        title.add("美食");

        pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            //返回要得到的fragment
            @Override
            public Fragment getItem(int position) {
                return mfragment.get(position);
            }

            @Override
            public int getCount() {
                return mfragment.size();
            }
            //返回标题，这里这样使用，不会造成不显示问题
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        });
        //metraial design 库中的tablayout可以直接设置绑定Viewpager
        tabLayout.setupWithViewPager(pager);
        return view;
    }

    //6.0权限检测与申请
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        } else {
            Toast.makeText(getContext(), "您已拒绝授予权限，无法得到您所在位置", Toast.LENGTH_SHORT).show();
        }
    }

    //点击了添加按钮以后，跳转界面
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), AddNewWant.class);
        startActivity(intent);
    }

    //6.0权限检测与申请
    public void getpression() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
        }
    }

}
