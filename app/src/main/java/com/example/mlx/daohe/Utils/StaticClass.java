package com.example.mlx.daohe.Utils;

/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.UtilTools
 * 创建者：MLX
 * 创建时间：2017/2/1 21:57
 * 用途：静态变量工具类
 */

public class StaticClass {

    //在闪屏页中默认Handler的what消息
    public static final int HAND_SPLASH=0217;
    //在SharedPreferences中存储是否第一次打开应用的键名
    public static final String SPLASH_ISFIRST="isFirst";
    //腾讯Bugly应用的appID
    public static final String BuglyAppID="b8f1aae714";
    //BOMB后端云的AppID
    public static final String BmobAppID="dac2faee7190d653e2f3a7da2521fa61";
    //猫眼API首页接口地址
    public static final String HOT_URL="http://m.maoyan.com/movie/list.json?type=hot&offset=0&limit=1000";
    //聚合餐饮数据接口
    public static  final String JuHe_Meishi="http://apis.juhe.cn/catering/query";
    //聚合餐饮数据接口AppID
    public static final String JuHe_appID="7bd22cbf3adb7abc8fe1d01b0a9eb5fe";
    //高德地图key
    public static final String Gaode_appID="24e9b6cc1f63ef9f564f2d15f62d2c33";
    //IMSDK APP KEY
    public static final String IMSDK_APPID="70d835f457c4d668c1dd166a";
    //是否是debug模式
    public static final boolean DEBUG=true;
    //好友请求：未读-未添加->接收到别人发给我的好友添加请求，初始状态
    public static final int STATUS_VERIFY_NONE=0;
    //好友请求：已读-未添加->点击查看了新朋友，则都变成已读状态
    public static final int STATUS_VERIFY_READED=2;
    //好友请求：已添加
    public static final int STATUS_VERIFIED=1;
    //好友请求：拒绝
    public static final int STATUS_VERIFY_REFUSE=3;
    //好友请求：我发出的好友请求-暂未存储到本地数据库中
    public static final int STATUS_VERIFY_ME_SEND=4;
    //小红点ID
    public static final int MESSAGE_ID=1;
    public static final int Address_ID=2;
    //知乎日报的API
    public static final String ZhihuUrl="http://news-at.zhihu.com/api/4/news/latest";
    //知乎日报内容的URL
    public static final String ZhihuContentURL="http://news-at.zhihu.com/api/4/news/";


}
