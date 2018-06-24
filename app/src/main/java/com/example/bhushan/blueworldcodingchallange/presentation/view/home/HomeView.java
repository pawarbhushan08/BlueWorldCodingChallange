package com.example.bhushan.blueworldcodingchallange.presentation.view.home;

import com.arellomobile.mvp.MvpView;
import com.example.bhushan.blueworldcodingchallange.models.view.ForecastItem;

import java.util.ArrayList;

public interface HomeView extends MvpView {
    void showProgress();

    void hideProgress();

    void showForecast(Double temp);

    void setCityName(String name);

    void setCountryName(String displayCountry);

    void setDescription(String description);

    void setApparentTemp(Double temp);

    void setHumidity(Integer humidity);

    void setWind(Double speed);

    void setPressure(Integer pressure);

    void setVisibility(int i);

    void setSunset(String sunset);

    void showLocationActivity();

    void setWeekForecast(ArrayList<ForecastItem> forecastItems);

    void showNoNetworkLayout();
}
