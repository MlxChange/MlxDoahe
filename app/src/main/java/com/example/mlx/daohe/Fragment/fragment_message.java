package com.example.mlx.daohe.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;
import com.example.mlx.daohe.Acvitity.ChatAcvitity;
import com.example.mlx.daohe.Adapter.MessageAdappter;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.SharedUtils;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.MessageEntiy;
import com.example.mlx.daohe.entiy.MyUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.util.SharedUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Fragment
 * 创建者：MLX
 * 创建时间：2017/2/17 23:52
 * 用途：消息fragment
 */

public class fragment_message extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView mlistview;//listview 用于展示消息列表
    private MessageAdappter adapter;//messageadpater
    private Map<String,String> lastMessages;//最后一条消息map
    private List<BmobIMConversation> allConversation;//本地会话列表
    private BadgeView badgeView;//小红点
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        mlistview= (ListView) view.findViewById(R.id.message_listview);
        //lastMessages=new HashMap<>();
        getMessageEntiy();

        adapter=new MessageAdappter(getContext(),allConversation);

        mlistview.setAdapter(adapter);
        mlistview.setOnItemClickListener(this);
        mlistview.setOnItemLongClickListener(this);
        //注册EventBus
        EventBus.getDefault().register(this);
        return view;
    }
    //获得最近会话
    public void getMessageEntiy(){
        allConversation = BmobIM.getInstance().loadAllConversation();
    }

    //设置为异步，即后注册也能接受到事件发送
    @Subscribe(sticky = true)
    public void getMessage(MessageEvent event){

        BmobIMConversation imConversation = event.getConversation();
        //如果发送方Id不是本地用户的话就添加进去
        if(!event.getFromUserInfo().getUserId().equals(BmobUser.getCurrentUser().getObjectId())){
            adapter.addLastMessage(event);
        }
    }


    //点击后跳转到聊天界面
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(), ChatAcvitity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("c",allConversation.get(position));
        intent.putExtra("c",bundle);
        SharedUtils.putString(getContext(),allConversation.get(position).getConversationId()+"num","");
        startActivity(intent);
    }





    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final int i=position;
        builder.setTitle("删除确认").setMessage("你确认要删除这个会话吗，将会删除所有聊天记录").setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BmobIM.getInstance().deleteConversation(allConversation.get(i));
                allConversation.remove(i);
                Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        }).setPositiveButton("取消",null).setCancelable(false).create().show();
        return false;
    }
}
