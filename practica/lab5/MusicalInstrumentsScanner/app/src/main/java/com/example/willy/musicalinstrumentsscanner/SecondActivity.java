package com.example.willy.musicalinstrumentsscanner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class SecondActivity extends AppCompatActivity {

    private Button scanButton;
    private TextView scannedText;

    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Getting the scan buton reference.
        scanButton = findViewById(R.id.scanButton);

        // setting the click listener for the scanbutton.
        scanButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN"); // Loagind the scan intention.
                intent.setPackage("com.google.zxing.client.android"); // Loading the package.
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // Setting the scan mode to QR
                startActivityForResult(intent, 0); // Setting the acclivity fot the expected result.
            }
        });

        // For video playing...
        video = findViewById(R.id.videoView);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String scanContents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan.
                Log.i(">>>",scanContents);
                Log.i(">>>",format);
                handleScan(scanContents);
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel Not implemented.
            }
        }
    }

    private void handleScan(String contents) {
        int raw = matchVideo(contents);
        if (raw > 0) {
            //scannedText.setText("Musical instrument found: " + contents);
            Uri uri = Uri.parse("android.resource://com.example.willy.musicalinstrumentsscanner/" + raw);
            video.setVideoURI(uri);
            video.requestFocus();
            video.start();
        }
    }

    private int matchVideo(String contents) {
        switch (contents) {
            case "Bass":
                return R.raw.bass_video;

            case "Drum":
                return R.raw.drum_video;
            case "Flute":
                return R.raw.flute_video;
            case "Guitar":
                return R.raw.guitar_video;
            case "Violin":
                return R.raw.violin_video;
            default:
                return 0;
        }
    }
}
