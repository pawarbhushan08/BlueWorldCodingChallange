package com.example.bhushan.blueworldcodingchallange.models.view;

import com.example.bhushan.blueworldcodingchallange.models.ForecastList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ForecastItem {

    public String date;
    public String weather;
    public String snow;
    public String temp;


    public ForecastItem(ForecastList forecastList) {
        date = getDate(forecastList.getDtTxt());
        weather = forecastList.getWeather().get(0).getMain();
        temp = forecastList.getMain().getTemp() + "°";
        try {
            snow = getSnowfallRisk(forecastList.getSnow().get3h());
        }
        catch (NullPointerException e) {
             snow = "Data Unavailable";
        }


    }

    private String getSnowfallRisk(Double snowVol) {
        /*
        AMOFSG/7-IP/4 27/.6/08
        The Experts at the WMO 1997 meeting suggested that the
        LWE intensity for snowfall  could be defined as follows:
        0 – 1 mm/hr:  Light
        1- 5.0 mm/hr: Moderate
        > 5.0 mm/hr:  Heavy
        */
        String snowFall;
        double snowVolPerHr = snowVol/3;//As the API readings are for every 3 hrs
        if (snowVolPerHr > 0 && snowVolPerHr < 1.0){
          snowFall = "Light";
        }else if (snowVolPerHr >= 1.0 && snowVolPerHr < 5.0){
            snowFall = "Moderate";
        }else {
            snowFall = "High";
        }
        return snowFall;
    }

    private String getDate(String dtTxt) {
        Date d;
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        SimpleDateFormat newFormat = new SimpleDateFormat("hh:mm E", Locale.getDefault());

        try {
            d = oldFormat.parse(dtTxt);
        }
        catch (ParseException e) {
            return dtTxt;
        }

        return newFormat.format(d);
    }

}
