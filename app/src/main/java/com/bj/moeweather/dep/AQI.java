package com.bj.moeweather.dep;

/**
 * JSON中的AQI信息
 * Created by BJ on 2018/1/28.
 */
@Deprecated
public class AQI {

    public AQICity city;

    public class AQICity{

        public String aqi;

        public String pm25;
    }

}
