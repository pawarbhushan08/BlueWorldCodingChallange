package com.example.bhushan.blueworldcodingchallange.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.bhushan.blueworldcodingchallange.data.source.remote.exceptions.NoNetworkException;
import com.example.bhushan.blueworldcodingchallange.domain.Location.LocationInteractor;
import com.example.bhushan.blueworldcodingchallange.presentation.Common.Rx.RxSchedulersProvider;
import com.example.bhushan.blueworldcodingchallange.presentation.view.start.StartView;

import java.util.ArrayList;

import javax.inject.Inject;

@InjectViewState
public class StartPresenter extends MvpPresenter<StartView> {

    private LocationInteractor locationInteractor;
    private RxSchedulersProvider rxSchedulersProvider;

    @Inject
    public StartPresenter(LocationInteractor locationInteractor, RxSchedulersProvider rxSchedulersProvider) {
        this.rxSchedulersProvider = rxSchedulersProvider;
        this.locationInteractor = locationInteractor;
    }

    public void onCheckLocation() {
        locationInteractor.getLocation()
                .compose(rxSchedulersProvider.getIoToMainTransformerSingle())
                .subscribe(this::onLocationLoadSuccess, this::onLocationLoadError);
    }

    private void onHistoryLoadError(Throwable throwable) {
        if(throwable instanceof NoNetworkException) {
            throwable.printStackTrace();
        }
    }

    private void onHistoryLoadSuccess(ArrayList<String> cities) {
        getViewState().showHistory(cities);
    }

    private void onLocationLoadError(Throwable throwable) {
        if(throwable instanceof NoNetworkException) {
            throwable.printStackTrace();
        }
    }

    private void onLocationLoadSuccess(String cityName) {
        if(cityName.length() > 0) {
            getViewState().loadForecast(cityName);
        }
    }

    public void onSetCityName(String cityName) {
        locationInteractor.setLocation(cityName)
                .compose(rxSchedulersProvider.getIoToMainTransformerCompletableCompletable())
                .subscribe(() -> onLocationLoadSuccess(cityName));
    }

    public void onShowHistory() {
        locationInteractor.getHistory()
                .compose(rxSchedulersProvider.getIoToMainTransformerSingle())
                .subscribe(this::onHistoryLoadSuccess, this::onHistoryLoadError);
    }
}
