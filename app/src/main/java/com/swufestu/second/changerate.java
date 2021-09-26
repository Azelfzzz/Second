package com.swufestu.second;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class changerate extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changerate);
        Intent intent = getIntent();
        float dollar2 = intent.getFloatExtra("dollar_rate",0.0f);
        float euro2 = intent.getFloatExtra("euro_rate",0.0f);
        float jpn2 = intent.getFloatExtra("jpn_rate",0.0f);
        TextView rate1 = findViewById(R.id.rate1);
        rate1.setText(String.valueOf(dollar2));

        TextView rate2 = findViewById(R.id.rate2);
        rate2.setText(String.valueOf(euro2));

        TextView rate3 = findViewById(R.id.rate3);
        rate3.setText(String.valueOf(jpn2));
    }
    public void save(View btn){
        Intent save = new Intent(this,RMBchange.class);
        EditText ratec1,ratec2,ratec3;
        /*if(ratec1.length()>0){
            String ratecc1 = ratec1.getText().toString();
            float rateccc1 =Float.parseFloat(ratecc1);
            save.putExtra("dollar_rate_c",rateccc1);
        }
        else{
            save.putExtra("dollar_rate_c",0.1547f);
        }
        if(ratec2.length()>0){
            String ratecc2 = ratec2.getText().toString();
            float rateccc2 =Float.parseFloat(ratecc2);
            save.putExtra("euro_rate_c",rateccc2);
        }
        else{
            save.putExtra("euro_rate_c",0.132f);
        }
        if(ratec1.length()>0){
            String ratecc3 = ratec3.getText().toString();
            float rateccc3 =Float.parseFloat(ratecc3);
            save.putExtra("jpn_rate_c",rateccc3);
        }
        else{
            save.putExtra("jpn_rate_c",17.1234f);
        }*/
        ratec1 = findViewById(R.id.change1);
        String ratecc1 = ratec1.getText().toString();
        float rateccc1 =Float.parseFloat(ratecc1);

        ratec2 = findViewById(R.id.change2);
        String ratecc2 = ratec2.getText().toString();
        float rateccc2 =Float.parseFloat(ratecc2);

        ratec3 = findViewById(R.id.change3);
        String ratecc3 = ratec3.getText().toString();
        float rateccc3 =Float.parseFloat(ratecc3);

        save.putExtra("dollar_rate_c",rateccc1);
        save.putExtra("euro_rate_c",rateccc2);
        save.putExtra("jpn_rate_c",rateccc3);
        startActivity(save);
    }
}