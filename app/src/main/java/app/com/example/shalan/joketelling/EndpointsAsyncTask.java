package app.com.example.shalan.joketelling;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.noura.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by noura on 26/07/2017.
 */

public class EndpointsAsyncTask extends AsyncTask<Context,Void,String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://builditbigger-shalan.appspot.com/_ah/api/") ;

            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.tellJoke().execute().getData() ;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        //Intent intent = new Intent(context, JokerMainActivity.class);
        //intent.putExtra("joke",result);
        //context.startActivity(intent);
    }
}