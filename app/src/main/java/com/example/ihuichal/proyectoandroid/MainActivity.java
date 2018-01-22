package com.example.ihuichal.proyectoandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {


    String Json  ="{\n" +
            "        \"id\": 12{\n" +
            "        \"date\": 21/01/2018,\n" +
            "        \"description\": \"5a620ea6312ff48c80e0ce66\",\n" +
            "    }\n" +
            "}";
    String urlParameters  = "id=12&date=21/01/2018&description=23";


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
            byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int postDataLength = postData.length;

            try {
                URL url = new URL("http://192.168.1.150:8090/api/register");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Host", "asdf");
                httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength ));

                httpURLConnection.connect();




                DataOutputStream Salida = new DataOutputStream(httpURLConnection.getOutputStream());
                //Salida.writeBytes(Json);
                Salida.write(postData);
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
