package com.example.k_files.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.k_files.R;

public class DisplayImage extends AppCompatActivity implements DisplayImageItf {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        imageView = findViewById(R.id.image_image);
        Intent intent = getIntent();
        String path = intent.getStringExtra("image");
        displayImage(path);
    }

    @Override
    public void displayImage(String path) {
        imageView.setImageBitmap(BitmapFactory.decodeFile(path));
    }
}