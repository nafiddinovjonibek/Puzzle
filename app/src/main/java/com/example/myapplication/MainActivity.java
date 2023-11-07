package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> sonlar = new ArrayList<>();
    int[] img2 = {R.drawable.img2_1,R.drawable.img2_2,R.drawable.img2_3,R.drawable.img2_4,R.drawable.img2_5,R.drawable.img2_6,R.drawable.img2_7,R.drawable.img2_8,R.drawable.img2_9,R.drawable.img2_10,R.drawable.img2_11,R.drawable.img2_12,R.drawable.img2_13,R.drawable.img2_14,R.drawable.img2_15};
    int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,R.drawable.img13,R.drawable.img14,R.drawable.img15};
    Button[][] buttons = new Button[4][4];

    int[] random_images;
    RelativeLayout relativeLayout;
    int emptyI = 3;
    int emptyJ = 3;

    int cuont = 0;
    TextView step, sekund;
    int winner=0;
    int firstclk;
    Timer timer;
    int time=600;
    int r_num;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        step = (TextView)findViewById(R.id.count_step);
        sekund = findViewById(R.id.sekund);
        firstclk = 0;
        timer = new Timer();
        shuffle_images();
        loadnumbers();
        loadButtons();
        loaddatabuttons();




    }
    public void loadnumbers(){
        for (int i=1; i<=15; i++){
            sonlar.add(i);
        }
        Collections.shuffle(sonlar);
        Log.d("asas", "loadnumbers: "+sonlar);
    }

    public void loadButtons(){
        relativeLayout = findViewById(R.id.relative1);
        for(int i=0; i<relativeLayout.getChildCount();i++){
            buttons[i/4][i%4] = (Button) relativeLayout.getChildAt(i);
        }
    }


    public void loaddatabuttons(){
        for(int i=0; i<relativeLayout.getChildCount()-1;i++) {
            // num buttonlarga berilgan yangi sonlar
            int num = sonlar.get(i);
            //shuffle qilingan raqamlarni buttonlarga berish
            buttons[i/4][i%4].setText(sonlar.get(i).toString());
            //buttonlar raqamiga mos ravishda rasm berish
            buttons[i/4][i%4].setBackgroundResource(random_images[num-1]);
        }
        buttons[3][3].setBackgroundResource(R.drawable.empty);




    }

    public String countTimer(int second){
        int sec = second%60;
        int hour = second/3600;
        int minute = (second-(hour*3600+sec))/60;

        String timeFormat = String.format("%02d:%02d:%02d",hour,minute,sec);
        return timeFormat;
    }


    public void clk(View view) {
        int pressI = Integer.parseInt(view.getTag().toString()) / 10;
        int pressJ = Integer.parseInt(view.getTag().toString()) % 10;
        if (buttons[pressI][pressJ] != buttons[emptyI][emptyJ]){

            String getPressedText = (String) buttons[pressI][pressJ].getText();
//        int getPressedResource = random_images[Integer.parseInt(getPressedText) - 1];
        int getPressedResource = R.drawable.shakl;
//        Toast.makeText(this, pressI+" "+pressJ, Toast.LENGTH_SHORT).show();
        if ((pressI - emptyI == 0 && Math.abs(pressJ - emptyJ) == 1) || (Math.abs(pressI - emptyI) == 1 && pressJ - emptyJ == 0)) {
            ///////////qachonki button bosilsa keyin timer ishga tushsin;
            firstclk++;
            Log.d("clk", String.valueOf(firstclk));
            timerStart();

            buttons[emptyI][emptyJ].setText(getPressedText);
            buttons[emptyI][emptyJ].setBackgroundResource(getPressedResource);

            buttons[pressI][pressJ].setText("");
            buttons[pressI][pressJ].setBackgroundResource(R.drawable.empty);

            emptyI = pressI;
            emptyJ = pressJ;
            cuont++;
            step.setText("Your step: " + cuont);

            if (emptyI == 3 && emptyJ == 3) {
                winner = 0;
                for (int i = 0; i < 15; i++) {
                    if (buttons[i / 4][i % 4].getText().toString().equals((i + 1) + "")) {
                        winner++;
                    }
                }
                //---------------------------Check Win-------------------------------------------
                if (winner == 15) {

//                    Toast.makeText(this, "Winner", Toast.LENGTH_SHORT).show();
                    timer.cancel();
                    String vaqt = sekund.getText().toString();
                    String qadam = step.getText().toString();
                    String imgnum = String.valueOf(r_num);
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("vaqti", vaqt);
                    intent.putExtra("qadami", qadam);
                    intent.putExtra("rasmi", imgnum);
                    res(view);
                    startActivity(intent);
                }
            }

        }
        }
    }

    public void stop_game(View view) {
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
         AlertDialog alertDialog = builder.create();
         alertDialog.show();
    }

    public void timerStart(){
        if (firstclk==1){
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (time>0){
                        time--;
                    }
                    else {
                        timer.cancel();
                        Intent intent2 = new Intent(MainActivity.this, MainActivity3.class);
                        startActivity(intent2);
                        finish();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sekund.setText(countTimer(time));
                        }
                    });
                }
            },1000,1000);
        }

    }

    public void shuffle_images(){
        Random ran = new Random();
        r_num = ran.nextInt(2);


        if(r_num == 0){
            random_images = images;
        }
        if (r_num == 1){
            random_images = img2;
        }

        Log.d("rimages", "shuffle_images: "+random_images[3]);
    }


    //---------------- RESTART GAME -------------------//
    public void res(View v){
        firstclk=0;
        Log.d("res_clk", String.valueOf(firstclk));
        time = 600;
        timer.cancel();
        cuont = 0;
        sekund.setText("00:10:00");
        step.setText("Your step: "+ cuont);
        emptyI = 3;
        emptyJ = 3;
        shuffle_images();
        Collections.shuffle(sonlar);
        loaddatabuttons();
    }


    //---------------- RESTART GAME -------------------//
    public void about(View view) {
        res(view);
        Intent about = new Intent(MainActivity.this, about.class);
        startActivity(about);
    }


}