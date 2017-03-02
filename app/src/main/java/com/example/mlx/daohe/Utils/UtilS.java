package com.example.mlx.daohe.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.mlx.daohe.Adapter.FriendAdappter;
import com.example.mlx.daohe.entiy.Friend;
import com.example.mlx.daohe.entiy.MessageEntiy;
import com.example.mlx.daohe.entiy.MyUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.UtilTools
 * 创建者：MLX
 * 创建时间：2017/1/31
 * 用途：工具类
 */

public class UtilS {

    private static MyUser user;

    //设置字体
    public static void setFont(Context context, TextView textView) {
        Typeface asset = Typeface.createFromAsset(context.getAssets(), "font/Font.ttf");
        textView.setTypeface(asset);
    }

    //手机号验证
    public static boolean isPhone(String tel) {
        String telRegex = "[1][3578]\\d{9}";
        //L.i(tel.matches(telRegex)+"");
        return tel.matches(telRegex);
    }

    //用户名验证
    public static boolean isUsername(String username) {
        String usernameRegex = "^[a-zA-z][a-zA-Z0-9_]{6,15}$";
        return username.matches(usernameRegex);
    }

    //密码验证
    public static boolean isPass(String pass) {
        String passwordRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";
        return pass.matches(passwordRegex);
    }

    //邮箱验证
    public static boolean isEmail(String email) {
        String emailRegex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return email.matches(emailRegex);
    }

    //获取电影详情api地址
    public static String getMoviesURL(String id){
        String s="http://m.maoyan.com/movie/"+id+".json";
        return s;
    }
    //获取电影详情和影院详情api地址
    public static String getMoviesAndCinameURL(String cinemaid,String moviesid){
        String s="http://m.maoyan.com/showtime/wrap.json?cinemaid="+cinemaid+"&movieid="+moviesid;
        return s;
    }
    //获取餐饮数据api地址
    public static String getMeishiURL(double lng,double lat){
        String s=StaticClass.JuHe_Meishi+"?key="+StaticClass.JuHe_appID+"&lng="+lng+"&lat="+lat+"&radius=5000&page=10";
        return s;
    }
    //格式化时间
    public static String getTime(long time){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        Date date=new Date(time);
        String format = simpleDateFormat.format(date);
        return format;
    }
    //是否大于十分钟.
    public static boolean isOvertenMin(long time,long time2){
        long l = time - time2;
        long a=10*60*1000;
        if(l>a){
            return true;
        }else{
            return false;
        }
    }
    //判断时间间隔多久
    public static String getLiketime(long time){
        Date date=new Date(time);
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar.setTime(date);
        if(calendar1.get(Calendar.DAY_OF_MONTH)==calendar.get(Calendar.DAY_OF_MONTH)){
            return new SimpleDateFormat("hh:mm").format(date);
        }else if(calendar1.get(Calendar.YEAR)==calendar.get(Calendar.YEAR)){
            return new SimpleDateFormat("MM-dd").format(date);
        }else{
            return new SimpleDateFormat("yyyy年").format(date);
        }
    }


    public static void updateUserInfo(MessageEvent event) {
        final BmobIMConversation conversation = event.getConversation();
        final BmobIMUserInfo info = event.getFromUserInfo();
        final BmobIMMessage msg = event.getMessage();
        String username = info.getName();
        String title = conversation.getConversationTitle();
        L.i(username+",title:"+title);
        //sdk内部，将新会话的会话标题用objectId表示，因此需要比对用户名和会话标题--单聊，后续会根据会话类型进行判断
        if (!username.equals(title)) {
            BmobQuery<MyUser> query=new BmobQuery<>();
            query.addWhereEqualTo("objectId",info.getUserId());
            query.findObjects(new FindListener<MyUser>() {
                @Override
                public void done(List<MyUser> list, BmobException e) {
                    if(e==null){
                        user =list.get(0);
                        String fileUrl = user.getImgFileUrl();
                        if(!TextUtils.isEmpty(fileUrl)){
                            conversation.setConversationIcon(fileUrl);
                        }else{
                            conversation.setConversationIcon("");
                        }
                        conversation.setConversationTitle(user.getUsername());
                        //L.i("更新了会话信息");
                        BmobIM.getInstance().updateConversation(conversation);
                        BmobIM.getInstance().updateUserInfo(info);
                        if(!msg.isTransient()){
                            BmobIM.getInstance().updateConversation(conversation);
                        }
                    }
                }
            });

        }
    }

    //get用户信息
    public MyUser getFriends(String id) {
        BmobQuery<MyUser> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId", id);
        final MyUser[] user = new MyUser[1];
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                user[0] =list.get(0);
            }
        });
        return user[0];
    }
    //get猫眼评论URL
    public static String getThinkURl(String id){
        String url="http://api.maoyan.com/mmdb/comments/movie/v2/"+id+".json?offset=0&limit=20";
        return url;
    }
    //get知乎日报内容的api
    public static String getZhihuContentURL(String id){
        String s = StaticClass.ZhihuContentURL + id;
        return s;
    }

}
