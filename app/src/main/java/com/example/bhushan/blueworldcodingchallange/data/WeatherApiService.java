package com.example.bhushan.blueworldcodingchallange.data;

import com.example.bhushan.blueworldcodingchallange.models.Forecast;
import com.example.bhushan.blueworldcodingchallange.models.ForecastWeek;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

    @GET("data/2.5/weather?")
    Single<Forecast> getCurrentWeather(@Query("q") String cityName,
                                       @Query("appid") String appId,
                                       @Query("units") String units,
                                       @Query("lang") String lang);

    @GET("data/2.5/forecast?")
    Single<ForecastWeek> getWeekWeather(@Query("q") String cityName,
                                        @Query("appid") String appId,
                                        @Query("units") String units,
                                        @Query("lang") String lang);

}
