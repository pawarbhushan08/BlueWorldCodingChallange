package com.example.bhushan.blueworldcodingchallange.di.modules;

import com.example.bhushan.blueworldcodingchallange.data.WeatherApiService;
import com.example.bhushan.blueworldcodingchallange.data.source.remote.NetworkChecker;
import com.example.bhushan.blueworldcodingchallange.data.source.remote.WeatherRemoteSource;
import com.example.bhushan.blueworldcodingchallange.data.source.remote.interceptors.NetworkCheckInterceptor;
import com.example.bhushan.blueworldcodingchallange.repository.LocationRepository;
import com.example.bhushan.blueworldcodingchallange.repository.LocationRepositoryImpl;
import com.example.bhushan.blueworldcodingchallange.repository.WeatherRepository;
import com.example.bhushan.blueworldcodingchallange.repository.WeatherRepositoryImpl;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    private final String baseUrl;

    public DataModule() {
        this.baseUrl = "http://api.openweathermap.org/";
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherRepositoryImpl weatherRepository) {
        return weatherRepository;
    }

    @Provides
    @Singleton
    LocationRepository provideLocationRepository(LocationRepositoryImpl locationRepository) {
        return locationRepository;
    }

    @Provides
    @Singleton
    WeatherRemoteSource provideWeatherDataSource(WeatherApiService apiService) {
        return new WeatherRemoteSource(apiService);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(NetworkChecker networkChecker) {
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new NetworkCheckInterceptor(networkChecker));

        return okHttpBuilder.build();
    }

    @Provides
    @Singleton
    WeatherApiService provideWeatherApiService(Retrofit retrofit) {
        return retrofit.create(WeatherApiService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return new Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build();
    }

}