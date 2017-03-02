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
 * 用途：
 */

public class Hot_Fragment extends Fragment implements View.OnClickListener {


    private TabLayout tabLayout;
    private ViewPager pager;
    private List<Fragment> mfragment;
    private List<String> title;
    private Fragment fragment_movies,fragment_meishi;
    private FloatingActionButton button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_fragment, container, false);
        getpression();
        tabLayout= (TabLayout) view.findViewById(R.id.hot_movies_tab);
        pager= (ViewPager) view.findViewById(R.id.hot_movies_viewpager);
        button= (FloatingActionButton) view.findViewById(R.id.hot_add);
        button.setOnClickListener(this);
        mfragment=new ArrayList<>();
        fragment_movies=new fragment_movies();
        fragment_meishi=new fragment_meishi();
        mfragment.add(fragment_movies);
        mfragment.add(fragment_meishi);
        title=new ArrayList<>();
        title.add("电影");
        title.add("美食");

        pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mfragment.get(position);
            }

            @Override
            public int getCount() {
                return mfragment.size();
            }
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        });
        tabLayout.setupWithViewPager(pager);
        return view;
    }

    public void addMoviesOrMeishi(View v){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        } else {
            Toast.makeText(getContext(), "您已拒绝授予权限，无法得到您所在位置", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), AddNewWant.class);
        startActivity(intent);
    }

    public void getpression() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
        }
    }

}
