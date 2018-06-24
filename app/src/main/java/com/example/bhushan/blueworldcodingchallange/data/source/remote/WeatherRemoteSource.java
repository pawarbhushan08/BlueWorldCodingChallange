package com.example.bhushan.blueworldcodingchallange.data.source.remote;

import com.example.bhushan.blueworldcodingchallange.data.WeatherApiService;
import com.example.bhushan.blueworldcodingchallange.data.source.RemoteSource;
import com.example.bhushan.blueworldcodingchallange.models.Forecast;

import javax.inject.Inject;

import io.reactivex.Single;

public class WeatherRemoteSource implements RemoteSource {

    private WeatherApiService weatherApiService;

    @Inject
    public WeatherRemoteSource(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    @Override
    public Single<Forecast> getCurrentForecast(String cityId) {
        return null;
    }
}
