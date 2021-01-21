package com.kmilo.pablodemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.kmilo.pablodemo.activities.BrainActivity;
import com.kmilo.pablodemo.activities.ConnectGameActivity;
import com.kmilo.pablodemo.activities.HikersActivity;
import com.kmilo.pablodemo.activities.MasterDetailsActivity;
import com.kmilo.pablodemo.activities.Retrofit2Activity;
import com.kmilo.pablodemo.activities.WeatherActivity;
import com.kmilo.pablodemo.fragments.StartFragment;
import com.kmilo.pablodemo.interfaces.IComunicaFragments;

public class MainActivity extends AppCompatActivity implements IComunicaFragments, StartFragment.OnFragmentInteractionListener {
    Fragment startFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startFragment = new StartFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,startFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void retrofitLib() {
        Intent intent=new Intent(this, Retrofit2Activity.class);
        startActivity(intent);
    }

    @Override
    public void masterDetail() {
        Intent intent=new Intent(this, MasterDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void hikersWatch() {
        Intent intent=new Intent(this, HikersActivity.class);
        startActivity(intent);
    }

    @Override
    public void cityWeather() {
        Intent intent=new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    @Override
    public void connect3Games() {
        Intent intent=new Intent(this, ConnectGameActivity.class);
        startActivity(intent);
    }

    @Override
    public void brainTrainer() {
        Intent intent=new Intent(this, BrainActivity.class);
        startActivity(intent);
    }
}