package com.example.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stepcounter.R;

import org.w3c.dom.Text;

public class StepCounter extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;
    private boolean running = false;
    private int totalSteps = 0;
    private int previousTotalSteps = 0;
    private TextView tvSteps;
    private double previousMagnitude = 0;
    private int stepCount = 0;
    private double highestMagnitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steps_screen);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tvSteps = (TextView) findViewById(R.id.steps);

        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(StepCounter.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //tvSteps.setText(String.valueOf(sensorEvent.values[0]));
        //Log.d("KELEV", String.valueOf(sensorEvent.values[0]));
        Log.d("KELEV", "OnSensorChanged X: " + sensorEvent.values[0] + ", OnSensorChanged Y: " + sensorEvent.values[1] + ", OnSensorChanged Z: " + sensorEvent.values[2]);
        if(sensorEvent != null){
            float xAcceleration = sensorEvent.values[0];
            float yAcceleration = sensorEvent.values[1];
            float zAcceleration = sensorEvent.values[2];

            double magnitude = Math.sqrt(xAcceleration*xAcceleration + yAcceleration*yAcceleration + zAcceleration*zAcceleration );
            double magnitudeDelta = magnitude - previousMagnitude;
            previousMagnitude = magnitude;

            if(magnitudeDelta > 10 && magnitudeDelta < 13){
                stepCount++;
            }

            tvSteps.setText(String.valueOf(stepCount));

        }
    }
}
