package com.example.bhushan.blueworldcodingchallange.domain.forecast;

import com.example.bhushan.blueworldcodingchallange.models.Forecast;
import com.example.bhushan.blueworldcodingchallange.models.ForecastWeek;
import com.example.bhushan.blueworldcodingchallange.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class ForecastInteractor {

    private WeatherRepository weatherRepository;

    @Inject
    public ForecastInteractor(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Single<Forecast> getForecast(String cityName) {
        return weatherRepository.getForecast(cityName);
    }

    public Single<ForecastWeek> getWeekForecast(String cityName) {
        return weatherRepository.getWeekForecast(cityName);
    }

    /**
     * Temperature equivalent perceived by humans
     * form http://www.bom.gov.au/info/thermal_stress/
     */
    public double getApparentTemperature(double temperature, int humidity, double windSpeed) {
        double pressure = humidity / 100 * 6.105 * Math.exp(17.27 * temperature / (237.7 / temperature));
        return Math.round((temperature + 0.33 * pressure + 0.7 * windSpeed - 4) * 100.0) / 100.0;
    }

}
