package com.example.bhushan.blueworldcodingchallange.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.bhushan.blueworldcodingchallange.data.source.remote.exceptions.NoNetworkException;
import com.example.bhushan.blueworldcodingchallange.domain.forecast.ForecastInteractor;
import com.example.bhushan.blueworldcodingchallange.models.Forecast;
import com.example.bhushan.blueworldcodingchallange.models.ForecastWeek;
import com.example.bhushan.blueworldcodingchallange.models.view.ForecastItem;
import com.example.bhushan.blueworldcodingchallange.presentation.Common.Rx.RxSchedulersProvider;
import com.example.bhushan.blueworldcodingchallange.presentation.view.home.HomeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {

    private ForecastInteractor forecastInteractor;
    private RxSchedulersProvider rxSchedulersProvider;
    private String cityName;

    @Inject
    public HomePresenter(ForecastInteractor forecastInteractor, RxSchedulersProvider rxSchedulersProvider, String cityName) {
        this.rxSchedulersProvider = rxSchedulersProvider;
        this.forecastInteractor = forecastInteractor;
        this.cityName = cityName;

        getViewState().showProgress();
    }

    public void onGetForecast() {
        forecastInteractor.getForecast(cityName)
                .compose(rxSchedulersProvider.getIoToMainTransformerSingle())
                .subscribe(this::onForecastLoadSuccess, this::onForecastLoadError);

        forecastInteractor.getWeekForecast(cityName)
                .compose(rxSchedulersProvider.getIoToMainTransformerSingle())
                .subscribe(this::onWeekForecastLoadSuccess, this::onForecastLoadError);
    }

    private void onWeekForecastLoadSuccess(ForecastWeek forecast) {
        if(forecast.getForecastList() == null) {
            return;
        }

        ArrayList<ForecastItem> forecastItems = new ArrayList<>();

        for(int i = 0; i < forecast.getForecastList().size(); i++) {
            ForecastItem item = new ForecastItem(forecast.getForecastList().get(i));
            forecastItems.add(item);
        }

        getViewState().setWeekForecast(forecastItems);
        getViewState().hideProgress();
    }

    private void onForecastLoadSuccess(Forecast forecast) {
        double apparentTemp = forecastInteractor.getApparentTemperature(forecast.getMain().getTemp(),
                forecast.getMain().getHumidity(),
                forecast.getWind().getSpeed());

        getViewState().showForecast(forecast.getMain().getTemp());
        getViewState().setCityName(forecast.getName());
        getViewState().setApparentTemp(apparentTemp);
        getViewState().setHumidity(forecast.getMain().getHumidity());
        getViewState().setWind(forecast.getWind().getSpeed());
        getViewState().setPressure(forecast.getMain().getPressure());
        getViewState().setVisibility(10);
        getViewState().setSunset(new SimpleDateFormat("HH:MM", Locale.ENGLISH).format(forecast.getSys().getSunset()));
        getViewState().setDescription(forecast.getWeather().get(0).getDescription());
        getViewState().setCountryName((new Locale("", forecast.getSys().getCountry())).getDisplayCountry());

        getViewState().hideProgress();
    }

    private void onForecastLoadError(Throwable throwable) {
        if(throwable instanceof NoNetworkException) {
            getViewState().hideProgress();
            getViewState().showNoNetworkLayout();
        }
    }

    public void onSetLocation() {
        getViewState().showLocationActivity();
    }
}

