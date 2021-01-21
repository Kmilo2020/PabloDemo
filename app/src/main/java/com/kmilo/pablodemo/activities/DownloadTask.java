package com.kmilo.pablodemo.activities;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {

    String result = "";
    URL url;
    HttpURLConnection urlConnection = null;
    JSONObject mJSONObject;
    public  String message = "";


    @Override
    protected String doInBackground(String... urls) {
        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while (data != -1){
                char current = (char) data;
                result += current;
                data = reader.read();
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            mJSONObject = new JSONObject(s);
            String weatherInfo = mJSONObject.getString("weather");
            Log.i("weatherInfo",weatherInfo);
            JSONArray arr = new JSONArray(weatherInfo);

            for (int i=0; i<arr.length(); i++){
                JSONObject jsonPart = arr.getJSONObject(i);
                String main = jsonPart.getString("main");
                Log.i("main",main);
                String description = jsonPart.getString("description");
                Log.i("description",description);

                if (!main.equals("") && !description.equals("")){
                    message += main + ": " + description + "\r\n"; // SIGNIFICA \r\n UN SALTO DE LINEA
                    Log.i("message",message);
                }
            }

            if (!message.equals("")){
                WeatherActivity.txtInfoWeather.setText(message);
            }else {
                Toast.makeText(WeatherActivity.context, "Could not find weather", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
