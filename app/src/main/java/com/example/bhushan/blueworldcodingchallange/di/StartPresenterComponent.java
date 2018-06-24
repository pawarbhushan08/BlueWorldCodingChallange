package com.example.bhushan.blueworldcodingchallange.di;

import com.example.bhushan.blueworldcodingchallange.di.modules.StartPresenterModule;
import com.example.bhushan.blueworldcodingchallange.di.scopes.Presenter;
import com.example.bhushan.blueworldcodingchallange.presentation.presenter.StartPresenter;

import dagger.Component;

@Presenter
@Component(dependencies = ApplicationComponent.class, modules = StartPresenterModule.class)
public interface StartPresenterComponent {

    StartPresenter getPresenter();

}
