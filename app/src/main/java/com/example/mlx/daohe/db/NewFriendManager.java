package com.example.mlx.daohe.db;


import android.content.Context;

import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.db.dao.DaoMaster;
import com.example.mlx.daohe.db.dao.DaoSession;
import com.example.mlx.daohe.db.dao.NewFriendDao;
import com.example.mlx.daohe.entiy.NewFriend;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/4/27.
 */
public class NewFriendManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private Context mcontext;
    private static NewFriendManager manager = null;

    private NewFriendManager(Context context) {
        mcontext=context.getApplicationContext();
        init();
    }

    public static NewFriendManager getInstance(Context context){
        if(manager==null){
            manager=new NewFriendManager(context);
            return manager;
        }else{
            return manager;
        }
    }



    private void init() {
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(mcontext,"newfriend",null);
        mDaoMaster = new DaoMaster(helper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoMaster getmDaoMaster()
    {
        return mDaoMaster;
    }
    public DaoSession getmDaoSession()
    {
        return mDaoSession;
    }
    public DaoSession getNewSession()
    {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }


    /**获取本地所有的邀请信息
     * @return
     */
    public  List<NewFriend> getAllNewFriend(){
        NewFriendDao dao = mDaoSession.getNewFriendDao();
        final List<NewFriend>[] newFriends = new List[]{dao.loadAll()};
        if(newFriends[0].size()==0){
            BmobQuery<NewFriend> query = new BmobQuery<>();
            String sql="select *from newfriend";
            query.setSQL(sql);
            query.doSQLQuery(new SQLQueryListener<NewFriend>() {
                @Override
                public void done(BmobQueryResult<NewFriend> bmobQueryResult, BmobException e) {
                    if(e==null){
                        newFriends[0] = bmobQueryResult.getResults();
                    }
                }
            });
        }
        return newFriends[0];
    }

    //判断是否是新的好友邀请
    public boolean isNewFriends(NewFriend friend){
        QueryBuilder<NewFriend> builder = mDaoSession.getNewFriendDao().queryBuilder();
        List<NewFriend> list = builder.where(NewFriendDao.Properties.Uid.eq(friend.getUid())).list();
        L.i(list.size()+"");
        if(list.size()>0){
            return false;
        }else{
            return true;
        }
    }

    //存储本地和服务器邀请信息
    public void insertNreFriends(NewFriend friend){
        mDaoSession.getNewFriendDao().insertOrReplace(friend);
        friend.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

            }
        });

    }
    //修改指定好友请求状态
    public void updateNewFriend(NewFriend friend,int state) {
        friend.setStatus(state);
        mDaoSession.getNewFriendDao().insertOrReplace(friend);
        friend.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e!=null){
                    L.i(e.getErrorCode()+","+e.getMessage());
                }
            }
        });
    }



}
