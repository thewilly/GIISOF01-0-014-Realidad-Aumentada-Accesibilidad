package com.example.willy.videoplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;

    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.videoView);

        String pathVideo = "android.resource://com.example.willy.videoplayer/" + R.raw.bass_video;
        Uri uri = Uri.parse(pathVideo);
        videoView.setVideoURI(uri);

        mediaController = new MediaController(this);

        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
    }
}
