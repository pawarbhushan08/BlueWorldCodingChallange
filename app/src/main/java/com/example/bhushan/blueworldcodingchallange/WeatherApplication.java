package com.example.bhushan.blueworldcodingchallange;

import android.app.Application;
import android.content.Context;

import com.example.bhushan.blueworldcodingchallange.di.ApplicationComponent;
import com.example.bhushan.blueworldcodingchallange.di.DaggerApplicationComponent;
import com.example.bhushan.blueworldcodingchallange.di.modules.ApplicationModule;
import com.example.bhushan.blueworldcodingchallange.di.modules.DataModule;

public class WeatherApplication extends Application {

    private static ApplicationComponent applicationComponent;
    private static WeatherApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        applicationComponent = buildComponent();
    }

    public ApplicationComponent buildComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public static Context getInstance() {
        return instance;
    }

    public static ApplicationComponent getComponent() {
        return applicationComponent;
    }

}
