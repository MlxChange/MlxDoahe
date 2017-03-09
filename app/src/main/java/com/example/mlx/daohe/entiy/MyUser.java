package com.example.mlx.daohe.entiy;


import org.greenrobot.greendao.annotation.Entity;

import java.io.File;

import cn.bmob.v3.BmobUser;

/**
 * 项目名：Test2
 * 包名：com.mlx.smartmlx.entiy
 * 创建者：MLX
 * 创建时间：2017/2/4 15:34
 * 用途：用户信息实体类
 */

public class MyUser extends BmobUser {


    private int age;//年龄
    private boolean sex;//性别
    private String desc;//简介
    private String imgFileUrl;
    private String requestWant;
    private String memberWant;
    private String requestThink;
    private String responseThink;
    private String QQ;
    private String mypass;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getImgFileUrl() {
        return imgFileUrl;
    }

    public void setImgFileUrl(String imgFileUrl) {
        this.imgFileUrl = imgFileUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRequestWant() {
        return requestWant;
    }

    public void setRequestWant(String requestWant) {
        this.requestWant = requestWant;
    }

    public String getMemberWant() {
        return memberWant;
    }

    public void setMemberWant(String memberWant) {
        this.memberWant = memberWant;
    }

    public String getRequestThink() {
        return requestThink;
    }

    public void setRequestThink(String requestThink) {
        this.requestThink = requestThink;
    }

    public String getResponseThink() {
        return responseThink;
    }

    public void setResponseThink(String responseThink) {
        this.responseThink = responseThink;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getMypass() {
        return mypass;
    }

    public void setMypass(String mypass) {
        this.mypass = mypass;
    }
}
