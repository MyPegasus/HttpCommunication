package com.example.mypegasus.httpcommunication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientDoGetActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    HttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client_do_get);

        client = new DefaultHttpClient();

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                readNet("http://10.0.2.2:8080/Test20151029/Do?name=acely");
                readNet("http://10.0.3.2:8080/Test20151029/Do?name=" + editText.getText());
            }
        });

    }

    private void readNet(String url) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String urlString = params[0];
                HttpGet get = new HttpGet(urlString);
                try {
                    HttpResponse response = client.execute(get);
                    String value = EntityUtils.toString(response.getEntity());

//                    System.out.println(value);

                    return value;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                textView.setText(s);
            }
        }.execute(url);
    }
}
