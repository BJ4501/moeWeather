package com.bj.moeweather;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bj.moeweather.gson.Weather;
import com.bj.moeweather.utils.Utility;

import butterknife.BindView;

public class WeatherActivity extends Activity {
    @BindView(R.id.weather_layout)
    private ScrollView weatherLayout;
    @BindView(R.id.title_city)
    private TextView titleCity;
    @BindView(R.id.title_update_time)
    private TextView titleUpdateTime;
    @BindView(R.id.degree_text)
    private TextView degreeText;
    @BindView(R.id.weather_info_text)
    private TextView weatherInfoText;
    @BindView(R.id.forecast_layout)
    private LinearLayout forecastLayout;
    @BindView(R.id.aqi_text)
    private TextView aqiText;
    @BindView(R.id.pm25_text)
    private TextView pm25Text;
    @BindView(R.id.comfort_text)
    private TextView comfortText;
    @BindView(R.id.car_wash_text)
    private TextView carWashText;
    @BindView(R.id.sport_text)
    private TextView sportText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //fixme ??? 作用是？
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather",null);
        if (weatherString != null){
            //有缓存时，直接解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
            showWeatherInfo(weather);
        }else {
            //无缓存时去服务器查询天气
            String weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
    }

    //根据天气id请求城市天气信息
    private void requestWeather(String weatherId) {

    }

    //处理并展示Weather实体类中的数据
    private void showWeatherInfo(Weather weather) {

    }
}


