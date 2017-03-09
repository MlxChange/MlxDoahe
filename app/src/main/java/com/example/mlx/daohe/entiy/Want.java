package com.example.mlx.daohe.entiy;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobGeoPoint;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/25 2:29
 * 用途：邀请信息实体类
 */
@Entity
public class Want extends BmobObject {

    @Id
    private long id;
    private String username;//发起人用户名
    private String friendname;//参加人用户名
    private String imgurl;
    private boolean state;//状态
    private String type;//类型
    private long requestTime;//发起时间
    private long responseTime;//完成时间
    private String message;//意向内容
    private double jingdu;//精度
    private double weidu;//纬度
    private String content;//具体内容
    @Transient
    private BmobGeoPoint gpsAdd;
    private String phonenumber;//发起人手机号






    @Generated(hash = 1056544753)
    public Want(long id, String username, String friendname, String imgurl,
            boolean state, String type, long requestTime, long responseTime,
            String message, double jingdu, double weidu, String content,
            String phonenumber) {
        this.id = id;
        this.username = username;
        this.friendname = friendname;
        this.imgurl = imgurl;
        this.state = state;
        this.type = type;
        this.requestTime = requestTime;
        this.responseTime = responseTime;
        this.message = message;
        this.jingdu = jingdu;
        this.weidu = weidu;
        this.content = content;
        this.phonenumber = phonenumber;
    }

    @Generated(hash = 270937821)
    public Want() {
    }




    

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public BmobGeoPoint getGpsAdd() {
        return gpsAdd;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setGpsAdd(BmobGeoPoint gpsAdd) {
        this.gpsAdd = gpsAdd;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendname() {
        return friendname;
    }

    public void setFriendname(String friendname) {
        this.friendname = friendname;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getJingdu() {
        return jingdu;
    }

    public void setJingdu(double jingdu) {
        this.jingdu = jingdu;
    }

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }

    public boolean getState() {
        return this.state;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
