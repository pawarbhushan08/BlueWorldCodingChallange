package com.example.bhushan.blueworldcodingchallange.models.view;

import com.example.bhushan.blueworldcodingchallange.models.ForecastList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ForecastItem {

    public String date;
    public String weather;
    public String temp;


    public ForecastItem(ForecastList forecastList) {
        date = getDate(forecastList.getDtTxt());
        weather = forecastList.getWeather().get(0).getMain();
        temp = forecastList.getMain().getTemp() + "°";

    }

    private String getSnowfallRisk(Double snowVol) {
        /*
        0 – 1 mm/hr: Light
        1-2.5 mm/hr: Moderate
        > 2.5 mm/hr: Heavy
        */
        String snowFall;
        double snowVolPerHr = snowVol/3;
        if (snowVolPerHr > 0 && snowVolPerHr < 1){
          snowFall = "Light";
        }else if (snowVolPerHr >= 1 && snowVolPerHr < 2.5){
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
