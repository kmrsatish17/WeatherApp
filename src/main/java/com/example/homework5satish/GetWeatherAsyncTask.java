package com.example.homework5satish;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Satish on 10/9/2016.
 */
public class GetWeatherAsyncTask extends AsyncTask<String , Void , String> {

    CityWeather cityWeather = null ;

    IPassCityWeather passCity;

    GetWeatherAsyncTask(IPassCityWeather pass){
        this.passCity = pass;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Gson gson = null;

            gson = new GsonBuilder().create();
            cityWeather = gson.fromJson(s, CityWeather.class);
            passCity.passCityData(cityWeather);


      //Log.d("demo" , "$$$ Gson Object " + cityWeather.toString());

    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);

           // Log.d("HAHAHA", params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int status_code = connection.getResponseCode();

            if (status_code == HttpURLConnection.HTTP_OK){

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null){

                    sb.append(line+"\n");
                }
                // Log.d("HAHAHA", sb.toString());
                return sb.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static interface IPassCityWeather{

        public void passCityData(CityWeather cityWeath);
    }
}
