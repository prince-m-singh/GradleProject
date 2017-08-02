package com.princekumar.gradlebigbuild;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.princekumar.jokeapp.backend.myApi.MyApi;

import java.io.IOException;


class FetchJokeTask extends AsyncTask<Pair<Context, String>, Void, String>   {
    private static MyApi myApiService = null;
    private Context context;
    ClickResponse clickResponse;
    String data="0";

    public FetchJokeTask(ClickResponse clickResponse){
        this.clickResponse=clickResponse;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.7:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            //data= myApiService.sayHi(name).execute().getData();
            data= myApiService.getRandomJoke().execute().getText();
            Log.e(getClass().getName(),data);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();

        }
        Log.e(getClass().getName(),data);
        return data;

    }

    @Override
    public void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        clickResponse.onGetSuccess(result);
    }

}
