package com.swufestu.second;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreCount extends AppCompatActivity {
    private static final String TAG = "activity_score_count";
    int scoreA = 0;
    int scoreB = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_count);

    }
    public void click(View btn){
        TextView ShowA = findViewById(R.id.ScoreA);
        TextView ShowB = findViewById(R.id.ScoreB);
        Log.i(TAG, "click: ");
        if(btn.getId()==R.id.btn1){
            scoreA +=1;
        }
        else if(btn.getId()==R.id.btn2){
            scoreA +=2;
        }
        else if(btn.getId()==R.id.btn3){
            scoreA +=3;
        }
        else if(btn.getId()==R.id.btn4){
            scoreB +=1;
        }
        else if(btn.getId()==R.id.btn5){
            scoreB +=2;
        }
        else if(btn.getId()==R.id.btn6){
            scoreB +=3;
        }
        else if(btn.getId()==R.id.btn7){
            scoreA =0;
            scoreB =0;

        }
        ShowA.setText(String.valueOf(scoreA));
        ShowB.setText(String.valueOf(scoreB));
    }
}