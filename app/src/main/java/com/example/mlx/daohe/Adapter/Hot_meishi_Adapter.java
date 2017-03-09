package com.example.mlx.daohe.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.entiy.Hot_Meishi;
import com.example.mlx.daohe.entiy.Hot_Movies;

import java.util.List;
import java.util.Random;

/**
 * 项目名：LxChange
 * 包名：com.example.administrator.lxchange.Adapter
 * 创建者：MLX
 * 创建时间：2017/2/11 18:05
 * 用途：热门美食适配器
 */

public class Hot_meishi_Adapter extends BaseAdapter{

    //热门美食实体类集合
    private List<Hot_Meishi.ResultBean> mlist;
    private LayoutInflater inflater;
    private Context mcontext;
    //随机加载图片
    private int[] mdrawables={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};


    public Hot_meishi_Adapter(Context context, Hot_Meishi meishi) {
        inflater=LayoutInflater.from(context);
        mcontext=context;
        mlist=meishi.getResult();
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
        Random random = new Random();
        if(convertView==null){
            convertView=inflater.inflate(R.layout.hot_meishi_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.img= (ImageView) convertView.findViewById(R.id.hot_meishi_item_img);
            viewHolder.like= (ImageView) convertView.findViewById(R.id.hot_meishi_item_like);
            viewHolder.name= (TextView) convertView.findViewById(R.id.hot_meishi_item_MeishiName);
            viewHolder.dec= (TextView) convertView.findViewById(R.id.hot_meishi_item_tag);
            viewHolder.start= (TextView) convertView.findViewById(R.id.hot_meishi_item_star);
            viewHolder.number= (TextView) convertView.findViewById(R.id.hot_meishi_item_address);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(mlist!=null){
            viewHolder.name.setText(mlist.get(position).getName());
            viewHolder.dec.setText(mlist.get(position).getTags());
            viewHolder.start.setText(random.nextInt(2)+8+"."+random.nextInt(10));
            viewHolder.number.setText(mlist.get(position).getCity()+
                    mlist.get(position).getArea()+mlist.get(position).getAddress());

            Glide.with(mcontext).load(mdrawables[random.nextInt(5)]).into(viewHolder.img);
        }
        return convertView;
    }

    private class ViewHolder{
        private ImageView img,like;
        private TextView name,start,dec,number;
    }
}
