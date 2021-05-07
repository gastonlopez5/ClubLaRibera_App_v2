package com.example.clublaribera_app_v2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clublaribera_app_v2.ui.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long DELAYED_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);  //Iniciamos la nueva Activity
                finish();   //Finalizamos la Activity actual
            }
        }, DELAYED_TIME);   //Como segundo parámetro le pasamos el tiempo de espera que tendrá antes
        // de que se ejecuté el bloque de código dentro del método run()
    }
}