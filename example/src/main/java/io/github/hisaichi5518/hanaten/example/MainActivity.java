package io.github.hisaichi5518.hanaten.example;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import io.github.hisaichi5518.hanaten.Hanaten;
import io.github.hisaichi5518.hanaten.Splitter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView greetingView = (TextView) findViewById(R.id.activity_main__greeting);

        String greeting = new Splitter<String>(this, "MainActivity_Greeting")
                .add(20, "こんにちは!")
                .add(20, "Hello!")
                .add(20, "你好!")
                .add(20, "Bonjour!")
                .add(20, "Ciao!")
                .split();

        greetingView.setText(greeting);

        new Hanaten(this, "MainActivity_GreetingTextColor")
                .add(50, new Runnable() {
                    @Override
                    public void run() {
                        setGreenToBackgroundColor(greetingView);
                    }
                })
                .add(50, new Runnable() {
                    @Override
                    public void run() {
                        setBlueToBackgroundColor(greetingView);
                    }
                })
                .start();
    }

    private void setGreenToBackgroundColor(View view) {
        view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
    }

    private void setBlueToBackgroundColor(View view) {
        view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
    }
}
