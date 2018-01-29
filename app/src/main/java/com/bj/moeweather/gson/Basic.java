package com.bj.moeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * JSON基础信息
 * Created by BJ on 2018/1/28.
 */
public class Basic {

    /* JSON格式
        "basic": {
                "cid": "CN101010100",
                "location": "北京",
                "parent_city": "北京",
                "admin_area": "北京",
                "cnty": "中国",
                "lat": "39.90498734",
                "lon": "116.40528870",
                "tz": "8.0"
            }
    */

    //NOTE:  @SerializedName 因为有些字段不适合直接作为java字段的名字，
    // 通过这个注解，让JSON字段和java字段建立映射联系

    public String cid; //城市ID
    @SerializedName("location")
    public String cityName; //城市名
    @SerializedName("parent_city")
    public String parentCity; //城市的上级城市
    @SerializedName("admin_area")
    public String adminArea; //城市所属行政区域

    public String cnty; //城市所属国家名称

    public String lon; //纬度

    public String lat; //经度

    public String tz; //城市所在时区

}
