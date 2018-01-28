package com.bj.moeweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 天气返回的JSON总体
 * Created by BJ on 2018/1/28.
 */
public class Weather {

    public String status; //状态码

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
