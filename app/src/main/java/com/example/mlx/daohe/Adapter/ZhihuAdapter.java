package com.example.mlx.daohe.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.entiy.ZhihuEntiy;

import java.util.List;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Adapter
 * 创建者：MLX
 * 创建时间：2017/2/28 2:28
 * 用途：
 */

public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<ZhihuEntiy.StoriesBean> mlist;
    public interface onItemClickLinster{
        public void onClick(View v,int position);
    }
    private onItemClickLinster linster;
    public ZhihuAdapter(Context context, List<ZhihuEntiy.StoriesBean> mlist) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.mlist = mlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_zhihu, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
       if(mlist!=null){
           Glide.with(context).load(mlist.get(position).getImages().get(0)).into(holder.item_zhihu_img);
           holder.item_zhihu_text.setText(mlist.get(position).getTitle());
           holder.layout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   linster.onClick(v,position);
               }
           });
       }
    }

    public void addOnitemClickLinster(onItemClickLinster linster){
        this.linster=linster;
    }

    @Override
    public int getItemCount() {
        return mlist==null?0:mlist.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        private LinearLayout layout;
        public TextView item_zhihu_text;
        public ImageView item_zhihu_img;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            layout= (LinearLayout) rootView.findViewById(R.id.item_zhihu_layout);
            this.item_zhihu_text = (TextView) rootView.findViewById(R.id.item_zhihu_text);
            this.item_zhihu_img = (ImageView) rootView.findViewById(R.id.item_zhihu_img);
        }

    }
}
