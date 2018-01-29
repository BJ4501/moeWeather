package com.bj.moeweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Neko-T4 on 2018/1/29.
 */

public class WeatherForecast {

    public Basic basic;

    public UpdateTime update;

    public String status;

    //天气预报
    @SerializedName("daily_forecast")
    public List<DailyForecast> dailyForecastList;

    public class DailyForecast{

        @SerializedName("date")
        public String date; //预报日期

        @SerializedName("sr")
        public String sunRise; //日出时间

        @SerializedName("ss")
        public String sunSet; //日落时间

        @SerializedName("mr")
        public String moonRise; //月升时间

        @SerializedName("ms")
        public String moonSet; //月落时间

        @SerializedName("tmp_max")
        public String tmpMax; //最高温度

        @SerializedName("tmp_min")
        public String tmpMin; //最低温度

        @SerializedName("cond_code_d")
        public String condCodeDay; //白天天气状况代码

        @SerializedName("cond_code_n")
        public String condCodeNight; //晚间天气状况代码

        @SerializedName("cond_txt_d")
        public String condTextDay; //白天天气状况描述

        @SerializedName("cond_txt_n")
        public String condTextNight; //晚间天气状况描述

        @SerializedName("wind_deg")
        public String windDeg; //风向360角度

        @SerializedName("wind_dir")
        public String windDir; //风向

        @SerializedName("wind_sc")
        public String windSc; //风力

        @SerializedName("wind_spd")
        public String windSpd; //风速，公里/小时

        @SerializedName("hum")
        public String humidity; //相对湿度

        @SerializedName("pcpn")
        public String precipitation; //降水量

        @SerializedName("pres")
        public String pressure; //大气压强

        @SerializedName("vis")
        public String visibility; //能见度，默认单位：公里

        @SerializedName("uv_index")
        public String uvIndex; //紫外线强度指数

    }


}
