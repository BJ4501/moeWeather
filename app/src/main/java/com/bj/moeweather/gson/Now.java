package com.bj.moeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * JSON中的天气信息
 * Created by BJ on 2018/1/28.
 */
public class Now {

    @SerializedName("tmp")
    public String temperature; //温度

    @SerializedName("cond")
    public More more; //天气

    public class More{
        @SerializedName("txt")
        public String info;
    }

}
