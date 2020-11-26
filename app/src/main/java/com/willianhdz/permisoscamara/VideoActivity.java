package com.willianhdz.permisoscamara;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView vVideo;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        vVideo = findViewById(R.id.vVideo);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) uri = Uri.parse(bundle.getString("VIDEO"));

        vVideo.setVideoURI(uri);
        vVideo.start();
    }
}