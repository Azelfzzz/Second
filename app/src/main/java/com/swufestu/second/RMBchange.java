package com.swufestu.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RMBchange extends AppCompatActivity {
    private static final String TAG = "activity_rmbchange";
    float result1=0;
    float dollar_rate = 0.1547f;
    float euro_rate = 0.132f;
    float jpy_rate = 17.1234f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmbchange);
        Intent intent = getIntent();
        dollar_rate = intent.getFloatExtra("dollar_rate_c",0.1547f);
        euro_rate = intent.getFloatExtra("euro_rate_c",0.132f);
        jpy_rate = intent.getFloatExtra("jpn_rate_c",17.1234f);
    }
    public void DollarClick(View btn){

        Log.i(TAG, "click: ");
        TextView result = findViewById(R.id.ResultofChange);
        EditText input = findViewById(R.id.inputRMB);
        String inputRMB1 = input.getText().toString();
        //Double inputRMB1 = Intent.getFloatExtra("first111",0.0f);
        if(inputRMB1.length()>0){
            float inputRMB =Float.parseFloat(inputRMB1);
            if(btn.getId()==R.id.dollar){
                result1 = inputRMB * dollar_rate;
            }
            else if(btn.getId()==R.id.euro){
                result1 = inputRMB * euro_rate;
            }
            else if(btn.getId()==R.id.jpn){
                result1 = inputRMB * jpy_rate;
            }
            result.setText(String.valueOf(result1));
        }
        else{
            Toast.makeText(this,"请输入金额后转换",Toast.LENGTH_SHORT).show();
            result.setText(R.string.result);
        }

    }
    public void config(View btn){
        Intent config = new Intent(this,changerate.class);
        config.putExtra("dollar_rate",dollar_rate);
        config.putExtra("euro_rate",euro_rate);
        config.putExtra("jpn_rate",jpy_rate);
        Log.i(TAG,"openOne:dollarRate="+dollar_rate);
        Log.i(TAG,"openOne:eurorate="+euro_rate);
        Log.i(TAG,"openOne:jpyrate="+jpy_rate);
        startActivity(config);
    }


}