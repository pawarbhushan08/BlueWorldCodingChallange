package com.example.bhushan.blueworldcodingchallange.di;

import com.example.bhushan.blueworldcodingchallange.di.modules.ApplicationModule;
import com.example.bhushan.blueworldcodingchallange.di.modules.DataModule;
import com.example.bhushan.blueworldcodingchallange.repository.LocationRepository;
import com.example.bhushan.blueworldcodingchallange.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    WeatherRepository provideWeatherRepository();
    LocationRepository provideLocationRepository();

}