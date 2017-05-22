package com.example.homework5satish;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Satish on 10/9/2016.
 */
public class CityWeather implements Serializable{

    Response response;
    String updatedOn;
    String cityNameG;
    String stateNameG;
    ArrayList<HourlyForecast> hourly_forecast = new ArrayList<HourlyForecast>();

    public static class HourlyForecast implements Serializable{

        FCTTIMES FCTTIME;
        LangValue temp;
        LangValue dewpoint;
        String condition;
        String icon;
        String icon_url;
        String fctcode;
        String sky;
        LangValue wspd;
        WDIR wdir;
        String wx;
        String uvi;
        String humidity;
        LangValue windchill;
        LangValue heatindex;
        LangValue feelslike;
        LangValue qpf;
        LangValue snow;
        String pop;
        LangValue mslp;

        @Override
        public String toString() {
            return "HourlyForecast{" +
                    "FCTTIME=" + FCTTIME +
                    ", temp=" + temp +
                    ", dewpoint=" + dewpoint +
                    ", condition='" + condition + '\'' +
                    ", icon='" + icon + '\'' +
                    ", icon_url='" + icon_url + '\'' +
                    ", fctcode='" + fctcode + '\'' +
                    ", sky='" + sky + '\'' +
                    ", wspd=" + wspd +
                    ", wdir=" + wdir +
                    ", wx='" + wx + '\'' +
                    ", uvi='" + uvi + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", windchill=" + windchill +
                    ", heatindex=" + heatindex +
                    ", feelslike=" + feelslike +
                    ", qpf=" + qpf +
                    ", snow=" + snow +
                    ", pop='" + pop + '\'' +
                    ", mslp=" + mslp +
                    '}';
        }



    }


    public static class WDIR implements Serializable{

        String dir;
        String degrees;

    }

    public  static class  LangValue implements Serializable{

        String english;
        String metric;

    }
    public static class FCTTIMES implements Serializable {

        String hour;
        String hour_padded;
        String min;
        String min_unpadded;
        String sec;
        String year;
        String mon;
        String mon_padded;
        String mon_abbrev;
        String mday;
        String mday_padded;
        String yday;
        String isdst;
        String epoch;
        String pretty;
        String civil;
        String month_name;
        String month_name_abbrev;
        String weekday_name;
        String weekday_name_night;
        String weekday_name_abbrev;
        String weekday_name_unlang;
        String weekday_name_night_unlang;
        String ampm;
        String tz;
        String age;
        String UTCDATE;

    }


    public static class Response implements Serializable{

        String version;
        String termsofService;
        Features features;
        Errors error;

    }

    public static class Features implements Serializable{
        String hourly;

    }

    public static class Errors implements Serializable{
        String type;
        String description;

    }

    @Override
    public String toString() {
        return "CityWeather{" +
                "response=" + response +
                ", hourly_forecast=" + hourly_forecast +
                '}';
    }

}