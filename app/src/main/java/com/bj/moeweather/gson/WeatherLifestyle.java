package com.bj.moeweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 生活指数JSON
 * Created by Neko-T4 on 2018/1/29.
 */

public class WeatherLifestyle {

    public Basic basic;

    public UpdateTime update;

    public String status;

    @SerializedName("lifestyle")
    public List<Lifestyle> lifestyleList;

    //生活指数
    public class Lifestyle{

        public String brf; //生活指数简介

        public String txt; //生活指数详细描述

        public String type;
        //生活指数类型
        // comf：舒适度指数、
        // cw：洗车指数、
        // drsg：穿衣指数、
        // flu：感冒指数、
        // sport：运动指数、
        // trav：旅游指数、
        // uv：紫外线指数、
        // air：空气污染扩散条件指数
    }


}
