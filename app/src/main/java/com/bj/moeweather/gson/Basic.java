package com.bj.moeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BJ on 2018/1/28.
 */
public class Basic {

    //NOTE:  @SerializedName 因为有些字段不适合直接作为java字段的名字，
    // 通过这个注解，让JSON字段和java字段建立映射联系
    @SerializedName("city")
    public String cityName; //城市名

    @SerializedName("id")
    public String weatherId; //城市对应的天气id

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateName; //天气的更新时间
    }

}
