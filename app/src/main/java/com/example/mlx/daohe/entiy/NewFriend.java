package com.example.mlx.daohe.entiy;



import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/21 20:39
 * 用途：
 */
@Entity
public class NewFriend extends BmobObject implements Serializable {

    private static final long serialVersionUID=1;
    @Id
    //用户uid
    private String uid;
    //留言消息
    private String msg;
    //用户名
    private String name;
    //头像
    private String avatar;
    //状态：未读、已读、已添加、已拒绝等
    private Integer status;
    //请求时间
    private Long time;
    @Generated(hash = 1550151525)
    public NewFriend(String uid, String msg, String name, String avatar,
            Integer status, Long time) {
        this.uid = uid;
        this.msg = msg;
        this.name = name;
        this.avatar = avatar;
        this.status = status;
        this.time = time;
    }
    @Generated(hash = 163516704)
    public NewFriend() {
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Long getTime() {
        return this.time;
    }
    public void setTime(Long time) {
        this.time = time;
    }


}
