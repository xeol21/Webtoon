package com.example.webtoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;


public class MainActivity extends AppCompatActivity {
    private static final long DELAY_BEFORE_NAVIGATION = 2000; // Milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,Login.class));
               finish();
            }
        },DELAY_BEFORE_NAVIGATION);
    }
}