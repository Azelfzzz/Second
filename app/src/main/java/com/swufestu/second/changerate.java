package com.swufestu.second;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class changerate extends AppCompatActivity {
    EditText dollar_rate;
    EditText euro_rate;
    EditText jny_rate;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changerate);
        Intent intent = getIntent();
        float dollar2 = intent.getFloatExtra("dollar_rate",0.0f);
        float euro2 = intent.getFloatExtra("euro_rate",0.0f);
        float jpn2 = intent.getFloatExtra("jpn_rate",0.0f);
        dollar_rate = (EditText) findViewById(R.id.change1);
        euro_rate = (EditText)findViewById(R.id.change2);
        jny_rate = (EditText)findViewById(R.id.change3);

        dollar_rate.setText(String.valueOf(dollar2));
        euro_rate.setText(String.valueOf(euro2));
        jny_rate.setText(String.valueOf(jpn2));
    }
    public void save(View btn){
        Intent save = new Intent(this,RMBchange.class);

        float rateccc1 =Float.parseFloat(dollar_rate.getText().toString());
        float rateccc2 =Float.parseFloat(euro_rate.getText().toString());
        float rateccc3 =Float.parseFloat(jny_rate.getText().toString());

        /*save.putExtra("dollar_rate_c",rateccc1);
        save.putExtra("euro_rate_c",rateccc2);
        save.putExtra("jpn_rate_c",rateccc3);*/

        Bundle bundle = new Bundle();
        bundle.putFloat("dollar_rate_c",rateccc1);
        bundle.putFloat("euro_rate_c",rateccc2);
        bundle.putFloat("jpn_rate_c",rateccc3);
        save.putExtras(bundle);
        //startActivity(save);
        setResult(2,save);
        //返回调用页面
        finish();
    }
}