package com.example.movies_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movies_app.R;

public class StartUpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        ImageView logo=findViewById(R.id.startuplogo);
        TextView lgotext=findViewById(R.id.startuplogo_text);
        logo.animate().rotation(360f).setDuration(2500);
        lgotext.animate().translationX(550f).setDuration(2500);
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(StartUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    StartUpActivity.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

    }
}