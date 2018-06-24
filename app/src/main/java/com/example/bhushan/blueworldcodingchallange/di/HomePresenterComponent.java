package com.example.bhushan.blueworldcodingchallange.di;

import com.example.bhushan.blueworldcodingchallange.di.modules.HomePresenterModule;
import com.example.bhushan.blueworldcodingchallange.di.scopes.Presenter;
import com.example.bhushan.blueworldcodingchallange.presentation.presenter.HomePresenter;

import dagger.Component;

@Presenter
@Component(dependencies = ApplicationComponent.class, modules = HomePresenterModule.class)
public interface HomePresenterComponent {

    HomePresenter getPresenter();

}