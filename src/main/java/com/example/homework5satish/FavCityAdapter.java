package com.example.homework5satish;

import android.content.Context;
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
 * Created by Satish on 10/11/2016.
 */
public class FavCityAdapter extends ArrayAdapter<CityWeather> {

    Context favContext ;
    int favResource;
    List<CityWeather> favWeatherList = new ArrayList<>();


    public FavCityAdapter(Context context, int resource, ArrayList<CityWeather> objects) {
        super(context, resource, objects);

        this.favContext = context;
        this.favResource = resource;
        this.favWeatherList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)favContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(favResource, parent, false);
        }

        TextView tLocation = (TextView)convertView.findViewById(R.id.tFavLocation);
        TextView tFavTemp = (TextView)convertView.findViewById(R.id.tFavTemp);
        TextView tFavDate = (TextView)convertView.findViewById(R.id.tFavDate);
        Log.d("ccc" , "### " + MainActivity.getCity() + MainActivity.getState());

        if(favWeatherList != null ){
            CityWeather cw = favWeatherList.get(position);
            Log.d("xxx" , "xxx " + cw.toString());
            Log.d("yyy" , "yyy cw.cityNameG " + cw.cityNameG);
            Log.d("yyy" , "yyy cw.stateNameG" + cw.stateNameG);
            tLocation.setText(cw.cityNameG.replaceAll("_", " ").toUpperCase() + ", " + cw.stateNameG.toUpperCase());

            float avg = (float) 0.0;
            for (int i = 0 ; i < cw.hourly_forecast.size(); i++){

                avg += Float.parseFloat(cw.hourly_forecast.get(i).temp.english);

            }
            tFavTemp.setText((avg / cw.hourly_forecast.size()) + " F");
            tFavDate.setText("Updated on: " +cw.updatedOn);

        }


        return convertView;

    }
}
