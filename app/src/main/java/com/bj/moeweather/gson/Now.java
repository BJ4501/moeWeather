package com.bj.moeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * JSON中的天气信息
 * Created by BJ on 2018/1/28.
 */
public class Now {

    /* JSON格式
        "now": {
                "cond_code": "101",
                "cond_txt": "多云",
                "fl": "16",
                "hum": "73",
                "pcpn": "0",
                "pres": "1017",
                "tmp": "14",
                "vis": "1",
                "wind_deg": "11",
                "wind_dir": "北风",
                "wind_sc": "微风",
                "wind_spd": "6"
            }
    */

    @SerializedName("fl")
    public String feelTemp; //体感温度

    @SerializedName("tmp")
    public String temperature; //温度

    @SerializedName("cond_code")
    public String condCode; //实况天气状况代码

    @SerializedName("cond_txt")
    public String condTxt; //天气

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

    @SerializedName("cloud")
    public String cloudiness; //云量

}
