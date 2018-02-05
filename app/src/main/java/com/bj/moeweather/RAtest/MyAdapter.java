package com.bj.moeweather.RAtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bj.moeweather.R;

/**
 * 被装饰类
 * Created by Neko-T4 on 2018/2/2.
 */

public class MyAdapter extends BaseAdapter<String> {

    private Context mContext;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_mine,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).bind(getDataSet().get(position));
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_content);
        }

        public void bind(CharSequence content){
            mTextView.setText(content);
        }
    }


}
