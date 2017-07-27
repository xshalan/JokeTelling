package app.com.example.shalan.joketelling;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import app.com.example.shalan.joketelling.Utils.JokeListener;

import static org.junit.Assert.assertTrue;

/**
 * Created by noura on 27/07/2017.
 */

@RunWith(AndroidJUnit4.class)
public class EndPointAsyncTaskTest implements JokeListener {
    String Joke = null;
    Context mContext = InstrumentationRegistry.getTargetContext();
    final CountDownLatch latch = new CountDownLatch(1);


    @Test
    public void doInBackgroundTesting() throws InterruptedException {
        new EndpointsAsyncTask(this).execute(mContext);
        latch.await();
        assertTrue("Joke not found!" , Joke != null);
    }


    @Override
    public void isCompleted(String result) {
        latch.countDown();
        Joke = result ;

    }
}
