package com.example.homework5satish;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Satish on 10/10/2016.
 */
public class CityWeatherAdapter extends ArrayAdapter<CityWeather.HourlyForecast> {

    Context lContext ;
    int lResource;
    List<CityWeather.HourlyForecast> lWeatherList = new ArrayList<CityWeather.HourlyForecast>();

    public CityWeatherAdapter(Context context, int resource, List<CityWeather.HourlyForecast> objects) {
        super(context, resource, objects);

        this.lContext = context;
        this.lResource = resource;
        this.lWeatherList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)lContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(lResource, parent, false);
        }

        CityWeather.HourlyForecast hf = lWeatherList.get(position);

        Log.d("demo" , "&&& lWeatherList.get(position) " + lWeatherList.get(position));

        ImageView imageViewW = (ImageView)convertView.findViewById(R.id.imageViewWeather);
        TextView textViewTime = (TextView)convertView.findViewById(R.id.textViewTime);
        TextView textViewCloud = (TextView)convertView.findViewById(R.id.textViewCloud);
        TextView textViewTemp = (TextView)convertView.findViewById(R.id.textViewTemp);

        Picasso.with(lContext).load(hf.icon_url).into(imageViewW);
        textViewTime.setText(hf.FCTTIME.civil);
        textViewCloud.setText(hf.condition);
        textViewTemp.setText(hf.temp.english + " F");

        return convertView;
    }


}
