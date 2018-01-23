package com.example.ihuichal.proyectoandroid;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String Mac = MacDevice.getMacAddress();
        Toast.makeText(getBaseContext(), Mac, Toast.LENGTH_LONG).show();
    }

    ///metodo para el boton  de envio que arranca el metodo envia
    public void envia(View v) {
        new HttpSend(getApplicationContext()).execute();
    }
}
