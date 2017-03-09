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
import com.example.mlx.daohe.entiy.Hot_Movies;

import java.util.List;

/**
 * 项目名：LxChange
 * 包名：com.example.administrator.lxchange.Adapter
 * 创建者：MLX
 * 创建时间：2017/2/11 18:05
 * 用途：热门电影适配器
 */

public class Hot_movies_Adapter  extends BaseAdapter{

    //热门电影实体类集合
    private List<Hot_Movies.DataBean.MoviesBean> mlist;
    private LayoutInflater inflater;
    private Context mcontext;

    public Hot_movies_Adapter(Context context,Hot_Movies movies) {
        inflater=LayoutInflater.from(context);
        mcontext=context;
        mlist=movies.getData().getMovies();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.hot_movies_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.img= (ImageView) convertView.findViewById(R.id.hot_movies_item_img);
            viewHolder.like= (ImageView) convertView.findViewById(R.id.hot_movies_item_like);
            viewHolder.name= (TextView) convertView.findViewById(R.id.hot_movies_item_MoviesName);
            viewHolder.dec= (TextView) convertView.findViewById(R.id.hot_movies_item_dec);
            viewHolder.start= (TextView) convertView.findViewById(R.id.hot_movies_item_star);
            viewHolder.number= (TextView) convertView.findViewById(R.id.hot_movies_item_number);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
       if(mlist!=null){
           viewHolder.name.setText(mlist.get(position).getNm());
           viewHolder.dec.setText(mlist.get(position).getScm());
           viewHolder.start.setText(mlist.get(position).getSc()+"");
           viewHolder.number.setText(mlist.get(position).getShowInfo());
           Glide.with(mcontext).load(mlist.get(position).getImg()).into(viewHolder.img);
       }
        return convertView;
    }

    private class ViewHolder{
        private ImageView img,like;
        private TextView name,start,dec,number;
    }
}
