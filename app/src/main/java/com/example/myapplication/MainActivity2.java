package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView time,step;
    ImageView imageView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        time = findViewById(R.id.time);
        step = findViewById(R.id.count);
        imageView = findViewById(R.id.winimg);
        Intent intent = getIntent();
        String vaqt = intent.getStringExtra("vaqti");
        String qadam = intent.getStringExtra("qadami");
        String img_num = intent.getStringExtra("rasmi");

        time.setText("Your time is: "+vaqt);
        step.setText("Your steps is: "+qadam);

        if (img_num.equals("0")){
            imageView.setImageResource(R.drawable.org1);

        }
        if (img_num.equals("1")){
            imageView.setImageResource(R.drawable.org2);

        }
    }
}