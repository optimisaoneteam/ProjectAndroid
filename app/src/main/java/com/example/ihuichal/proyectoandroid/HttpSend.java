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
    Double latitud, longitud;
    String Mac = MacDevice.getMacAddress(), urlParameters;;


    // este metodo corre durante la ejecucion del hilo
    public HttpSend(Context context,double lat,double lon){
        super();
        this.context=context;
        this.latitud = lat;
        this.longitud = lon;
        urlParameters  = "mac="+Mac+"&lat="+String.valueOf(latitud)+"&lon="+String.valueOf(longitud);
    }


        @Override
        protected String doInBackground(Void... voids) {
            //conector https
            HttpURLConnection httpURLConnection = null;
            //estructura de detos para leer el response
            StringBuilder sb = new StringBuilder();
            //postdata aqui se prepara para ser enviado
            byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
            try {
                //se esesifica la url y se guarda en la clase url
                URL url = new URL("http://10.10.7.105:8090/api/register");
                // se inserta la url en el  conector
                httpURLConnection = (HttpURLConnection) url.openConnection();
                // se setean los headres del http
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Host", "asdf");
                httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length ));
                // y se conecta
                httpURLConnection.connect();



                // se  crea la salida  de la data por stream y  ejecuta los headers
                DataOutputStream Salida = new DataOutputStream(httpURLConnection.getOutputStream());
                //y escribe el body
                Salida.write(postData);
                Salida.flush();
                // cerramos porque ya no vamo a ocupar
                Salida.close();

                // con el conector  capturamos el response code
                int result= httpURLConnection.getResponseCode();
                /// se compara si es 200
                if (result == httpURLConnection.HTTP_OK){
                    // "Resibimos el response y lo guardamos en sb
                    BufferedReader bufferreader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
                    String linea= null;
                    while((linea = bufferreader.readLine())!= null){
                        sb.append(linea+"\n");
                    }
                    // cerraos el Buffereader porque no se ocupa mas y se recomienda
                    bufferreader.close();

                }
                else{
                    // mensaja de error del respone
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
            //System.out.println(s);
           Toast.makeText(context,"Enviado al Sevidor",Toast.LENGTH_SHORT).show();

        }



}
