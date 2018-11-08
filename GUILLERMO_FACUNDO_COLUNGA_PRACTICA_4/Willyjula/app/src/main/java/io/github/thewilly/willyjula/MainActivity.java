package io.github.thewilly.willyjula;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Compass compass;
    private ImageView compassImage;

    private float currentAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compassImage = findViewById(R.id.compass);
        setupCompass();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        compass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        compass.stop();
    }

    private void setupCompass() {
        compass = new Compass(this);
        Compass.CompassListener cl = getCompassListener();
        compass.setListener(cl);
    }

    private void adjustArrow(float newAngle) {
        Log.d(TAG, "will set rotation from " + currentAngle + " to "
                + newAngle);

        Animation an = new RotateAnimation(-currentAngle, -newAngle,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAngle = newAngle;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        compassImage.startAnimation(an);
    }

    private Compass.CompassListener getCompassListener() {
        return new Compass.CompassListener() {
            @Override
            public void onNewAngle(final float angle) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adjustArrow(angle);
                    }
                });
            }
        };
    }
}
