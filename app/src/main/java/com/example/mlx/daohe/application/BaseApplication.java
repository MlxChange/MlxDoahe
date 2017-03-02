package com.example.mlx.daohe.application;

import android.app.Application;


import com.example.mlx.daohe.MyReceive.MyReceive;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.db.NewFriendManager;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.http.RequestQueue;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;


/**
 * 项目名：Test2
 * 包名：com.example.administrator.lxchange.application
 * 创建者：MLX
 * 创建时间：2017/1/31
 * 用途：BaseApplication
 */

public class BaseApplication extends LitePalApplication {

    private static BaseApplication INSTANCE;
    public static BaseApplication INSTANCE(){
        return INSTANCE;
    }
    private void setInstance(BaseApplication app) {
        setBmobIMApplication(app);
    }
    private static void setBmobIMApplication(BaseApplication a) {
        BaseApplication.INSTANCE = a;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库对象
        NewFriendManager.getInstance(this);
        //启动腾讯bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BuglyAppID,true);
        //启动bomg后端云服务
        Bmob.initialize(this, StaticClass.BmobAppID);
        //替换RxVolley中的Volley为OKhttp3
        //RxVolley.setRequestQueue(RequestQueue.newRequestQueue(RxVolley.CACHE_FOLDER,
          //      new OkHttpStack(new OkHttpClient())));
        //加载litepal
        LitePal.initialize(this);
        //BombIm初始化
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            //im初始化
            BmobIM.init(this);
            //注册消息接收器
            BmobIM.registerDefaultMessageHandler(new MyReceive(this));
        }
    }

    /**
     * 获取当前运行的进程名
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
