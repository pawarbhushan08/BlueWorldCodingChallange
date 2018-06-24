package com.example.bhushan.blueworldcodingchallange.data.source;

import com.example.bhushan.blueworldcodingchallange.models.Forecast;

import io.reactivex.Single;

public interface RemoteSource {

        Single<Forecast> getCurrentForecast(String cityId);

}
