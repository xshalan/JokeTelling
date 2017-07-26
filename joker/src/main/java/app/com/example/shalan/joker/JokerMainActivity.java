package app.com.example.shalan.joker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokerMainActivity extends AppCompatActivity {
    String joke ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker_main);
        TextView joke_text = (TextView) findViewById(R.id.joke_textview);
        Intent intent = getIntent() ;

        if(intent.hasExtra("joke")){
             joke = intent.getStringExtra("joke");

        }else{
            joke = "Nothing to show :)" ;
        }
        joke_text.setText(joke);
    }
}
