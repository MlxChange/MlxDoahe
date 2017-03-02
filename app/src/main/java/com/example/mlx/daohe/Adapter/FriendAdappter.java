package com.example.mlx.daohe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.entiy.Friend;

import java.util.List;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Adapter
 * 创建者：MLX
 * 创建时间：2017/2/19 17:43
 * 用途：
 */

public class FriendAdappter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Friend> mlist;
    private Context mcontext;

    public FriendAdappter(Context context, List<Friend> mlist) {
        mcontext=context;
        inflater=LayoutInflater.from(context);
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist==null?0:mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist==null?null:mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_friend,parent,false);
            viewholder=new Viewholder();
            viewholder.circle= (ImageView) convertView.findViewById(R.id.friend_circle);
            viewholder.name= (TextView) convertView.findViewById(R.id.friend_name);
            viewholder.sendmessage= (ImageView) convertView.findViewById(R.id.friend_sendmessage);
            convertView.setTag(viewholder);
        }else{
            viewholder= (Viewholder) convertView.getTag();
        }
        if(mlist!=null){
            Glide.with(mcontext).load(mlist.get(position).getFriend().getImgFileUrl()).error(R.drawable.error).into(viewholder.circle);
            viewholder.name.setText(mlist.get(position).getFriend().getUsername());
            viewholder.sendmessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = mlist.get(position).getFriend().getUsername();
                }
            });
        }
        return convertView;
    }

    class Viewholder{
        private ImageView circle,sendmessage;
        private TextView name;
    }
}
