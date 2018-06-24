package com.example.bhushan.blueworldcodingchallange.repository;

import com.example.bhushan.blueworldcodingchallange.models.Forecast;
import com.example.bhushan.blueworldcodingchallange.models.ForecastWeek;

import io.reactivex.Single;

public interface WeatherRepository {

    Single<Forecast> getForecast(String cityName);

    Single<ForecastWeek> getWeekForecast(String cityName);

}
