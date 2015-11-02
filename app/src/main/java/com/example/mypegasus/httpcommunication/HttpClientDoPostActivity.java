package com.example.mypegasus.httpcommunication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientDoPostActivity extends AppCompatActivity {

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
                readNet("http://10.0.3.2:8080/Test20151029/Do", editText.getText().toString());
            }
        });

    }

    private void readNet(String url, String param) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String urlString = params[0];
//                HttpGet get = new HttpGet(urlString);
                HttpPost post = new HttpPost(urlString);
                try {
                    /*StringEntity entity = new StringEntity(params[1]);
                    post.setEntity(entity);*/
                    List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
                    list.add(new BasicNameValuePair("name", params[1]));
                    post.setEntity(new UrlEncodedFormEntity(list));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    HttpResponse response = client.execute(post);

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
        }.execute(url, param);
    }
}
