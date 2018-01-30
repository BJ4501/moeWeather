package com.bj.moeweather.utils;

import android.text.TextUtils;

import com.bj.moeweather.db.City;
import com.bj.moeweather.db.County;
import com.bj.moeweather.db.Province;
import com.bj.moeweather.dep.Weather;
import com.bj.moeweather.gson.AirNow;
import com.bj.moeweather.gson.WeatherForecast;
import com.bj.moeweather.gson.WeatherLifestyle;
import com.bj.moeweather.gson.WeatherNow;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析请求返回来的JSON信息
 * Created by BJ on 2018/1/28.
 */
public class Utility {

    /**
     * 解析处理服务器返回的省数据
     */
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析处理服务器返回的市数据
     */
    public static boolean handleCityResponse(String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析处理服务器返回的县、区数据
     */
    public static boolean handleCountyResponse(String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountryName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的JSON数据解析成WeatherNow实体类
     */
    public static WeatherNow handleWeatherNowResponse(String response){
        try {
            if (response != null) {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
                String weatherContent = jsonArray.getJSONObject(0).toString();
                //将获得的Json转换为Weather
                return new Gson().fromJson(weatherContent, WeatherNow.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成WeatherLifestyle实体类
     */
    public static WeatherLifestyle handleWeatherLifestyleResponse(String response){
        try {
            if (response != null) {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
                String weatherContent = jsonArray.getJSONObject(0).toString();
                //将获得的Json转换为Weather
                return new Gson().fromJson(weatherContent, WeatherLifestyle.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的JSON数据解析成WeatherForecast实体类
     */
    public static WeatherForecast handleWeatherForecastResponse(String response){
        try {
            if (response != null) {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
                String weatherContent = jsonArray.getJSONObject(0).toString();
                //将获得的Json转换为Weather
                return new Gson().fromJson(weatherContent,WeatherForecast.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static AirNow handleAirNowResponse(String response){
        try {
            if (response != null) {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
                String weatherContent = jsonArray.getJSONObject(0).toString();
                //将获得的Json转换为Weather
                return new Gson().fromJson(weatherContent, AirNow.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
