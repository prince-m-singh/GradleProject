package com.princekumar.gradlebigbuild;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.princekumar.jokesdisplay.ViewJokeActivity;
import com.udacity.gradle.builditbigger.R;

import static com.princekumar.jokelib.JokesMyClass.getRandomJoke;


public class MainActivity extends AppCompatActivity implements ClickResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
/*
        Toast.makeText(this, getRandomJoke(), Toast.LENGTH_SHORT).show();
*/


        new FetchJokeTask(this).execute(new Pair<Context, String>(this, "Prince"));

    }

    @Override
    public void onGetSuccess(String data) {
        Log.e(getClass().getName(),data);
        Intent viewJokeIntent = new Intent(MainActivity.this, ViewJokeActivity.class);
        viewJokeIntent.putExtra(ViewJokeActivity.INTENT_EXTRA_JOKE, data);
        MainActivity.this.startActivity(viewJokeIntent);
    }

    @Override
    public void onError(String errorCode) {

    }




}

interface ClickResponse {
    public void onGetSuccess(String data);
    public void onError(String errorCode);
}

