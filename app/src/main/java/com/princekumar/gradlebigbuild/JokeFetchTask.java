package com.princekumar.gradlebigbuild;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.princekumar.jokeapp.backend.myApi.MyApi;

import java.io.IOException;


class JokeFetchTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;

    @Override
    protected final String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://192.168.1.7:8080/_ah/api/");
            myApiService = builder.build();
        }
        try {
            return myApiService.getRandomJoke().execute().getText();
        } catch (IOException e) {
            Log.e(JokeFetchTask.class.getSimpleName(), e.getMessage());
            return null;
        }
    }
}
