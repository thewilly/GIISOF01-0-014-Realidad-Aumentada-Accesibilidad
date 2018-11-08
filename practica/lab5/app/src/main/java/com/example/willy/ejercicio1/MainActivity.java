package com.example.willy.ejercicio1;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.drum_audio);

        Button btnPlay = (Button) this.findViewById(R.id.button);
        Button btnStop = (Button) this.findViewById(R.id.button2);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Log.d("My debug","Media Player playing");
                }
            }
        });

            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        Log.d("My debug", "Media Player stopping");
                        getReady();
                    }
                }
            });
    }

    private void getReady()
    {
        try {
            mediaPlayer.prepare();
            Log.d("My debug", "Media Player prepared");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
