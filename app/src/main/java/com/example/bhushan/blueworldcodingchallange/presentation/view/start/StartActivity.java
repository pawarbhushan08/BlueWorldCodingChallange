package com.example.bhushan.blueworldcodingchallange.presentation.view.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.bhushan.blueworldcodingchallange.R;
import com.example.bhushan.blueworldcodingchallange.WeatherApplication;
import com.example.bhushan.blueworldcodingchallange.di.DaggerStartPresenterComponent;
import com.example.bhushan.blueworldcodingchallange.di.StartPresenterComponent;
import com.example.bhushan.blueworldcodingchallange.presentation.presenter.StartPresenter;
import com.example.bhushan.blueworldcodingchallange.presentation.view.home.HomeActivity;

import java.util.ArrayList;

public class StartActivity extends MvpAppCompatActivity implements StartView {

    private ListView citiesList;
    private EditText cityNameEdit;

    @InjectPresenter
    StartPresenter startPresenter;

    @ProvidePresenter
    StartPresenter providePresenter() {
        StartPresenterComponent presenterComponent = DaggerStartPresenterComponent.builder()
                .applicationComponent(WeatherApplication.getComponent())
                .build();

        return presenterComponent.getPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startPresenter.onShowHistory();

        if(!getIntent().getBooleanExtra("reset", false)) {
            startPresenter.onCheckLocation();
        }

        init();
    }

    private void init() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        citiesList = findViewById(R.id.cities_list);
        cityNameEdit = findViewById(R.id.city_name_edit);

        findViewById(R.id.done_btn)
                .setOnClickListener(v -> startPresenter.onSetCityName(cityNameEdit.getText().toString()));
    }

    @Override
    public void clearView() {
        cityNameEdit.setText("");
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadForecast(String cityName) {
        Intent intent = new Intent(this, HomeActivity.class);

        intent.putExtra("city_name", cityName);

        startActivity(intent);
    }

    @Override
    public void showHistory(ArrayList<String> cities) {
        if(cities.size() > 0) {
            findViewById(R.id.resent_locations_text).setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < cities.size(); i ++) {
            cities.set(i, cities.get(i).substring(1, cities.get(i).length()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.history_item, cities);
        citiesList.setAdapter(adapter);
        citiesList.setOnItemClickListener((parent, view, position, id) -> loadForecast(adapter.getItem(position)));
    }
}
