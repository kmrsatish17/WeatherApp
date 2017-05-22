package com.example.homework5satish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    //List<CityWeather.HourlyForecast> hourlyForList = new ArrayList<CityWeather.HourlyForecast>();
    CityWeather.HourlyForecast hourlyForecastObj = new CityWeather.HourlyForecast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        hourlyForecastObj = (CityWeather.HourlyForecast) getIntent().getSerializableExtra(CityWeatherActivity.WEATHER_LIST_KEY);
        Log.d("aaaa" , "$$$ DetailsActivity " + hourlyForecastObj.toString());

        TextView locTag = (TextView)findViewById(R.id.tDetLocaVal);

        ImageView  imageViewIcon = (ImageView) findViewById(R.id.imageViewDetails);
        TextView tTemp = (TextView) findViewById(R.id.textViewTempDet);
        TextView tCloud = (TextView) findViewById(R.id.textViewCloudDet);

        TextView tFeel = (TextView) findViewById(R.id.textViewFeelVal);
        TextView tHum = (TextView) findViewById(R.id.textViewHumVal);
        TextView tDew = (TextView) findViewById(R.id.textViewDewVal);
        TextView tPree = (TextView) findViewById(R.id.textViewPreVal);
        TextView tClo = (TextView) findViewById(R.id.textViewCloudVal);
        TextView tWinds = (TextView) findViewById(R.id.textViewWindVal);

        locTag.setText(MainActivity.getCity().toUpperCase().replaceAll("_" , " ") + ", " + MainActivity.getState().toUpperCase() + " (" + hourlyForecastObj.FCTTIME.civil + ")");
        Picasso.with(this).load(hourlyForecastObj.icon_url).into(imageViewIcon);
        tTemp.setText(hourlyForecastObj.temp.english + " F");
        tCloud.setText(hourlyForecastObj.condition);
        tFeel.setText(hourlyForecastObj.feelslike.english + " Fahrenheit");
        tHum.setText(hourlyForecastObj.humidity + " %");
        tDew.setText(hourlyForecastObj.dewpoint.english + " Fahrenheit");
        tPree.setText(hourlyForecastObj.mslp.english + " hPa");
        tClo.setText(hourlyForecastObj.wx);
        tWinds.setText(hourlyForecastObj.wspd.english + " mph, " + hourlyForecastObj.wdir.degrees + " " + hourlyForecastObj.wdir.dir );




    }
}
