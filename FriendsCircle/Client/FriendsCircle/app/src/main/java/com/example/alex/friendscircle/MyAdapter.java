package com.example.alex.friendscircle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by 15587 on 2018/11/28.
 */
public class MyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Users> list;

    public MyAdapter(Context context, List<Users> list){
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    public int getCount(){
        return list.size();
    }

    public Object getItem(int position){
        return list.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.user = (TextView) convertView.findViewById(R.id.name);
            holder.text = (TextView) convertView.findViewById(R.id.tv);
            holder.pic = (ImageView) convertView.findViewById(R.id.iv1);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        GetImg getIcon = new GetImg(list.get(position).getIconUrl());
        GetImg getPic= new GetImg();
        getIcon.start();
        try {
            getIcon.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!list.get(position).getPicUrl().equals("")){
            getPic.setImgUrl(list.get(position).getPicUrl());
            getPic.start();
            try{
                getPic.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            holder.pic.setImageBitmap(getPic.getBm());
        }
        holder.user.setText(list.get(position).getUser());
        holder.text.setText(list.get(position).getText());
        holder.icon.setImageBitmap(getIcon.getBm());
        holder.time.setText(list.get(position).getDate());
        return convertView;
    }

    public final class ViewHolder{
        public ImageView icon;
        public TextView user;
        public TextView text;
        public ImageView pic;
        public TextView time;
    }
}
