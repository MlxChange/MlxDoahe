package com.example.mlx.daohe.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.MyUser;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Adapter
 * 创建者：MLX
 * 创建时间：2017/2/23 21:11
 * 用途：
 */

public class ChatAdapter extends BaseAdapter {


    private LayoutInflater inflater;
    private Context mcontent;
    private List<BmobIMMessage> messages=new ArrayList<>();
    private String uid;
    private BmobIMConversation mConversation;


    public ChatAdapter(Context mcontent, BmobIMConversation c) {
        this.mcontent = mcontent;
        this.mConversation=c;
        inflater = LayoutInflater.from(mcontent);
        uid= BmobUser.getCurrentUser(MyUser.class).getObjectId();
        //messages=mConversation.getMessages();
    }

    @Override
    public int getCount() {
        return messages==null?0:messages.size();
    }


    @Override
    public Object getItem(int position) {
        return messages==null?null:messages.get(position)!=null?messages.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_chat, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(messages!=null){
            String time = UtilS.getTime(messages.get(position).getCreateTime());
            //L.i("position:"+position+","+time);
            if (position>0){
                boolean overtenMin = UtilS.isOvertenMin(messages.get(position).getCreateTime(), messages.get(position - 1).getCreateTime());
                // L.i("positon:"+position+",与上一次比"+overtenMin);
                if(overtenMin){
                    viewHolder.time.setVisibility(View.VISIBLE);
                    viewHolder.time.setText(time);
                }else{
                    viewHolder.time.setVisibility(View.GONE);
                }
            }else{
                viewHolder.time.setText(time);
                viewHolder.time.setVisibility(View.VISIBLE);
            }
            //L.i(uid+",friendid:"+messages.get(position).getFromId());
            if(TextUtils.equals(uid,messages.get(position).getFromId())){
                viewHolder.mChatSend.setVisibility(View.VISIBLE);
                viewHolder.mChatRecive.setVisibility(View.GONE);
                Glide.with(mcontent).load(BmobUser.getCurrentUser(MyUser.class).getImgFileUrl()).error(R.drawable.error).into(viewHolder.mChatRightImg);
                viewHolder.mChatRightTxt.setText(messages.get(position).getContent());
            }else{
                viewHolder.mChatSend.setVisibility(View.GONE);
                viewHolder.mChatRecive.setVisibility(View.VISIBLE);
                BmobQuery<MyUser> query = new BmobQuery<>();
                query.addWhereEqualTo("objectId", messages.get(position).getFromId());
                query.findObjects(new FindListener<MyUser>() {
                    @Override
                    public void done(List<MyUser> list, BmobException e) {
                        if(e==null){
                            Glide.with(mcontent).load(list.get(0).getImgFileUrl()).error(R.drawable.error).into(viewHolder.mChatLeftImg);
                        }else{
                            Glide.with(mcontent).load("").error(R.drawable.error).into(viewHolder.mChatLeftImg);
                        }
                    }
                });
                viewHolder.mChatLeftTxt.setText(messages.get(position).getContent());
            }
        }
        return convertView;
    }


    public void addMessage(BmobIMMessage message){
        messages.add(message);
        //L.i(messages.size()+"");
        notifyDataSetChanged();
    }

    public void addMessages(List<BmobIMMessage> msgs) {
        messages.addAll(0, msgs);
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public View rootView;
        public CircleImageView mChatLeftImg;
        public TextView mChatLeftTxt;
        public LinearLayout mChatRecive;
        public TextView mChatRightTxt;
        public CircleImageView mChatRightImg;
        public LinearLayout mChatSend;
        public TextView time;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mChatLeftImg = (CircleImageView) rootView.findViewById(R.id.chat_left_img);
            this.mChatLeftTxt = (TextView) rootView.findViewById(R.id.chat_left_txt);
            this.mChatRecive = (LinearLayout) rootView.findViewById(R.id.chat_recive);
            this.mChatRightTxt = (TextView) rootView.findViewById(R.id.chat_right_txt);
            this.mChatRightImg = (CircleImageView) rootView.findViewById(R.id.chat_right_img);
            this.mChatSend = (LinearLayout) rootView.findViewById(R.id.chat_send);
            this.time= (TextView) rootView.findViewById(R.id.chat_time);
        }
    }
}
