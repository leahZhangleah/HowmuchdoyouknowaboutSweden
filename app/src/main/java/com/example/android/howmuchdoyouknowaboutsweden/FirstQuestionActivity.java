package com.example.android.howmuchdoyouknowaboutsweden;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirstQuestionActivity extends AppCompatActivity {
    TextView name;
    String nameGreeting;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    String firstQuestionAnswer;
    int correctAnswerNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_question);
        name = (TextView) findViewById(R.id.name_greeting);

        //get intent from last activity and extrea info
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                nameGreeting = null;
            } else {
                nameGreeting = extras.getString("UserName");
                name.setText(nameGreeting);
            }
        } else {
            nameGreeting= (String) savedInstanceState.getSerializable("UserName");
            name.setText(nameGreeting);
        }
    }

    //check if user has answered the question and go to next activity
    public void nextQuestion(View v){
        if (firstQuestionAnswer == null){
            Toast.makeText(this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();
        }else {
            checkAnswer();

            Intent intent = new Intent(this,SecondQuestionActivity.class);
            intent.putExtra("UserName",nameGreeting);
            intent.putExtra("Answer",correctAnswerNumber);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }

    }

    //check which radio button is clicked
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radio_button_carl:
                if (checked){
                    radioButton1 = (RadioButton) findViewById(R.id.radio_button_carl);
                    firstQuestionAnswer = radioButton1.getText().toString();

                }
                break;
            case R.id.radio_button_haakon:
                if (checked){
                    radioButton2 = (RadioButton) findViewById(R.id.radio_button_haakon);
                    firstQuestionAnswer = radioButton2.getText().toString();
                }
                break;
            case R.id.radio_button_wilhelm:
                if (checked){
                    radioButton3 = (RadioButton) findViewById(R.id.radio_button_wilhelm);
                    firstQuestionAnswer = radioButton3.getText().toString();
                }
                break;
            default:
                Toast.makeText(this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();

        }

    }

    //check if the answer is correct
    public void checkAnswer(){
        if (firstQuestionAnswer == getResources().getString(R.string.first_question_choice_1)){
            correctAnswerNumber += 1;
        }
    }




}
