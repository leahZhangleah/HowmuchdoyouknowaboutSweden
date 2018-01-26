package com.example.android.howmuchdoyouknowaboutsweden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SecondQuestionActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    TextView name;
    String nameGreeting;
    int correctAnswerNumber;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    boolean choice1;
    boolean choice2;
    boolean choice3;
    boolean choice4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);
        name = (TextView) findViewById(R.id.name_greeting);

        //get intent from last activity and extrea info
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                nameGreeting = null;
            } else {
                nameGreeting = extras.getString("UserName");
                correctAnswerNumber = (int) extras.getInt("Answer");
                name.setText(nameGreeting);
            }
        } else {
            nameGreeting= (String) savedInstanceState.getSerializable("UserName");
            correctAnswerNumber = (int) savedInstanceState.getSerializable("Answer");
            name.setText(nameGreeting);
        }

        //check if any checkbox has been checked
        checkBox1 = (CheckBox) findViewById(R.id.check_box_skype);
        checkBox1.setOnCheckedChangeListener(this);

        checkBox2 = (CheckBox) findViewById(R.id.check_box_bosch);
        checkBox2.setOnCheckedChangeListener(this);

        checkBox3 = (CheckBox) findViewById(R.id.check_box_ericsson);
        checkBox3.setOnCheckedChangeListener(this);

        checkBox4 = (CheckBox) findViewById(R.id.check_box_hm);
        checkBox4.setOnCheckedChangeListener(this);
    }

    //check if user has answered the question and go to next activity
    public void nextQuestion(View v){
        if (choice1 == false && choice2 == false && choice3 == false && choice4 == false){
            Toast.makeText(this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();
        }else {
            checkAnswer();
            Intent intent = new Intent(this, ThirdQuestionActivity.class);
            intent.putExtra("UserName", nameGreeting);
            intent.putExtra("Answer",correctAnswerNumber);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        }

    }


    public void checkAnswer(){
        if (choice1 == true && choice2 == false && choice3 == true && choice4 == true ){
            correctAnswerNumber += 1;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.check_box_skype:
                choice1 = b;
                break;
            case R.id.check_box_bosch:
                choice2 = b;
                break;
            case R.id.check_box_ericsson:
                choice3 = b;
                break;
            case R.id.check_box_hm:
                choice4 = b;
                break;
        }

    }
}
