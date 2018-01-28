package com.bj.moeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * JSON中未来几天的天气信息
 * Created by BJ on 2018/1/28.
 */
public class Forecast {

    public String date;

    @SerializedName("tmp")
    public Temperature temperature; //未来几天，最高温度~最低温度

    @SerializedName("cond")
    public More more; //天气

    public class Temperature{
        public String max;

        public String min;
    }

    public class More{
        @SerializedName("txt_d")
        public String info;
    }
}
