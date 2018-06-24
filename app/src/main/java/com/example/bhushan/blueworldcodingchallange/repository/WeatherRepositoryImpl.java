package com.example.bhushan.blueworldcodingchallange.repository;

import com.example.bhushan.blueworldcodingchallange.data.WeatherApiService;
import com.example.bhushan.blueworldcodingchallange.di.modules.Constants;
import com.example.bhushan.blueworldcodingchallange.models.Forecast;
import com.example.bhushan.blueworldcodingchallange.models.ForecastWeek;

import javax.inject.Inject;

import io.reactivex.Single;

public class WeatherRepositoryImpl implements WeatherRepository {

    private WeatherApiService weatherApiService;

    @Inject
    WeatherRepositoryImpl(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    @Override
    public Single<Forecast> getForecast(String cityName) {
        return weatherApiService.getCurrentWeather(cityName, Constants.APP_ID, "metric", "en");
    }

    @Override
    public Single<ForecastWeek> getWeekForecast(String cityName) {
        return weatherApiService.getWeekWeather(cityName, Constants.APP_ID, "metric", "en");
    }

}
