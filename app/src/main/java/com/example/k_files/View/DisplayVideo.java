package com.example.k_files.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.k_files.R;

public class DisplayVideo extends AppCompatActivity implements DisplayVideoItf {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video);
        videoView = findViewById(R.id.mVideo);
        Intent intent = getIntent();
        String path = intent.getStringExtra("video");
        displayVideo(path);
    }

    @Override
    public void displayVideo(String path) {
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();
    }
}