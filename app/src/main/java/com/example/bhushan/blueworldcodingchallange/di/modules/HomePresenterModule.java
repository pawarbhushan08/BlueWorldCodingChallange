package com.example.bhushan.blueworldcodingchallange.di.modules;

import com.example.bhushan.blueworldcodingchallange.di.scopes.Presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomePresenterModule {

    private final String cityName;

    public HomePresenterModule(String cityName) {
        this.cityName = cityName;
    }

    @Provides
    @Presenter
    String provideCityName() {
        return cityName;
    }

}
