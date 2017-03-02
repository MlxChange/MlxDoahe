package com.example.mlx.daohe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mlx.daohe.Moudel.UserModel;
import com.example.mlx.daohe.R;
import com.example.mlx.daohe.Utils.L;
import com.example.mlx.daohe.Utils.StaticClass;
import com.example.mlx.daohe.db.NewFriendManager;
import com.example.mlx.daohe.entiy.AgreeAddFriendMessage;
import com.example.mlx.daohe.entiy.DisArgeeAddFriendMessage;
import com.example.mlx.daohe.entiy.MyUser;
import com.example.mlx.daohe.entiy.NewFriend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.Adapter
 * 创建者：MLX
 * 创建时间：2017/2/22 22:17
 * 用途：
 */

public class NewFriendAdapter extends BaseAdapter implements View.OnClickListener {

    private Context mcontext;
    private LayoutInflater inflater;
    private List<NewFriend> mlist;
    private int mposition;
    private ViewHolder viewHolder;

    public NewFriendAdapter(Context mcontext, List<NewFriend> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        inflater=LayoutInflater.from(mcontext);
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
        mposition=position;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_newfriend,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.circle= (ImageView) convertView.findViewById(R.id.newfriend_circle);
            viewHolder.name= (TextView) convertView.findViewById(R.id.newfriend_name);
            viewHolder.message= (TextView) convertView.findViewById(R.id.newfriend_message);
            viewHolder.agree= (Button) convertView.findViewById(R.id.newfriend_agree);
            viewHolder.jujue= (Button) convertView.findViewById(R.id.newfriend_jujue);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(mlist!=null){
            Glide.with(mcontext).load(mlist.get(position).getAvatar()).error(R.drawable.error).into(viewHolder.circle);
            viewHolder.name.setText(mlist.get(position).getName());
            viewHolder.message.setText(mlist.get(position).getMsg());
            Integer status = mlist.get(position).getStatus();
            if(status==StaticClass.STATUS_VERIFY_REFUSE){
                viewHolder.agree.setVisibility(View.INVISIBLE);
                viewHolder.jujue.setText("已拒绝");
                viewHolder.jujue.setEnabled(false);
            }else if(status==StaticClass.STATUS_VERIFIED){
                viewHolder.agree.setVisibility(View.INVISIBLE);
                viewHolder.jujue.setText("已添加");
                viewHolder.jujue.setEnabled(false);
            }else if(status==StaticClass.STATUS_VERIFY_ME_SEND){
                viewHolder.agree.setVisibility(View.INVISIBLE);
                viewHolder.jujue.setText("已发送");
                viewHolder.jujue.setEnabled(false);
            }
            viewHolder.agree.setOnClickListener(this);
            viewHolder.jujue.setOnClickListener(this);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newfriend_agree:{
                sendAgreeAddFriendMessage(mlist.get(mposition));
                viewHolder.agree.setVisibility(View.INVISIBLE);
                viewHolder.jujue.setText("已添加");
                viewHolder.jujue.setEnabled(false);
                break;
            }
            case R.id.newfriend_jujue:{
                sendjujueAddFriendMessage(mlist.get(mposition));
                viewHolder.agree.setVisibility(View.INVISIBLE);
                viewHolder.jujue.setText("已拒绝");
                viewHolder.jujue.setEnabled(false);
                break;
            }
        }
    }

    private void sendjujueAddFriendMessage(final NewFriend add) {
        BmobIMUserInfo info = new BmobIMUserInfo(add.getUid(), add.getName(), add.getAvatar());
        //如果为true,则表明为暂态会话，也就是说该会话仅执行发送消息的操作，不会保存会话和消息到本地数据库中
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info,true,null);
        //这个obtain方法才是真正创建一个管理消息发送的会话
        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(),c);
        //而AgreeAddFriendMessage的isTransient设置为false，表明我希望在对方的会话数据库中保存该类型的消息
        DisArgeeAddFriendMessage msg =new DisArgeeAddFriendMessage();
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        msg.setContent("我拒绝了你的好友请求!");//---这句话是直接存储到对方的消息表中的
        Map<String,Object> map =new HashMap<>();
        map.put("msg",user.getUsername()+"拒绝添加你为好友");//显示在通知栏上面的内容
        map.put("uid",add.getUid());//发送者的uid-方便请求添加的发送方找到该条添加好友的请求
        map.put("time", add.getTime());//添加好友的请求时间
        msg.setExtraMap(map);
        conversation.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage msg, BmobException e){
                if (e == null) {//发送成功
                    //修改本地的好友请求记录
                    NewFriendManager.getInstance(mcontext).updateNewFriend(add, StaticClass.STATUS_VERIFY_REFUSE);
                } else {//发送失败

                }
            }
        });
    }

    private void sendAgreeAddFriendMessage(final NewFriend add) {
        BmobIMUserInfo info = new BmobIMUserInfo(add.getUid(), add.getName(), add.getAvatar());
        //如果为true,则表明为暂态会话，也就是说该会话仅执行发送消息的操作，不会保存会话和消息到本地数据库中
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info,true,null);
        //这个obtain方法才是真正创建一个管理消息发送的会话
        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(),c);
        //而AgreeAddFriendMessage的isTransient设置为false，表明我希望在对方的会话数据库中保存该类型的消息
        AgreeAddFriendMessage msg =new AgreeAddFriendMessage();
        final MyUser user = BmobUser.getCurrentUser(MyUser.class);
        msg.setContent("我通过了你的好友验证请求，我们可以开始聊天了!");//---这句话是直接存储到对方的消息表中的
        Map<String,Object> map =new HashMap<>();
        map.put("msg",user.getUsername()+"同意添加你为好友");//显示在通知栏上面的内容
        map.put("uid",add.getUid());//发送者的uid-方便请求添加的发送方找到该条添加好友的请求
        map.put("time", add.getTime());//添加好友的请求时间
        msg.setExtraMap(map);
        conversation.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage msg, BmobException e){
                if (e == null) {//发送成功

                    //修改本地的好友请求记录
                    NewFriendManager.getInstance(mcontext).updateNewFriend(add, StaticClass.STATUS_VERIFIED);
                    MyUser user1=new MyUser();
                    user1.setObjectId(add.getUid());
                    //L.i("addid:"+add.getUid());
                    UserModel.getInstance().agreeAddFriend(user1, new SaveListener() {
                        @Override
                        public void done(Object o, BmobException e) {
                            notifyDataSetChanged();
                        }

                        @Override
                        public void done(Object o, Object o2) {
                            notifyDataSetChanged();
                        }
                    });
                } else {//发送失败

                }
            }
        });

    }


    class ViewHolder{
        private ImageView circle;
        private Button agree,jujue;
        private TextView name,message;
    }
}
