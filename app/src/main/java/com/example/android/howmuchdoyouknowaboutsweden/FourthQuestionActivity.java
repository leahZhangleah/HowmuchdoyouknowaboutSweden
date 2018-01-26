package com.example.android.howmuchdoyouknowaboutsweden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FourthQuestionActivity extends AppCompatActivity {
    TextView name;
    String nameGreeting;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    String fourthQuestionAnswer;
    int correctAnswerNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_question);
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

    //check which radiobutton is clicked
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioButton_12:
                if (checked){
                    radioButton1 = (RadioButton) findViewById(R.id.radioButton_12);
                    fourthQuestionAnswer = radioButton1.getText().toString();

                }
                break;
            case R.id.radioButton_18:
                if (checked){
                    radioButton2 = (RadioButton) findViewById(R.id.radioButton_18);
                    fourthQuestionAnswer = radioButton2.getText().toString();
                }
                break;
            case R.id.radioButton_23:
                if (checked){
                    radioButton3 = (RadioButton) findViewById(R.id.radioButton_23);
                    fourthQuestionAnswer = radioButton3.getText().toString();
                }
                break;
            default:
                Toast.makeText(this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();

        }

    }

    //check if the question is answered and go to next activity
    public void submitAnswer(View v){
        if (fourthQuestionAnswer == null){
            Toast.makeText(this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();
        }else {
            checkAnswer();
            Intent intent = new Intent(this, CheckingAnswerActivity.class);
            intent.putExtra("UserName", nameGreeting);
            intent.putExtra("Answer",correctAnswerNumber);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    //check if the answer is correct
    public void checkAnswer(){
        if (fourthQuestionAnswer == getResources().getString(R.string.fourth_question_choice_3)){
            correctAnswerNumber += 1;
        }
    }

}
