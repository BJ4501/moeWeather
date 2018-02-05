package com.bj.moeweather.RAtest;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bj.moeweather.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends Fragment {

    RecyclerView mRecyclerView;

    BaseAdapter mAdapter;

    int loadCount;

    Activity mActivity;


    public BookListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivity =  getActivity();


        mRecyclerView = mActivity.findViewById(R.id.recycler);

        //创建被装饰者类的实例
        final MyAdapter adapter = new MyAdapter(mActivity.getApplicationContext());

        //创建装饰者实例，并传入被装饰者和回调接口
        mAdapter = new LoadMoreAdapterWrapper(adapter, new LoadMoreAdapterWrapper.OnLoad() {
            @Override
            public void load(int pagePosition, int pageSize, final LoadMoreAdapterWrapper.ILoadCallback callback) {
                //模拟网络请求操作。2S延迟，将拉取的数据更新到adapter中
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> dataSet = new ArrayList<>();
                        for (int i = 0; i < 10; i++){
                            dataSet.add("Data"+i);
                        }
                        //数据的处理最终还是交给被装饰的adapter来处理
                        adapter.appendData(dataSet);
                        callback.onSuccess();
                        //模拟加载到没有更多数据的情况，触发OnFailure
                        if (loadCount++ == 3){
                            callback.onFailure();
                        }
                    }
                },2000);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext(), LinearLayoutManager.VERTICAL,false));



        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

}
