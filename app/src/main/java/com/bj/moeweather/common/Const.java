package com.bj.moeweather.common;

/**
 * Created by Neko-T4 on 2018/1/30.
 */

public class Const {

    //和风天气Key
    private static final String KEY = CommonKey.HE_WEATHER_KEY;

    private static String weatherNowUrl = "https://free-api.heweather.com/s6/weather/now?location=";

    private static String airNowUrl = "https://free-api.heweather.com/s6/air/now?location=";

    private static String weatherForecastUrl = "https://free-api.heweather.com/s6/weather/forecast?location=";

    private static String weatherLifestyleUrl = "https://free-api.heweather.com/s6/weather/lifestyle?location=";


    public static String getWeatherNowUrl(String weatherId){
        return weatherNowUrl+weatherId+"&key="+KEY;
    }

    public static String getAirNowUrl(String weatherId){
        return airNowUrl+weatherId+"&key="+KEY;
    }

    public static String getWeatherForecastUrl(String weatherId){
        return weatherForecastUrl+weatherId+"&key="+KEY;
    }

    public static String getWeatherLifestyleUrl(String weatherId){
        return weatherLifestyleUrl+weatherId+"&key="+KEY;
    }


}
