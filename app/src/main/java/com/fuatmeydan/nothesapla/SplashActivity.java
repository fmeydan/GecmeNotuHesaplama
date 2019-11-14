package com.fuatmeydan.nothesapla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.getSupportActionBar().hide();
        new IntentLauncher().start();

    }

    private class IntentLauncher extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }
            Intent intent = new Intent(SplashActivity.this, UyariActivity.class);
            startActivity(intent);
            finish(); //Bu activity kapanır
        }
    }

}
