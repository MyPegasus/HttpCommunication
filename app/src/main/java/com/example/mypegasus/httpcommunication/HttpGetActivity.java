package com.example.mypegasus.httpcommunication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpGetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_get);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, Void>() {
                    @Override
                    protected Void doInBackground(String... params) {
                        try {
                            URL url = new URL(params[0]);
                            URLConnection connection = url.openConnection();
                            InputStream is = connection.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                            BufferedReader br = new BufferedReader(isr);
                            String line;
                            StringBuilder builder = new StringBuilder();
                            while ((line = br.readLine()) != null) {
                                System.out.println(line);
                            }

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
//                }.execute("http://fanyi.youdao.com/openapi.do?keyfrom=test1027HttpGet&key=1227176808&type=data&doctype=json&version=1.1&q=good");    }
                }.execute("http://10.0.3.2:8080/Test20151029/Do");
            }
        });
    }
}
