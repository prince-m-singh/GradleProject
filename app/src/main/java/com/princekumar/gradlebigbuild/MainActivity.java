package com.princekumar.gradlebigbuild;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.princekumar.jokesdisplay.ViewJokeActivity;
import com.udacity.gradle.builditbigger.R;


public class MainActivity extends AppCompatActivity {

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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new FetchAndDisplayJokeTask(this).execute();
    }

    private static class FetchAndDisplayJokeTask extends FetchJokeTask {
        private final Context mContext;

        public FetchAndDisplayJokeTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPostExecute(@Nullable String jokeText) {
            if (jokeText == null) {
                Toast.makeText(mContext, "Error fetching joke", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent viewJokeIntent = new Intent(mContext, ViewJokeActivity.class);
            viewJokeIntent.putExtra(ViewJokeActivity.INTENT_EXTRA_JOKE, jokeText);
            mContext.startActivity(viewJokeIntent);
        }


    }
}
