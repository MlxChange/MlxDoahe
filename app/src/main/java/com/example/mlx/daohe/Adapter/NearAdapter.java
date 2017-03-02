package com.example.mlx.daohe.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.Acvitity.Wantinfo;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.UtilS;
import com.example.mlx.daohe.entiy.Want;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Adapter
 * 创建者：MLX
 * 创建时间：2017/2/25 6:26
 * 用途：
 */

public class NearAdapter extends RecyclerView.Adapter<NearAdapter.MyViewholder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Want> mlist;

    public NearAdapter(Context context, List<Want> mlist) {
        this.context = context;
        this.mlist = mlist;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewholder viewholder = new MyViewholder(inflater.inflate(R.layout.near_item, parent, false));
        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, final int position) {
        Glide.with(context).load(mlist.get(position).getImgurl()).error(R.drawable.error).into(holder.near_circle);
        holder.near_username.setText(mlist.get(position).getUsername());
        holder.near_content.setText(mlist.get(position).getContent());
        holder.near_type.setText(mlist.get(position).getType());
        holder.time.setText(UtilS.getLiketime(mlist.get(position).getRequestTime()));
        if(mlist.get(position).isState()){
            holder.state.setText("已完成");
            holder.near_go.setEnabled(false);
        }else{
            holder.state.setText("未完成");
            holder.near_go.setEnabled(true);
        }
        holder.neat_message.setText(mlist.get(position).getMessage());
        holder.near_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(context).setTitle("邀请确认").setMessage("你确定参加此邀请吗?").setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mlist.get(position).setState(true);
                        mlist.get(position).setResponseTime(System.currentTimeMillis());
                        mlist.get(position).setFriendname(BmobUser.getCurrentUser().getUsername());
                        mlist.get(position).update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Intent intent=new Intent(context, Wantinfo.class);
                                    intent.putExtra("username",mlist.get(position).getUsername());
                                    intent.putExtra("time",mlist.get(position).getRequestTime());
                                    context.startActivity(intent);
                                }else{
                                    Toast.makeText(context, "更新失败，请重试："+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).setPositiveButton("取消", null).create();
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
       if(mlist!=null){
            return mlist.size();
        }else {
           return 0;
       }
    }

    public static class MyViewholder extends RecyclerView.ViewHolder{
        public View rootView;
        public CircleImageView near_circle;
        public TextView near_username;
        public TextView near_type;
        public TextView near_content;
        public TextView neat_message,time,state;
        public ImageView near_go;

        public MyViewholder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.near_circle = (CircleImageView) rootView.findViewById(R.id.near_circle);
            this.near_username = (TextView) rootView.findViewById(R.id.near_username);
            this.near_type = (TextView) rootView.findViewById(R.id.near_type);
            this.near_content = (TextView) rootView.findViewById(R.id.near_content);
            this.neat_message = (TextView) rootView.findViewById(R.id.neat_message);
            this.near_go = (ImageView) rootView.findViewById(R.id.near_go);
            this.time= (TextView) rootView.findViewById(R.id.near_item_time);
            this.state= (TextView) rootView.findViewById(R.id.near_item_state);
        }

    }
}
