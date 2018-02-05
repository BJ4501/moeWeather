package com.bj.moeweather.RAtest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bj.moeweather.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    BaseAdapter mAdapter;

    int loadCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
/*
         mRecyclerView = findViewById(R.id.recycler);

        //创建被装饰者类的实例
        final MyAdapter adapter = new MyAdapter(this);

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));*/



    }
}
