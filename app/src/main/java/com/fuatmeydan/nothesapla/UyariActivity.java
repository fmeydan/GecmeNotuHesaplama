package com.fuatmeydan.nothesapla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class UyariActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uyari);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.getSupportActionBar().hide();
        Button btndevam=findViewById(R.id.angry_btn);
        btndevam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentLauncher().start();
            }
        });

    }
    private class IntentLauncher extends Thread {
        @Override
        public void run() {

            Intent intent = new Intent(UyariActivity.this, GirisActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
