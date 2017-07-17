package com.example.mlx.daohe.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.mlx.daohe.Adapter.Hot_meishi_Adapter;
import com.example.mlx.daohe.Adapter.Hot_movies_Adapter;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.SharedUtils;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.Hot_Meishi;
import com.example.mlx.daohe.entiy.Hot_Movies;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 项目名：LxChange
 * 包名：com.example.administrator.lxchange.Fragment
 * 创建者：MLX
 * 创建时间：2017/2/11 19:06
 * 用途：美食Fragment
 */

public class fragment_meishi extends Fragment implements AMapLocationListener {

    private Context mcontext;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //listview，用来展示美食列表
    private ListView listView;
    //美食adapter
    private Hot_meishi_Adapter adapter;
    //美食实体类
    private Hot_Meishi hot_meishi;
    LocationManager manager;
    //经度和纬度
    private double lng, lat;
    //地理位置提供者字符串
    private String locationProvider;
    private Location location;
    //进度条对话框
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meishi, container, false);
        listView = (ListView) view.findViewById(R.id.hot_meishi_listview);
        manager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        mcontext = getContext();
        dialog=new ProgressDialog(mcontext);
        dialog.setTitle("请稍等哒");
        dialog.setMessage("努力加载中");
        getPression();
        //requestFormAPI();

        return view;
    }


    //从接口获取数据
    private void requestFormAPI() {
        RxVolley.get(UtilS.getMeishiURL(lng, lat), new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //将得到的json字符串通过shared保存起来，供添加邀请信息界面调用
                hot_meishi = new Gson().fromJson(t, Hot_Meishi.class);
                SharedUtils.putString(getActivity().getApplicationContext(),"meishi",t);
                adapter = new Hot_meishi_Adapter(getContext(), hot_meishi);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Toast.makeText(getActivity(), "获取数据失败，请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        dialog.dismiss();
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            getGaodeLocation();
        } else {
            Toast.makeText(getContext(), "您已拒绝授予权限，无法得到您所在位置", Toast.LENGTH_SHORT).show();
        }
    }

    //申请6.0动态权限:
    public void getPression() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //dialog.show();
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            getGaodeLocation();
        }
    }

    //当获取到位置后，调用requestFormAPI方法，查询数据
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        dialog.dismiss();
        if (aMapLocation.getErrorCode() == 0) {
            lat = aMapLocation.getLatitude();//获取纬度
            lng = aMapLocation.getLongitude();//获取经度
            requestFormAPI();
        } else {
            //L.i(aMapLocation.getErrorCode() + "," + aMapLocation.getErrorInfo());
            Toast.makeText(mcontext, "定位失败请重试", Toast.LENGTH_SHORT).show();
        }
    }



    public void getGaodeLocation() {
        dialog.show();
        mLocationClient = new AMapLocationClient(getContext());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
}
