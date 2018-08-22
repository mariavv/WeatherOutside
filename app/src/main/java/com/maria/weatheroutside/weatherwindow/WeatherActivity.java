package com.maria.weatheroutside.weatherwindow;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.maria.weatheroutside.R;
import com.maria.weatheroutside.model.entity.Weather;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    private TextView weatherView;

    private final WeatherPresenter presenter = new WeatherPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        initViews();

        presenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            /*case R.id.sync:
                presenter.sync();

                return true;*/
            case R.id.exit:
                presenter.exitBtnPressed();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showWeather(Weather weather) {
        weatherView.setText(weather.getMain());
    }

    @Override
    public void errorGetWeather(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions, RESULT_FIRST_USER);
    }

    @Override
    public void close() {
        exit();
    }

    private void exit() {
        finish();
    }

    private void initViews() {
        weatherView = findViewById(R.id.weather);
    }
}
