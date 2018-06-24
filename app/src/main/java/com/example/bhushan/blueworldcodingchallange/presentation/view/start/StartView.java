package com.example.bhushan.blueworldcodingchallange.presentation.view.start;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

public interface StartView extends MvpView {

    void clearView();

    void showMsg(String msg);

    void loadForecast(String cityName);

    void showHistory(ArrayList<String> cities);
}
