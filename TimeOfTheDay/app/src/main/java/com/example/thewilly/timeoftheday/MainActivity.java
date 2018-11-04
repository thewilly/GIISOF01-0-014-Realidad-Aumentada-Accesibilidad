package com.example.thewilly.timeoftheday;

import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import static java.lang.Math.atan;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ImageView imageView;

    private SensorManager sensorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorService =	(SensorManager)	getSystemService(SENSOR_SERVICE);

        imageView = (ImageView) findViewById(R.id.brujula);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if	(event.sensor.getType()	!=	Sensor.TYPE_ACCELEROMETER) {
            Log.i("sensor", event.sensor.getType() + "");
        }

        if	(event.sensor.getType()	==	Sensor.TYPE_MAGNETIC_FIELD) {
            float[] values = event.values;
            double angle = 90-atan(values[1]/values[0])*180/Math.PI;

            Log.i("angle", ""+angle);

            Matrix matrix = new Matrix();
            imageView.setScaleType(ImageView.ScaleType.MATRIX);   //required
            matrix.postRotate((float) angle, 0, 0);
            imageView.setImageMatrix(matrix);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //	register	this	class	as	a	listener	for	the	accelerometer	sensor
        sensorService.registerListener(this,	sensorService.getDefaultSensor(
                Sensor.TYPE_MAGNETIC_FIELD),	sensorService.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorService.unregisterListener(this);
    }
}
