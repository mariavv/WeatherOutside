package com.maria.weatheroutside.weatherwindow;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.maria.weatheroutside.R;
import com.maria.weatheroutside.model.entity.WeatherData;

import java.util.Date;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    private final WeatherPresenter presenter = new WeatherPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.sync:
                presenter.sync();
                return true;

            case R.id.exit:
                presenter.exitBtnPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showWeather(WeatherData weatherData) {
        String s = String.format(getString(R.string.weatherInfo),
                weatherData.getMain().getTemp(), weatherData.getMain().getPressure(),
                weatherData.getMain().getHumidity(),
                weatherData.getWeather().get(0).getDescription(),
                new Date().toString());
        TextView weatherView = findViewById(R.id.weatherView);
        weatherView.setText(s);
    }

    @Override
    public void errorGetWeather(Throwable throwable) {
        showMessage(throwable.getMessage());
    }

    @Override
    public void requestPermission(String[] permissions, int requestCodePermission) {
        ActivityCompat.requestPermissions(this, permissions, requestCodePermission);
    }

    @Override
    public void showMessage(int messageRes) {
        showMessage(getString(messageRes));
    }

    @Override
    public void close() {
        exit();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void exit() {
        finish();
    }
}
