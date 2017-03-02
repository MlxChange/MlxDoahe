package com.example.mlx.daohe.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mlx.daohe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：LxChange
 * 包名：com.example.administrator.lxchange.Fragment
 * 创建者：MLX
 * 创建时间：2017/2/11 17:01
 * 用途：
 */

public class IM_Fragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager pager;
    private List<Fragment> mfragment;
    private List<String> title;
    private Fragment fragment_message,fragment_friend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.im_fragment, container, false);

        tabLayout= (TabLayout) view.findViewById(R.id.im_tab);
        pager= (ViewPager) view.findViewById(R.id.im_viewpager);

        mfragment=new ArrayList<>();
        fragment_message=new fragment_message();
        fragment_friend=new fragment_friend();
        mfragment.add(fragment_message);
        mfragment.add(fragment_friend);
        title=new ArrayList<>();
        title.add("消息");
        title.add("好友");

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








}
