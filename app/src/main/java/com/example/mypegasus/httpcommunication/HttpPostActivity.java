package com.example.mypegasus.httpcommunication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_post);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, Void>() {
                    @Override
                    protected Void doInBackground(String... params) {
                        try {
                            URL url = new URL(params[0]);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//                            connection.setDoInput(true);//默认开启
                            System.out.println(connection.getDoOutput());// false
                            connection.setDoOutput(false);
                            System.out.println(connection.getDoOutput());
                            connection.setRequestMethod("POST");

                            //keyfrom=test1027HttpGet&key=1227176808&type=data&doctype=json&version=1.1&q=good
                            OutputStream os = connection.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("keyfrom=test1027HttpGet&key=1227176808&type=data&doctype=json&version=1.1&q=good&name=testpost");
                            System.out.println(connection.getDoOutput());
                            bw.flush();
//                            bw.close();

                            InputStream is = connection.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                            BufferedReader br = new BufferedReader(isr);
                            String line;
                            StringBuilder builder = new StringBuilder();
                            while ((line = br.readLine()) != null) {
                                System.out.println(line);
                            }

                            System.out.println(connection.getDoOutput());
                            br.close();
                            isr.close();
                            is.close();

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
//                }.execute("http://fanyi.youdao.com/openapi.do");
//                }.execute("http://10.0.2.2:8080/Test20151029/Do");// android模拟器用10.0.2.2
                }.execute("http://10.0.3.2:8080/Test20151029/Do");// genymotion用10.0.3.2
            }
        });
    }
}
