package com.bj.moeweather;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bj.moeweather.dep.Forecast;
import com.bj.moeweather.dep.Weather;
import com.bj.moeweather.gson.AirNow;
import com.bj.moeweather.gson.WeatherForecast;
import com.bj.moeweather.gson.WeatherLifestyle;
import com.bj.moeweather.gson.WeatherNow;
import com.bj.moeweather.utils.HttpUtils;
import com.bj.moeweather.utils.Utility;
import com.bumptech.glide.Glide;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends Activity {
    @Nullable
    @BindView(R.id.weather_layout)
    ScrollView weatherLayout;
    @BindView(R.id.title_city)
    TextView titleCity;
    @BindView(R.id.title_update_time)
    TextView titleUpdateTime;
    @BindView(R.id.degree_text)
    TextView degreeText;
    @BindView(R.id.weather_info_text)
    TextView weatherInfoText;
    @BindView(R.id.forecast_layout)
    LinearLayout forecastLayout;
    @BindView(R.id.aqi_text)
    TextView aqiText;
    @BindView(R.id.pm25_text)
    TextView pm25Text;
    @BindView(R.id.comfort_text)
    TextView comfortText;
    @BindView(R.id.car_wash_text)
    TextView carWashText;
    @BindView(R.id.sport_text)
    TextView sportText;
    @BindView(R.id.bing_pic_img)
    ImageView bingPicImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决状态栏颜色问题
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_weather);
        //初始化ButterKnife
        ButterKnife.bind(this);
        //fixme 缓存
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather",null);
        if (weatherString != null){
            //有缓存时，直接解析天气数据
            WeatherNow weatherNow = Utility.handleWeatherNowResponse(prefs.getString("weatherNow",null));
            AirNow airNow = Utility.handleAirNowResponse(prefs.getString("airNow",null));
            WeatherForecast weatherForecast = Utility.handleWeatherForecastResponse(prefs.getString("weatherForecast",null));
            WeatherLifestyle weatherLifestyle = Utility.handleWeatherLifestyleResponse(prefs.getString("weatherLifestyle",null));
            showWeatherInfo(weatherNow,airNow,weatherForecast,weatherLifestyle);
        }else {
            //无缓存时去服务器查询天气
            String weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

        //加载背景图片
        String bingPic = prefs.getString("bing_pic",null);
        if (bingPic != null){
            Glide.with(this).load(bingPic).into(bingPicImg);
        }else {
            loadBingPic();
        }
    }


    //----根据天气id请求城市天气信息------

    //获取实况天气
    private void requestWeather(final String weatherId) {
        //需要获取四种数据 Now，AirNow，Forecast，LifeStyle
        String weatherNowUrl = "https://free-api.heweather.com/s6/weather/now?location="
                + weatherId + "&key=7308de07cfe14045924b52dd7369ba66";
        requestWeatherPlugin(weatherNowUrl,"weatherNow");

        String airNowUrl = "https://free-api.heweather.com/s6/air/now?location="
                + weatherId + "&key=7308de07cfe14045924b52dd7369ba66";
        requestWeatherPlugin(airNowUrl,"airNow");

        String weatherForecastUrl = "https://free-api.heweather.com/s6/weather/forecast?location="
                + weatherId + "&key=7308de07cfe14045924b52dd7369ba66";
        requestWeatherPlugin(weatherForecastUrl,"weatherForecast");

        String weatherLifestyleUrl = "https://free-api.heweather.com/s6/weather/lifestyle?location="
                + weatherId + "&key=7308de07cfe14045924b52dd7369ba66";
        requestWeatherPlugin(weatherLifestyleUrl,"weatherLifestyle");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        final WeatherNow weatherNow = Utility.handleWeatherNowResponse(prefs.getString("weatherNow",null));
        final AirNow airNow = Utility.handleAirNowResponse(prefs.getString("airNow",null));
        final WeatherForecast weatherForecast = Utility.handleWeatherForecastResponse(prefs.getString("weatherForecast",null));
        final WeatherLifestyle weatherLifestyle = Utility.handleWeatherLifestyleResponse(prefs.getString("weatherLifestyle",null));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (weatherNow != null && "ok".equals(weatherNow.status)
                        &&airNow != null && "ok".equals(airNow.status)
                        &&weatherForecast != null && "ok".equals(weatherForecast.status)
                        &&weatherLifestyle != null && "ok".equals(weatherLifestyle.status)){

                    //显示
                    showWeatherInfo(weatherNow,airNow,weatherForecast,weatherLifestyle);
                } else {
                    Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //显示背景图片
        loadBingPic();
    }

    private void requestWeatherPlugin(final String url, final String nameStr) {
        HttpUtils.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString(nameStr, responseText).apply();
            }
        });
    }



    //处理并展示Weather实体类中的数据
    private void showWeatherInfo(WeatherNow weatherNow, AirNow airNow, WeatherForecast weatherForecast, WeatherLifestyle weatherLifestyle) {
        if (weatherNow != null && "ok".equals(weatherNow.status)
                &&airNow != null && "ok".equals(airNow.status)
                &&weatherForecast != null && "ok".equals(weatherForecast.status)
                &&weatherLifestyle != null && "ok".equals(weatherLifestyle.status)){

            String cityName = weatherNow.basic.cityName;
            String updateTime = weatherNow.update.loc.split(" ")[1];
            String degree = weatherNow.now.temperature + "℃";
            String weatherInfo = weatherNow.now.condTxt;
            titleCity.setText(cityName);
            titleUpdateTime.setText(updateTime);
            degreeText.setText(degree);
            weatherInfoText.setText(weatherInfo);
            forecastLayout.removeAllViews();
            //未来几日天气
            for (WeatherForecast.DailyForecast forecast : weatherForecast.dailyForecastList) {
                View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
                TextView dateText = view.findViewById(R.id.date_text);
                TextView infoText = view.findViewById(R.id.info_text);
                TextView maxText = view.findViewById(R.id.max_text);
                TextView minText = view.findViewById(R.id.min_text);
                dateText.setText(forecast.date);
                infoText.setText(forecast.condTextDay);
                maxText.setText(forecast.tmpMax);
                minText.setText(forecast.tmpMin);
                forecastLayout.addView(view);
            }
            //TODO 如果查不到，可以查询市区名的气象状态

            if (airNow.airNowCity.aqi != null)
                aqiText.setText(airNow.airNowCity.aqi);
            else
                aqiText.setText("未知");
            if (airNow.airNowCity.pm25 != null)
                pm25Text.setText(airNow.airNowCity.pm25);
            else
                pm25Text.setText("未知");

            String comfort = "舒适度：" + weatherLifestyle.lifestyleList.get(1);
            String carWash = "洗车指数：" + weatherLifestyle.lifestyleList.get(2);
            String sport = "运动建议：" + weatherLifestyle.lifestyleList.get(3);
            comfortText.setText(comfort);
            carWashText.setText(carWash);
            sportText.setText(sport);
            weatherLayout.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
        }
    }

    //加载背景图片
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtils.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic",bingPic).apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
}


/*HttpUtils.sendOkHttpRequest(weatherLifestyleUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                //将JSON解析为Weather
                final WeatherNow weatherNow = Utility.handleWeatherNowResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weatherNow != null && "ok".equals(weatherNow.status)){
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weatherNow", responseText).apply();
                            //showWeatherInfo(weatherNow);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });*/

