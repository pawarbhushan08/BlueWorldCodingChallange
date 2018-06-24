package com.example.bhushan.blueworldcodingchallange.domain.Location;

import com.example.bhushan.blueworldcodingchallange.repository.LocationRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class LocationInteractor {

    private LocationRepository locationRepository;

    @Inject
    public LocationInteractor(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Single<String> getLocation() {
        return locationRepository.getLocation();
    }

    public Single<ArrayList<String>> getHistory() {
        return locationRepository.getHistory();
    }

    public Completable setLocation(String cityName) {
        return locationRepository.saveLocation(cityName);
    }


}
