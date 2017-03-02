package com.example.mlx.daohe.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Picture;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;
import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.SharedUtils;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.MessageEntiy;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.event.MessageEvent;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Adapter
 * 创建者：MLX
 * 创建时间：2017/2/19 17:43
 * 用途：
 */

public class MessageAdappter extends BaseAdapter {

    private LayoutInflater inflater;
    List<BmobIMConversation> mlist;
    private MessageEvent event;
    private int number;

    private Context mcontext;

    public MessageAdappter(Context context,List<BmobIMConversation> mMessagelist) {
        mcontext=context;
        inflater=LayoutInflater.from(context);
        this.mlist = mMessagelist;
    }

    @Override
    public int getCount() {
        if(mlist==null){
            return 0;
        }else{
            return mlist.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return mlist==null?null:mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addLastMessage(MessageEvent event){
        this.event=event;
        SharedUtils.putString(mcontext,event.getConversation().getConversationId()+"time",UtilS.getLiketime(event.getMessage().getCreateTime()));
        SharedUtils.putString(mcontext,event.getConversation().getConversationId()+"txt",event.getMessage().getContent());
        SharedUtils.putString(mcontext,event.getConversation().getConversationId()+"num",BmobIM.getInstance().getUnReadCount(event.getConversation().getConversationId())+"");
        notifyDataSetChanged();
    }

    public void setNumber(String n){
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_message,parent,false);
            viewholder=new Viewholder();
            viewholder.circle= (ImageView) convertView.findViewById(R.id.message_circleimg);
            viewholder.name= (TextView) convertView.findViewById(R.id.contact_name);
            viewholder.message= (TextView) convertView.findViewById(R.id.contact_message);
            viewholder.time= (TextView) convertView.findViewById(R.id.contact_time);
            viewholder.badgeView= BadgeFactory.create(mcontext).setBadgeBackground(Color.RED).
                    setShape(BadgeView.SHAPE_CIRCLE).setTextSize(14).
                    setTextColor(Color.WHITE).setWidthAndHeight(0,0).setBadgeGravity(Gravity.RIGHT|Gravity.BOTTOM).setSpace(0,30);
            convertView.setTag(viewholder);
        }else{
            viewholder= (Viewholder) convertView.getTag();
        }
        if(mlist!=null){
            if(event!=null&&mlist.get(position).getConversationId().equals(mlist.get(position).getConversationId())){
                String content = event.getMessage().getContent();
                viewholder.message.setText(content);
                viewholder.time.setText(UtilS.getLiketime(event.getMessage().getCreateTime()));
                number =(int) BmobIM.getInstance().getUnReadCount(event.getConversation().getConversationId());
                if(number!=0){
                    viewholder.badgeView.setWidthAndHeight(20,20).
                            setBadgeCount(number).bind(viewholder.time);;
                }else{
                    viewholder.badgeView.unbind();
                }
            }else{
                String times = SharedUtils.getString(mcontext, mlist.get(position).getConversationId() + "time", "");
                String mess = SharedUtils.getString(mcontext, mlist.get(position).getConversationId() + "txt", "");
                //number=SharedUtils.getString(mcontext, mlist.get(position).getConversationId() + "num", "");
                number = (int)BmobIM.getInstance().getUnReadCount(mlist.get(position).getConversationId());
                if(!TextUtils.isEmpty(times)&&!TextUtils.isEmpty(mess)){
                    viewholder.message.setText(mess);
                    viewholder.time.setText(times);
                    if(number!=0){
                        viewholder.badgeView.setWidthAndHeight(20,20).
                                setBadgeCount(number).bind(viewholder.time);
                    }else{
                        viewholder.badgeView.unbind();
                    }
                }
            }
            Glide.with(mcontext).load(mlist.get(position).getConversationIcon()).error(R.drawable.error).into(viewholder.circle);
            viewholder.name.setText(mlist.get(position).getConversationTitle());
        }
        return convertView;
    }

    class Viewholder{
        private ImageView circle;
        private TextView time,name,message;
        BadgeView badgeView;
    }


}
