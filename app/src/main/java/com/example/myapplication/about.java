package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void insta(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com/nafiddinovjonibek"));
        startActivity(intent);
    }

    public void tg(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://t.me/man_nestam"));
        startActivity(intent);
    }

    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+998914444818"));
        startActivity(intent);
    }
}