package com.bj.moeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 空气质量信息
 * Created by Neko-T4 on 2018/1/29.
 */
//https://free-api.heweather.com/s6/air/now?parameters
public class AirNow {

    public Basic basic;

    public UpdateTime update;

    public String status;

    //AQI城市实况
    @SerializedName("air_now_city")
    public AirNowCity airNowCity;

    //TODO 检测站信息  AQI站点实况
    //public AirNowStation airNowStation;

    public class AirNowCity {
        /*
        * "air_now_city": {
                "aqi": "19",
                "co": "0",
                "main": "",
                "no2": "34",
                "o3": "31",
                "pm10": "18",
                "pm25": "8",
                "pub_time": "2017-11-07 22:00",
                "qlty": "优",
                "so2": "2"
            },
        * */

        @SerializedName("pub_time")
        public String pubTime; //数据发布时间,格式yyyy-MM-dd HH:mm

        @SerializedName("aqi")
        public String aqi; //空气质量指数

        @SerializedName("main")
        public String mainContaminants; //主要污染物

        @SerializedName("qlty")
        public String qlty; //空气质量，取值范围:优，良，轻度污染，中度污染，重度污染，严重污染

        @SerializedName("pm10")
        public String pm10; //pm10

        @SerializedName("pm25")
        public String pm25; //pm25

        @SerializedName("no2")
        public String no2; //二氧化氮

        @SerializedName("so2")
        public String so2; //二氧化硫

        @SerializedName("co")
        public String co; //一氧化碳

        @SerializedName("o3")
        public String o3; //臭氧

    }


}
