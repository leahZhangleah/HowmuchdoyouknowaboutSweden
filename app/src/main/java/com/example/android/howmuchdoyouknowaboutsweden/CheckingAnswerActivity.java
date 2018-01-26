package com.example.android.howmuchdoyouknowaboutsweden;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckingAnswerActivity extends AppCompatActivity {
    TextView name;
    String nameGreeting;
    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tv;
    ArrayList<String> answer = new ArrayList<String>();
    int correctAnswerNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_answer);

        name = (TextView) findViewById(R.id.name_greeting);

        //get intent from last activity and extrea info
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                nameGreeting = null;
            } else {
                nameGreeting = extras.getString("UserName");
                name.setText(nameGreeting);
                correctAnswerNumber = (int) extras.getInt("Answer");
            }
        }else {
            nameGreeting = (String)savedInstanceState.getSerializable("UserName");
            name.setText(nameGreeting);
            correctAnswerNumber = (int) savedInstanceState.getSerializable("Answer");

        }

        tv = (TextView) findViewById(R.id.final_score);

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.progress_circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar);
        //mProgress.setProgress(0);   // Main Progress
        //mProgress.setSecondaryProgress(100); // Secondary Progress
        //mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

        //progress bar animation
        ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(1000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        //not quite sure what this part is for, just copy online
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus <= (correctAnswerNumber/4 * 100)) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            tv.setText(correctAnswerNumber+ "/4");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(8); //thread will take approx 1.5 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
