package com.example.fyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ShowImageActivity extends AppCompatActivity {
ImageView Image;
String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        Image = findViewById(R.id.showImg);
        URL = getIntent().getStringExtra("URL");
        Picasso.get().load(URL).into(Image);

    }
}
