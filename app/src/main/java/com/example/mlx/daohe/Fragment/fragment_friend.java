package com.example.mlx.daohe.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;
import com.example.mlx.daohe.Acvitity.ChatAcvitity;
import com.example.mlx.daohe.Acvitity.FindFriends;
import com.example.mlx.daohe.Adapter.FriendAdappter;
import com.example.mlx.daohe.MessageEvent.MyEvent;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.entiy.Friend;
import com.example.mlx.daohe.entiy.MyUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Fragment
 * 创建者：MLX
 * 创建时间：2017/2/17 23:52
 * 用途：
 */

public class fragment_friend extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private List<Friend> mFriend;
    private FriendAdappter adapter;
    private TextView newFriend, friends;
    private TextView button1, button2;
    private ImageView img;
    private BadgeView badeview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        listView = (ListView) view.findViewById(R.id.im_friend_listview);
        button1= (TextView) view.findViewById(R.id.friend_newFriend);
        img= (ImageView) view.findViewById(R.id.newfriend_contact);
        button1.setOnClickListener(this);
        getFriends();
        if(mFriend!=null&&mFriend.size()>0){
            adapter = new FriendAdappter(getContext(), mFriend);
            listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void getFriends() {
        BmobQuery<Friend> query = new BmobQuery<>();
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        query.addWhereEqualTo("user", user);
        query.include("friend");
        query.order("-updatedAt");
        query.findObjects(new FindListener<Friend>() {
            @Override
            public void done(List<Friend> list, BmobException e) {
                if (e == null) {
                    mFriend=list;
                    adapter = new FriendAdappter(getContext(), list);
                    listView.setAdapter(adapter);
                } else {
                    L.i(e.getErrorCode()+","+e.getMessage());
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void showRedPoint(MyEvent event){
        badeview = BadgeFactory.create(getContext()).
                setWidthAndHeight(6, 6).setBadgeBackground(Color.RED).
                setBadgeGravity(Gravity.END | Gravity.TOP).setShape(BadgeView.SHAPE_CIRCLE).bind(img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.friend_newFriend:{
                if(badeview!=null){
                    badeview.unbind();
                }
                Intent intent=new Intent(getActivity(), FindFriends.class);
                startActivity(intent);
                break;
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final MyUser friend1 = mFriend.get(position).getFriend();
        BmobIMUserInfo info=new BmobIMUserInfo(friend1.getObjectId(),friend1.getUsername(),friend1.getImgFileUrl());
        BmobIM.getInstance().startPrivateConversation(info, new ConversationListener() {
            @Override
            public void done(BmobIMConversation bmobIMConversation, BmobException e) {
                //L.i("点击item");
                //L.i(e.getErrorCode()+","+e.getMessage());
                Bundle bundle=new Bundle();
                bundle.putSerializable("c",bmobIMConversation);
                Intent intent=new Intent(getActivity(),ChatAcvitity.class);
                intent.putExtra("c",bundle);
                //intent.putExtra("friendimg",friend1.getImgFileUrl());
                startActivity(intent);
            }
        });
    }
}
