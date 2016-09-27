package com.example.kaushalmandayam.kaushalmandayamcodingtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Kaushal.Mandayam on 9/27/2016.
 */
public class SplashActivity extends Activity {
    private final int SPLASH_DURATION = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DURATION);
    }

}
