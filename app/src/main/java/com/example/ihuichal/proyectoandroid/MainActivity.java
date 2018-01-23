package com.example.ihuichal.proyectoandroid;


import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String Mac = MacDevice.getMacAddress();
        Toast.makeText(getBaseContext(), Mac, Toast.LENGTH_LONG).show();

    }

        ///metodo para el boton  de envio que arranca el metodo envia
     public void sendRegister(View v) {
        //compruebo permiso
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        //creo instancia a la clase posicion
        Position position = new Position(getApplicationContext());

        //llamaos al metodo localizacion y lo iniciamos en la clase position
        Location location = position.getLocation();

        if(location != null) {
            //obtenemos los valores desde la clase Position
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            Toast.makeText(getApplicationContext(), "Latitud: " + lat + " \n Longitud: " + lon, Toast.LENGTH_SHORT).show();
        }

        new HttpSend(getApplicationContext()).execute();


    }

}
