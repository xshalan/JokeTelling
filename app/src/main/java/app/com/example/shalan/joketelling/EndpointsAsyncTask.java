package app.com.example.shalan.joketelling;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.noura.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import app.com.example.shalan.joker.JokerMainActivity;
import app.com.example.shalan.joketelling.Utils.JokeListener;

/**
 * Created by noura on 26/07/2017.
 */

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private JokeListener jokeListener;

    public EndpointsAsyncTask() {
    }

    public EndpointsAsyncTask(JokeListener jokeListener) {
        this.jokeListener = jokeListener;
    }

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://builditbigger-shalan.appspot.com/_ah/api/");

            // end options for devappserver
            myApiService = builder.build();

        }

        context = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        if (result != null) {
            Intent intent = new Intent(context, JokerMainActivity.class);
            intent.putExtra("joke", result);
            Log.v("AsyncTask: ",result);
            context.startActivity(intent);
            if (jokeListener != null) {
                jokeListener.isCompleted(result);
            }
        } else {
            Toast.makeText(context, "Nothing found!", Toast.LENGTH_LONG).show();
        }

    }
}