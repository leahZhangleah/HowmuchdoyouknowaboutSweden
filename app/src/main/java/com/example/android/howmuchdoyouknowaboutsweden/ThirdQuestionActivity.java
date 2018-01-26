package com.example.android.howmuchdoyouknowaboutsweden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdQuestionActivity extends AppCompatActivity {
    TextView name;
    String nameGreeting;
    String thirdQuestionAnswer;
    EditText thirdQuestionA;
    int correctAnswerNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);
        name = (TextView) findViewById(R.id.name_greeting);

        //get intent from last activity and extrea info
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                nameGreeting = null;
            } else {
                nameGreeting = extras.getString("UserName");
                name.setText(nameGreeting);
                correctAnswerNumber = (int) extras.getInt("Answer");
            }
        } else {
            nameGreeting= (String) savedInstanceState.getSerializable("UserName");
            name.setText(nameGreeting);
            correctAnswerNumber = (int) savedInstanceState.getSerializable("Answer");
        }
    }

    //check if user has answered the question and go to next activity
    public void nextQuestion(View v){
        thirdQuestionA = (EditText)findViewById(R.id.third_question_answer);
        thirdQuestionAnswer = thirdQuestionA.getText().toString();
        if (thirdQuestionAnswer.matches("")){
            Toast.makeText(this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isDigitsOnly(thirdQuestionAnswer)){
            if (thirdQuestionAnswer == "4"){
                correctAnswerNumber += 1;
            }
            Intent intent = new Intent(this, FourthQuestionActivity.class);
            intent.putExtra("UserName", nameGreeting);
            intent.putExtra("Answer", correctAnswerNumber);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else{
            Toast.makeText(this,R.string.third_question_digit_only,Toast.LENGTH_SHORT).show();
        }
    }

}
