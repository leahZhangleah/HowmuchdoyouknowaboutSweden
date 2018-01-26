package com.example.android.howmuchdoyouknowaboutsweden;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void startQuiz(View v){
        userName = (EditText) findViewById(R.id.user_name);
        if (userName.getText().toString().matches("")){
            Toast.makeText(this,R.string.name_required,Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this,FirstQuestionActivity.class);
            intent.putExtra("UserName",userName.getText().toString());
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }

    }


}
