package com.bj.moeweather.RAtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bj.moeweather.R;

/**
 * 只负责处理“加载更多”的逻辑
 * Created by Neko-T4 on 2018/2/2.
 */

public class LoadMoreAdapterWrapper extends BaseAdapter<String> {

    private BaseAdapter mAdapter;
    //每页数量
    private static final int mPageSize = 10;
    //页数
    private int mPagePosition = 0;

    private boolean hasMoreData = true;

    private OnLoad mOnLoad;

    public LoadMoreAdapterWrapper(BaseAdapter mAdapter, OnLoad mOnLoad) {
        this.mAdapter = mAdapter;
        this.mOnLoad = mOnLoad;
    }

    //通过判断viewType的类型返回不同的ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果显示类型是“没有更多内容”
        if (viewType == R.layout.list_item_no_more){
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
            return new NoMoreItemVH(view);
        }else if (viewType == R.layout.list_item_loading){
            //如果类型是“加载中”
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
            return new LoadingItemVH(view);
        }else {
            return mAdapter.onCreateViewHolder(parent,viewType);
        }
    }

    /**
     * 判断holder的类型的不同做出不同的操作，
     * 如果holder是加载更多的holder，那么表示加载更多的view正在展示
     * 这时候就应该做出加载更多的操作，这个操作放在了requestData方法中。
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingItemVH){
            requestData(mPagePosition, mPageSize);
        }else if (holder instanceof NoMoreItemVH){

        }else {
            mAdapter.onBindViewHolder(holder, position);
        }

    }

    /**
     * 触发了OnLoad接口中的load回调，传入的参数有当前的页码加载数据一页的大小
     * 加载完成之后的回调接口，可以看到加载完成之后会进行不同的作
     * 如果成功，则设置hasMoreData为true，并且通知数据发生改变
     * 更新列表，改变前页码；如果失败的话，则把hasMoreData设置为false。
     * @param pagePosition
     * @param pageSize
     */
    private void requestData(final int pagePosition, final int pageSize) {

        //网络请求
        //如果是异步请求，则在成功之后回调数据，并且调用notifyDataSetChanged方法，hasMoreData为true
        //如果没有数据了，则hasMoreData为false，然后通知变化，更新RecyclerView
        if (mOnLoad != null){
            mOnLoad.load(pagePosition, pageSize, new ILoadCallback() {
                @Override
                public void onSuccess() {
                    notifyDataSetChanged();
                    mPagePosition = (mPagePosition + 1) * mPageSize;
                    hasMoreData = true;
                }

                @Override
                public void onFailure() {
                    hasMoreData = false;
                }
            });
        }
    }

    /**
     * 首先判断position是否是最后一个，如果不是的话，则直接返回被装饰的mAdapter的getItemType；
     * 如果是的话判断hasMoreData变量，是true则返回加载更多的布局文件ID，
     * 反之返回到底的布局文件ID。这里的hasMoreData是我们自己定义的一个boolean字段，
     * 通过改变这个值就可以最后一个view应该是加载更多还是到底。
     * @param position 当前位置
     */
    @Override
    public int getItemViewType(int position) {
        //刨除最后一个状态栏的位置的·
        if (position == getItemCount() - 1){
            if (hasMoreData){
                return R.layout.list_item_loading;
            }else {
                return R.layout.list_item_no_more;
            }
        }else {
            return mAdapter.getItemViewType(position);
        }
    }

    /**
     * 因为在列表中最后始终会有一个加载更多或者是到底提示的view，
     * 所以item的数目始终是数据源的数目多一个。
     */
    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 1;
    }

    static class LoadingItemVH extends RecyclerView.ViewHolder{
        public LoadingItemVH(View itemView) {
            super(itemView);
        }
    }

    static class NoMoreItemVH extends RecyclerView.ViewHolder{
        public NoMoreItemVH(View itemView) {
            super(itemView);
        }
    }

    public interface OnLoad {
        void load(int pagePosition, int pageSize, ILoadCallback callback);
    }

    public interface ILoadCallback{
        void onSuccess();

        void onFailure();
    }

}



