package com.example.homework5satish;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CityWeatherActivity extends AppCompatActivity implements GetWeatherAsyncTask.IPassCityWeather {

    String cityNameSelected;
    String stateNameSelected;
    CityWeather city ;
    public static final String WEATHER_LIST_KEY = "weather_list";
    ProgressDialog pd = null;

    public CityWeather getCity() {
        return city;
    }

    public void setCity(CityWeather city) {
        this.city = city;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading Hourly Data");
        pd.setCancelable(false);
        pd.setMax(100);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();

        cityNameSelected = (String) getIntent().getStringExtra(MainActivity.CITY_KEY);
        stateNameSelected = (String) getIntent().getStringExtra(MainActivity.STATE_KEY);

        new GetWeatherAsyncTask(this).execute("http://api.wunderground.com/api/7ef919c61d7a6783/hourly/q/"+stateNameSelected+"/"+cityNameSelected+".json");

    }



    @Override
    public void passCityData(final CityWeather cityPassed) {

        pd.dismiss();

        if (cityPassed.response.error == null) {

            TextView location = (TextView) findViewById(R.id.tLocation);
            location.setText(cityNameSelected.toUpperCase().replaceAll("_" , " ") + ", " + stateNameSelected.toUpperCase());
            ListView listViewW = (ListView) findViewById(R.id.listViewWeather);
            CityWeatherAdapter adapter = new CityWeatherAdapter(this, R.layout.row_item_weather, cityPassed.hourly_forecast);
            listViewW.setAdapter(adapter);
            adapter.setNotifyOnChange(true);


            ImageView addFav = (ImageView) findViewById(R.id.butFav);

            addFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(CityWeatherActivity.this, v);

                    popup.inflate(R.menu.add_to_fav);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.addToFavourite:

                                    DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
                                    cityPassed.cityNameG = cityNameSelected;
                                    cityPassed.stateNameG = stateNameSelected;
                                    cityPassed.updatedOn = df.format(new Date());
                                    MainActivity.favTag.setVisibility(View.VISIBLE);
                                    MainActivity.noCity.setVisibility(View.GONE);

                                    for (int i = 0; i < MainActivity.favCityList.size(); i++) {
                                        CityWeather ctw = MainActivity.favCityList.get(i);
                                        if (ctw.cityNameG.equals(cityPassed.cityNameG) && ctw.stateNameG.equals(cityPassed.stateNameG)) {
                                            MainActivity.favCityList.remove(i);
                                            MainActivity.favCityList.add(cityPassed);
                                            MainActivity.adaFav.notifyDataSetChanged();
                                            Toast.makeText(CityWeatherActivity.this, "Updated Favourites Record", Toast.LENGTH_SHORT).show();
                                            return true;
                                        }
                                    }
                                    MainActivity.favCityList.add(cityPassed);
                                    MainActivity.adaFav.notifyDataSetChanged();
                                    Toast.makeText(CityWeatherActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();



                            }
                            return true;
                        }
                    });
                    popup.show();

                }
            });

            listViewW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(CityWeatherActivity.this, DetailsActivity.class);
                    intent.putExtra(WEATHER_LIST_KEY, (Serializable) cityPassed.hourly_forecast.get(position));
                    startActivity(intent);
                    Log.d("bbbb", "*** " + cityPassed.hourly_forecast.get(position));
                }
            });


        }else {
        Toast.makeText(this, cityPassed.response.error.description, Toast.LENGTH_SHORT).show();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    finish();
                }
            }, 5000);
    }

    }
}
