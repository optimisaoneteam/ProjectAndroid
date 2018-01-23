package com.example.ihuichal.proyectoandroid;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by foyarzun on 23-01-18.
 */

public class HttpSend extends AsyncTask<Void,String,String> {
    Context context;

    public HttpSend(Context context){

        super();
        this.context=context;
    }

    String urlParameters  = "id=12&date=23/01/2018&description=23";
        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            StringBuilder sb = new StringBuilder();
            byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int postDataLength = postData.length;

            try {
                URL url = new URL("http://10.10.7.127:8090/api/register");
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
            return ("  ");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println(s);
           Toast.makeText(context,"Enviado al Sevidor",Toast.LENGTH_SHORT).show();

        }



}
