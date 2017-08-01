package com.princekumar.jokesdisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewJokeActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_JOKE = "intent:extra:joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_joke);

        String joke = getIntent().getStringExtra(INTENT_EXTRA_JOKE);
        TextView jokeView = (TextView) findViewById(R.id.joke);
        if (jokeView != null) {
            jokeView.setText(joke);
        }
    }

}
