package com.example.mlx.daohe.Acvitity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.SharedUtils;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.Hot_Meishi;
import com.example.mlx.daohe.entiy.Hot_Movies;
import com.example.mlx.daohe.entiy.Movies;
import com.example.mlx.daohe.entiy.MyUser;
import com.example.mlx.daohe.entiy.Want;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Acvitity
 * 创建者：MLX
 * 创建时间：2017/2/25 3:26
 * 用途：
 */

public class AddNewWant extends BaseAcvitity implements MaterialSpinner.OnItemSelectedListener, AMapLocationListener {

    private ImageView addnewwant_bg;
    private CircleImageView addnewwant_cirlImage;
    private MaterialSpinner addnewwant_type;
    private MaterialSpinner addnewwant_content;
    private MaterialSpinner addnewwant_type2;
    private EditText addnewwant_desc;
    private ImageView addnewwant_ok;
    private List<String> moviesName;
    private List<String> meishiName;
    Hot_Movies hot_movie;
    private Hot_Meishi hot_meishi;
    private List<String> type1;
    private List<String> type2;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private LocationManager manager;
    private double jingdu,weidu;
    private Location location;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init();
        setContentView(R.layout.addwant);
        initView();
        String json = SharedUtils.getString(getApplicationContext(), "movies", "");
        if(!TextUtils.isEmpty(json)){
            hot_movie = new Gson().fromJson(json, Hot_Movies.class);
            List<Hot_Movies.DataBean.MoviesBean> movies = hot_movie.getData().getMovies();
            for (int i = 0; i < movies.size(); i++) {
                meishiName.add(movies.get(i).getNm());
            }
        }
        String json2 = SharedUtils.getString(getApplicationContext(), "meishi", "");
        if(!TextUtils.isEmpty(json)){
            hot_meishi = new Gson().fromJson(json2, Hot_Meishi.class);
            List<Hot_Meishi.ResultBean> meishi = hot_meishi.getResult();
            for (int i = 0; i < meishi.size(); i++) {
                moviesName.add(meishi.get(i).getName());
            }
        }
    }



    private void initView() {
        addnewwant_bg = (ImageView) findViewById(R.id.addnewwant_bg);
        Glide.with(this).load(R.drawable.splash).into(addnewwant_bg);
        addnewwant_cirlImage = (CircleImageView) findViewById(R.id.addnewwant_cirlImage);
        Glide.with(this).load(BmobUser.getCurrentUser(MyUser.class).getImgFileUrl()).error(R.drawable.error).into(addnewwant_cirlImage);
        addnewwant_type = (MaterialSpinner) findViewById(R.id.addnewwant_type);
        addnewwant_content = (MaterialSpinner) findViewById(R.id.addnewwant_content);
        addnewwant_type2 = (MaterialSpinner) findViewById(R.id.addnewwant_type2);
        addnewwant_desc = (EditText) findViewById(R.id.addnewwant_desc);
        addnewwant_ok = (ImageView) findViewById(R.id.addnewwant_ok);
        moviesName=new ArrayList<>();
        meishiName=new ArrayList<>();
        type1=new ArrayList<>();
        type2=new ArrayList<>();
        type1.add("电影");
        type1.add("美食");
        type2.add("请客");
        type2.add("AA");
        type2.add("参与");
        addnewwant_type.setItems(type1);
        addnewwant_type.setOnItemSelectedListener(this);
        addnewwant_type2.setItems(type2);
        addnewwant_type2.setOnItemSelectedListener(this);
        addnewwant_content.setOnItemSelectedListener(this);
        dialog=new ProgressDialog(this);
        dialog.setTitle("正在提交");
    }



    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        switch (view.getId()){
            case R.id.addnewwant_type:{
                if(position==0){
                    if(moviesName!=null){
                        addnewwant_content.setItems(meishiName);
                    }
                }else{
                    if(meishiName!=null&&meishiName.size()>0){
                        addnewwant_content.setItems(moviesName);
                    }
                }
                break;
            }
        }
    }

    public void ok(View v){
        getJingAndWei();
    }

    private void request() {
        String typestring = type1.get(addnewwant_type.getSelectedIndex());
        String type2string = type2.get(addnewwant_type2.getSelectedIndex());
        String content;
        if(typestring.equals("电影")){
            content=moviesName.get(addnewwant_content.getSelectedIndex());
        }else{
            content=meishiName.get(addnewwant_content.getSelectedIndex());
        }
        String desc = addnewwant_desc.getText().toString().trim();
        if (TextUtils.isEmpty(desc)) {
            desc="大家好";
        }
        Want want=new Want();
        want.setUsername(BmobUser.getCurrentUser(MyUser.class).getUsername());
        want.setContent(typestring+":"+content);
        want.setMessage(desc);
        want.setRequestTime(System.currentTimeMillis());
        want.setState(false);
        want.setType(BmobUser.getCurrentUser(MyUser.class).getUsername()+" : "+type2string);
        want.setGpsAdd(new BmobGeoPoint(jingdu,weidu));
        want.setJingdu(jingdu);
        want.setWeidu(weidu);
        if(!TextUtils.isEmpty(BmobUser.getCurrentUser(MyUser.class).getImgFileUrl())){
            want.setImgurl(BmobUser.getCurrentUser(MyUser.class).getImgFileUrl());
        }else{
            want.setImgurl("");
        }
        want.setPhonenumber(BmobUser.getCurrentUser(MyUser.class).getMobilePhoneNumber());
        dialog.show();
        want.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                dialog.dismiss();
                if(e==null){
                    finish();
                    Toast.makeText(AddNewWant.this, "创建邀请成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddNewWant.this, "创建邀请失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                    addnewwant_desc.setText("");
                }
            }
        });
    }

    public void getJingAndWei() {
        mLocationClient = new AMapLocationClient(this);
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 0) {
            weidu = aMapLocation.getLatitude();//获取纬度
            jingdu = aMapLocation.getLongitude();//获取经度
            //L.i("经度："+jingdu+",维度："+weidu);
            request();
        } else {
            //L.i(aMapLocation.getErrorCode() + "," + aMapLocation.getErrorInfo());
            Toast.makeText(this, "定位失败请重试", Toast.LENGTH_SHORT).show();
        }
    }
}
