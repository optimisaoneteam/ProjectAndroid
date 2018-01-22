package com.example.ihuichal.proyectoandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    String Json  ="{\n" +
            "        \"id\": {\n" +
            "        \"date\": 21/01/2018,\n" +
            "        \"description\": \"5a620ea6312ff48c80e0ce66\",\n" +
            "    }\n" +
            "}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void envia (View v ){
        new Envia().execute();

    }

    public class Envia extends AsyncTask<Void, String, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL("http://127.0.0.1:8090/api/register");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setRequestProperty("Content-Type","Optimisa");
                httpURLConnection.setRequestProperty("Host", "asdf");
                httpURLConnection.connect();

                DataOutputStream Salida = new DataOutputStream(httpURLConnection.getOutputStream());
                Salida.writeBytes(Json);

                Salida.flush();
                Salida.close();

                int result= httpURLConnection.getResponseCode();
                if (result == httpURLConnection.HTTP_OK){
                    BufferedReader bufferreader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
                    String linea= null;
                    while((linea = bufferreader.readLine())!= null){
                        sb.append(linea+"\n");
                    }
                    bufferreader.close();

                }
                else{
                    System.out.print(httpURLConnection.getResponseMessage());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //httpURLConnection.disconnect();

            }
            return (Json);
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println(s);
            Toast.makeText(getBaseContext(),"Enviado al Sevidor",Toast.LENGTH_LONG).show();

        }
    }
}
