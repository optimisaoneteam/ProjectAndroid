package com.example.ihuichal.proyectoandroid;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

public class Position implements LocationListener {

    Context context;
    public Position(Context context){
        super();
        this.context = context;
    }

    public Location getLocation(){
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(context,"Permiso no concedido",Toast.LENGTH_SHORT).show();
            Log.e("fist","error");
            return null;
        }
        try {
            LocationManager manager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
            boolean GPCenable = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (GPCenable) {
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
                Location loc = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                return loc;
                // Toast.makeText(context, "Avtivar GPS",Toast.LENGTH_LONG).show();
            } else {
                Log.e("sec", "error");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            return null;
        }


    @Override
    public void onLocationChanged(android.location.Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
