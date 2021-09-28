package com.swufestu.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RMBchange extends AppCompatActivity {
    private static final String TAG = "activity_rmbchange";
    float result1=0;
    float dollar_rate;
    float euro_rate;
    float jpy_rate;
    TextView result;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmbchange);
        dollar_rate = 0.1547f;
        euro_rate = 0.132f;
        jpy_rate = 17.1234f;
        result = findViewById(R.id.ResultofChange);
        input = findViewById(R.id.inputRMB);

    }
    public void DollarClick(View btn){

        Log.i(TAG, "click: ");

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
        openConfig();
    }

    public void openConfig(){
        Intent config = new Intent(this,changerate.class);
        Bundle bdl = new Bundle();
        /*config.putExtra("dollar_rate",dollar_rate);
        config.putExtra("euro_rate",euro_rate);
        config.putExtra("jpn_rate",jpy_rate);*/
        bdl.putFloat("dollar_rate",dollar_rate);
        bdl.putFloat("euro_rate",euro_rate);
        bdl.putFloat("jpn_rate",jpy_rate);
        Log.i(TAG,"openOne:dollarRate="+dollar_rate);
        Log.i(TAG,"openOne:eurorate="+euro_rate);
        Log.i(TAG,"openOne:jpyrate="+jpy_rate);
        config.putExtras(bdl);
        //startActivity(config);
        startActivityForResult(config,1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mune1,menu);
        return true;
    }
    //处理菜单按键
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuset){
            openConfig();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==2){
            Bundle bundle = data.getExtras();
            dollar_rate = bundle.getFloat("dollar_rate_c",0.1547f);
            euro_rate = bundle.getFloat("euro_rate_c",0.132f);
            jpy_rate = bundle.getFloat("jpn_rate_c",17.1234f);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}