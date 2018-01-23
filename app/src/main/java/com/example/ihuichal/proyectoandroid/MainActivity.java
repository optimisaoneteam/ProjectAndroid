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

        //---------LLAMADA A POSITION CLASS--------//
        Button GPCpocition = (Button)findViewById(R.id.buttonPosition);
        final TextView showLat = (TextView)findViewById(R.id.showText);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        GPCpocition.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //instanciar la clase Position
                Position position = new Position(getApplicationContext());
                Location location = position.getLocation();
                if(location != null){
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    Toast.makeText(getApplicationContext(), "Latitud: "+lat+" \n Longitud: "+lon, Toast.LENGTH_SHORT).show();
                    //showLat.setText(String.valueOf(lat+"\n "+lon));
                    //System.out.println(lat);
                    //System.out.println(lon);
                }


            }
        });
        //----------------------------------------//

    }

}
