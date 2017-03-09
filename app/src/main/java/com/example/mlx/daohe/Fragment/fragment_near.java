package com.example.mlx.daohe.Fragment;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.mlx.daohe.Adapter.NearAdapter;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.SharedUtils;
import com.example.mlx.daohe.db.NewFriendManager;
import com.example.mlx.daohe.db.dao.WantDao;
import com.example.mlx.daohe.entiy.Want;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Fragment
 * 创建者：MLX
 * 创建时间：2017/2/25 6:25
 * 用途：附近信息的fragment
 */

public class fragment_near extends Fragment implements AMapLocationListener, SwipeRefreshLayout.OnRefreshListener {

    //recycleview用来展示附近信息列表
    private RecyclerView near_recycleview;
    //neardapter
    private NearAdapter adapter;
    //邀请信息实体类
    private List<Want> mwantlist;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private LocationManager manager;
    //经度，维度
    private double jingdu, weidu;
    private Location location;
    //下拉刷新控件
    private SwipeRefreshLayout swipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.near_fragment, container, false);
        initView(view);
        //getdata();
        test();
        return view;
    }

    //调用定位方法取得经纬度
    private void test() {
//        jingdu=113.122927;
//        weidu=36.208622;
        for (int i = 0; i < 2; i++) {
            getJingAndWei();
            //getdata1(i);
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        L.i("nearOnstart:"+mwantlist.size());
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        L.i("nearOnstop:"+mwantlist.size());
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        L.i("nearonDestroy:"+mwantlist.size());
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        L.i("nearoonResume:"+mwantlist.size());
//    }

    private void initView(View view) {
        near_recycleview = (RecyclerView) view.findViewById(R.id.near_recycleview);
        near_recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        near_recycleview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        near_recycleview.setItemAnimator(new DefaultItemAnimator());
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.near_swipe);
        swipe.setOnRefreshListener(this);
        //mwantlist=new ArrayList<>();
    }

    public void getdata() {
//        String want = SharedUtils.getString(getContext(), "want", "");
//        if(!TextUtils.isEmpty(want)){
//            getORMdata();
//        }else{
//            getJingAndWei();
//            SharedUtils.putString(getContext(),"want","true");
//        }
        getJingAndWei();

    }

    //从服务器获取数据
    private void getdata1(final int i) {
        if (weidu != 0 && jingdu != 0) {
            BmobGeoPoint point = new BmobGeoPoint(jingdu, weidu);
            BmobQuery<Want> query = new BmobQuery<>();
            query.addWhereNear("gpsAdd", point);
            query.setLimit(20);
            query.findObjects(new FindListener<Want>() {
                @Override
                public void done(List<Want> list, BmobException e) {
                    if (swipe != null && swipe.isRefreshing()) {
                        swipe.setRefreshing(false);//ding
                    }
                    if (e == null) {
                        mwantlist = list;
//                        L.i(i + ">得到的want数量是：" + mwantlist.size());
//                        L.i(i + ">得到的数量是：" + list.size());
                        if (adapter == null) {
                            adapter = new NearAdapter(getActivity(), mwantlist);
                            near_recycleview.setAdapter(adapter);
                            //L.i(i+">adapter为null");
                        } else {
//                            mwantlist.clear();
                            mwantlist.addAll(list);
                            adapter.notifyDataSetChanged();
                            //L.i(i+">adapter不为null");
                        }

//                        WantDao dao = NewFriendManager.getInstance(getActivity()).getmDaoSession().getWantDao();
//                        for (int i = 0; i <list.size(); i++) {
//                            dao.insertOrReplace(list.get(i));
//                        }
                    } else {
                        L.i("near出错：" + e.getErrorCode() + "," + e.getMessage());
                        Toast.makeText(getContext(), "查询数据出错，请重试", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void getdata2() {

        BmobQuery<Want> query = new BmobQuery<>();
        query.findObjects(new FindListener<Want>() {
            @Override
            public void done(List<Want> list, BmobException e) {
                if (e == null) {
                    if (adapter == null) {
                        adapter = new NearAdapter(getContext(), mwantlist);
                        near_recycleview.setAdapter(adapter);
                    } else {
                        mwantlist.clear();
                        mwantlist.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
//                    WantDao dao = NewFriendManager.getInstance(getContext()).getmDaoSession().getWantDao();
//                    for (int i = 0; i < list.size(); i++) {
//                        dao.insertOrReplace(list.get(i));
//                    }
                    if (swipe != null && swipe.isRefreshing()) {
                        swipe.setRefreshing(false);
                    }
                } else {
                    L.i("near出错：" + e.getErrorCode() + "," + e.getMessage());
                    Toast.makeText(getActivity(), "查询数据出错，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //高德地图定位方法
    public void getJingAndWei() {
        mLocationClient = new AMapLocationClient(getContext());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //当定位响应后
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 0) {
            weidu = aMapLocation.getLatitude();//获取纬度
            jingdu = aMapLocation.getLongitude();//获取经度
            //L.i("onLocationChanged");
            //这里当定位成功后，调用方法，从服务器取得数据
            getdata1(0);
        } else {
            //如果定位失败的话，就获取所有数据
            //L.i(aMapLocation.getErrorCode() + "," + aMapLocation.getErrorInfo());
            Toast.makeText(getActivity(), "定位失败请重试", Toast.LENGTH_SHORT).show();
            getdata2();
        }
    }

    //下拉后重新获取数据
    @Override
    public void onRefresh() {
        test();
    }

    //存储数据，目前已废弃
//    public void getORMdata() {
//        WantDao dao = NewFriendManager.getInstance(getContext()).getmDaoSession().getWantDao();
//        List<Want> wantList = dao.loadAll();
//        if (wantList != null && wantList.size() > 0) {
//            adapter = new NearAdapter(getContext(), wantList);
//            near_recycleview.setAdapter(adapter);
//        } else {
//            getJingAndWei();
//        }
//    }
}
