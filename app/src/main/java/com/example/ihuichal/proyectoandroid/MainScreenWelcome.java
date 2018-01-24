package com.example.ihuichal.proyectoandroid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainScreenWelcome extends AppCompatActivity {
    private  static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //en el manifest, asigne a esta actividad como inicio de la aplicacion...

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainScreenWelcome.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
