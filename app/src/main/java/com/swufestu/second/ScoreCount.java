package com.swufestu.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    public void clickA(View btn){
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
        show();
    }
    public void clickB(View btn){
        Log.i(TAG, "click: ");
        if(btn.getId()==R.id.btn4){
            scoreB +=1;
        }
        else if(btn.getId()==R.id.btn5){
            scoreB +=2;
        }
        else if(btn.getId()==R.id.btn6){
            scoreB +=3;
        }
        show();
    }
    public void reclick(View btn){
        if(btn.getId()==R.id.btn7){
            scoreA =0;
            scoreB =0;
        }
        show();
    }
    public void show(){
        Log.i(TAG, "click: ");
        TextView ShowA = findViewById(R.id.ScoreA);
        TextView ShowB = findViewById(R.id.ScoreB);
        ShowA.setText(String.valueOf(scoreA));
        ShowB.setText(String.valueOf(scoreB));
    }
    public void open(View v){
        Intent first = new Intent(this,RMBchange.class);
        first.putExtra("first111",scoreA);
        startActivity(first);
    }
    @Override//保存
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scoreA",scoreA);
        outState.putInt("scoreB",scoreB);
    }

    @Override//还原
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreA = savedInstanceState.getInt("scoreA",0);
        scoreB = savedInstanceState.getInt("scoreB",0);
    }
}