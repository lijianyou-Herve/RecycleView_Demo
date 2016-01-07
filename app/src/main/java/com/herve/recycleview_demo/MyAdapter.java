package com.herve.recycleview_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Herve on 2016/01/07.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private int count = 0;
    private OnItemClicklistener onItemClicklistener;

    private List<Integer> Data = new ArrayList();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }


    public void setData(ArrayList<Integer> Data) {
        this.Data = Data;
    }

    public void addAllData(ArrayList<Integer> Data) {
        Data.addAll(Data);
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public void insertData(int data) {
        this.Data.add(data);
    }

    public void deleteData(int location) {
        Data.remove(location);
    }

    public void deleteAllData() {
        Data = new ArrayList();
    }

    public void removeAll() {
        Data.removeAll(Data);
    }


    public interface OnItemClicklistener {
        void onItemClickListener(View view, int position);

    }

    public void setOnItemClicklistener(OnItemClicklistener onItemClicklistener) {
        this.onItemClicklistener = onItemClicklistener;
        Log.i("Herve不应该啊", "setOnItemClicklistener: ");
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
        //加载地三个位置的Imageview为查看更多
        if (position == 3) {
            Glide.with(context).load(R.mipmap.community_details_more_selected_touch).into(holder.imageView);
        } else {
            Glide.with(context).load(R.mipmap.image2).into(holder.imageView);
        }
        //如果点击了某个，则触发回调函数
        if (onItemClicklistener != null) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicklistener.onItemClickListener(holder.imageView, position);
                }
            });
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private FrameLayout frameLayout01;

        public ViewHolder(View v) {
            super(v);

            imageView = (ImageView) v.findViewById(R.id.iv_film_logo);
            frameLayout01 = (FrameLayout) v.findViewById(R.id.frameLayout01);

        }

    }

}
