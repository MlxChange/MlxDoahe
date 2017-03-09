package com.example.mlx.daohe.entiy;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：LxChange
 * 包名：com.example.administrator.lxchange.entiy
 * 创建者：MLX
 * 创建时间：2017/2/11 0:50
 * 用途：电影评论
 */

public class Think extends BmobObject{

    private String username;//评价人姓名
    private String friednname;//被评价人姓名
    private String thinkDate;//评价时间
    private int leavel;//评价等级
    private String message;//评价信息

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriednname() {
        return friednname;
    }

    public void setFriednname(String friednname) {
        this.friednname = friednname;
    }

    public String getThinkDate() {
        return thinkDate;
    }

    public void setThinkDate(String thinkDate) {
        this.thinkDate = thinkDate;
    }

    public int getLeavel() {
        return leavel;
    }

    public void setLeavel(int leavel) {
        this.leavel = leavel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
