package com.princekumar.gradlebigbuild;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.ExecutionException;

@SuppressWarnings("deprecation")
public class JokeFetchTaskTest extends AndroidTestCase {

    private static final String TAG = JokeFetchTaskTest.class.getSimpleName();

    public void testFetchesNonEmptyString() {
        JokeFetchTask fetchJokeTask = new JokeFetchTask();
        fetchJokeTask.execute();
        try {
            String joke = fetchJokeTask.get();
            Log.d(TAG, "Joke text: " + joke);
            assertNotNull(joke);
            assertTrue(joke.length() > 0);
        } catch (InterruptedException | ExecutionException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

}
