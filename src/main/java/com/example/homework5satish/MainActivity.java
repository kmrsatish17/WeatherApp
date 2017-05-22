package com.example.homework5satish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText cityName ;
    EditText stateName ;
    Button buttonSubmit ;
    public static final String CITY_KEY = "cityName";
    public static final String STATE_KEY = "stateName";
    public static ArrayList<CityWeather> favCityList = new ArrayList<>();
    public static String city = "";
    public static String state = "";
    public static FavCityAdapter adaFav;
    public  static TextView noCity ;
    public static TextView favTag;




    public static String getCity() {

        return city;
    }

    public static void setCity(String city) {
        MainActivity.city = city;
    }

    public static String getState() {
        return state;
    }

    public static void setState(String state) {
        MainActivity.state = state;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = (EditText) findViewById(R.id.textViewCity);
        stateName = (EditText) findViewById(R.id.textViewState);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("demo" ,"$$$ cityName.getText()" + cityName.getText() );
                Log.d("demo" ,"$$$ stateName.getText()" + stateName.getText() );
                city = cityName.getText().toString().trim().toLowerCase().replace(" " ,"_");
                state = stateName.getText().toString().replaceAll(" ", "").toLowerCase();
                setCity(city);
                setState(state);

                if(city != null && !city.isEmpty() && state !=null && !state.isEmpty() && state.length() == 2){

                    Intent intent = new Intent(MainActivity.this , CityWeatherActivity.class);
                    intent.putExtra(CITY_KEY , city);
                    intent.putExtra(STATE_KEY , state);
                    startActivity(intent);
                }else {

                    Toast.makeText(MainActivity.this, "Not proper Input", Toast.LENGTH_SHORT).show();
                }


            }
        });


            favTag = (TextView) findViewById(R.id.textVFavTag);
            favTag.setText("Favorites");
            noCity = (TextView) findViewById(R.id.textViewNoCity);
            noCity.setText("There is no city in your favourite");
            favTag.setVisibility(View.GONE);
            noCity.setVisibility(View.VISIBLE);
            ListView lFav = (ListView) findViewById(R.id.listViewFav);
            adaFav = new FavCityAdapter(this, R.layout.fav_item_display, favCityList);
            lFav.setAdapter(adaFav);
            adaFav.setNotifyOnChange(true);
            lFav.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    favCityList.remove(position);
                    if (favCityList.size() == 0){
                        favTag.setVisibility(View.GONE);
                        noCity.setVisibility(View.VISIBLE);

                    }
                    adaFav.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "City Deleted", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

    }
}
