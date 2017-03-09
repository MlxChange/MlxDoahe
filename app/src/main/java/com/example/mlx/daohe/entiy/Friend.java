package com.example.mlx.daohe.entiy;




import cn.bmob.v3.BmobObject;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/21 20:20
 * 用途：好友实体类
 */

public class Friend extends BmobObject {

    private MyUser user;
    private MyUser friend;

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public MyUser getFriend() {
        return friend;
    }

    public void setFriend(MyUser friend) {
        this.friend = friend;
    }
}
