package com.example.ihuichal.proyectoandroid;


import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double lat;
    double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

        ///metodo para que al presionar boton, se envien datos al servidor
     public void sendRegister(View v) {
        //compruebo permiso
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        //creo instancia a la clase posicion
        Position position = new Position(getApplicationContext());
        //llamaos al metodo localizacion y lo iniciamos en la clase position
        Location location = position.getLocation();
        if(location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            //obtenemos los valores desde la clase Position
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            Toast.makeText(getApplicationContext(), "Latitud: " + lat + " \n Longitud: " + lon, Toast.LENGTH_SHORT).show();
        }
        new HttpSend(getApplicationContext(),lat,lon).execute();
     }

}
