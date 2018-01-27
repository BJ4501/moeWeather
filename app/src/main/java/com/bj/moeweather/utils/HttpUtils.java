package com.bj.moeweather.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by BJ on 2018/1/28.
 */
public class HttpUtils {

    /**
     * 封装Http请求，只需要使用这个方法，传入请求地址，就可以从回调来处理服务器响应
     * @param address
     * @param callback
     */
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

}
