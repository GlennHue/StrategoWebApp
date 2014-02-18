package com.example.Android;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends Activity {
    private static final String URL = "http://10.0.2.2:8080/test";
    public final static String EXTRA_USERNAME = "com.example.Android.USERNAME";
    private boolean isVerified = false;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void checkCredentials(View view) throws ExecutionException, InterruptedException {
       //wait until the asynctask has finished its doInBackground method.
        Object result = new sendUser().execute().get();
        System.out.println(isVerified);
        if(isVerified){
            Intent intent = new Intent(this,LobbyActivity.class);
            EditText editText = (EditText)findViewById(R.id.username);
            String usernameParam = editText.getText().toString();
            intent.putExtra(EXTRA_USERNAME,usernameParam);
            startActivity(intent);

        }


    }
    private class sendUser extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            String username = ((EditText)findViewById(R.id.username)).getText().toString();
            String password= ((EditText)findViewById(R.id.password)).getText().toString();
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL);

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", username));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                sb.append(reader.readLine());
                JSONObject data = new JSONObject(sb.toString());
                isVerified = data.getBoolean("isVerified");


            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
        }


    }
}
