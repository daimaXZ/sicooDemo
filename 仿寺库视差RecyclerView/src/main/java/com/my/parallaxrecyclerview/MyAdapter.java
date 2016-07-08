package com.my.parallaxrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by shaoze on 2016/5/18.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final ArrayList<MainActivity.ItemObj> list;

    public MyAdapter(Context context, ArrayList<MainActivity.ItemObj> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainActivity.ItemObj itemObj = list.get(position%(list.size()));
        MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.img.setImageDrawable(context.getResources().getDrawable(itemObj.iv));
        myHolder.tv_name.setText(itemObj.name);
        myHolder.tv_name_en.setText(itemObj.enName);
        myHolder.img.reuse();
        myHolder.img.setParallaxValue(10f);
    }

    @Override
    public int getItemCount() {
        return list.size()*3;
    }
}
