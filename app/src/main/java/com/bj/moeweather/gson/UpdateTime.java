package com.bj.moeweather.gson;

/**
 * Created by Neko-T4 on 2018/1/29.
 */

public class UpdateTime {
    /* 更新时间JSON
     "update": {
                "loc": "2017-10-26 17:29",
                "utc": "2017-10-26 09:29"
            }
    * */
    //当地时间，24小时制，格式yyyy-MM-dd HH:mm
    public String loc;
    //UTC时间，24小时制，格式yyyy-MM-dd HH:mm
    public String utc;

}
