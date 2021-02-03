package com.example.eggtimerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;

    TextView textView;

    Button button;

    Boolean counterIsActive=false;

    CountDownTimer countDownTimer;

    public void resetTimer(){
        textView.setText("00:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Go!");
        counterIsActive=false;
    }

    public void buttonClick(View view){

        if(counterIsActive){
            resetTimer();
        }else {

            counterIsActive = true;

            seekBar.setEnabled(false);

            button.setText("Stop!");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);

                    mediaPlayer.start();

                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft){
        int minutes=secondsLeft/60;
        int seconds=secondsLeft-(minutes*60);

        String secondsString=Integer.toString(seconds);

        String minutesString=Integer.toString(minutes);

        if(secondsString.equals("0")){
            secondsString="00";
        }

        if(secondsString.length()<=1){
            secondsString="0"+secondsString;
        }

        if(minutesString.length()<=1){
            minutesString="0"+minutesString;
        }

        textView.setText(minutesString+":"+secondsString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.timeProgress);

        textView=findViewById(R.id.timerText);

        button=findViewById(R.id.goButton);

        seekBar.setMax(600);

        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}