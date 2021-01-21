package com.kmilo.pablodemo.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kmilo.pablodemo.R;

import java.net.URLEncoder;

public class WeatherActivity extends AppCompatActivity {

    EditText edtNameCity;
    static TextView txtInfoWeather;
    static Context context;
    DownloadTask task;
    String encodedCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        edtNameCity = findViewById(R.id.edtNameCity);
        txtInfoWeather = findViewById(R.id.txtInfoWeather);
        context = getApplicationContext();
    }

    public void getWeather(View view) {
        task = new DownloadTask();
        try {
            encodedCityName = URLEncoder.encode(edtNameCity.getText().toString(),"UTF-8");
            Log.i("nameCity",encodedCityName);
            task.execute("https://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=2cf76ddd58a89c57aa7c6697d204e95d");

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtNameCity.getWindowToken(), 0);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_SHORT).show();
        }
    }
}