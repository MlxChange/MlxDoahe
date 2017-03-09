package com.example.mlx.daohe.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.entiy.Movies;
import com.example.mlx.daohe.entiy.MoviesThink;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Adapter
 * 创建者：MLX
 * 创建时间：2017/2/27 17:40
 * 用途：电影详情适配器
 */

public class Movies_Adapter extends RecyclerView.Adapter<Movies_Adapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<MoviesThink.HcmtsBean> mlist;


    public Movies_Adapter(Context context, List<MoviesThink.HcmtsBean> mlist) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.mlist = mlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_think, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MoviesThink.HcmtsBean hcmtsBean = mlist.get(position);
        Glide.with(context).load(hcmtsBean.getAvatarurl()).into(holder.item_think_img);
        holder.item_think_name.setText(hcmtsBean.getNickName());
        holder.item_think_time.setText(hcmtsBean.getTime());
        holder.item_think_content.setText("\t\t"+hcmtsBean.getContent());
        holder.item_think_zan.setText(hcmtsBean.getApprove()+"");
    }

    @Override
    public int getItemCount() {
        return mlist==null?0:mlist.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public CircleImageView item_think_img;
        public TextView item_think_name;
        public TextView item_think_content;
        public TextView item_think_time;
        public TextView item_think_zan;

        public MyViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.item_think_img = (CircleImageView) rootView.findViewById(R.id.item_think_img);
            this.item_think_name = (TextView) rootView.findViewById(R.id.item_think_name);
            this.item_think_content = (TextView) rootView.findViewById(R.id.item_think_content);
            this.item_think_time = (TextView) rootView.findViewById(R.id.item_think_time);
            this.item_think_zan = (TextView) rootView.findViewById(R.id.item_think_zan);
        }

    }
}
