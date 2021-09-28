package com.swufestu.second;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RMBchange extends AppCompatActivity implements Runnable{
    private static final String TAG = "activity_rmbchange";
    float result1=0;
    float dollar_rate;
    float euro_rate;
    float jpy_rate;
    TextView result;
    EditText input;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmbchange);

        dollar_rate = 0.1547f;
        euro_rate = 0.132f;
        jpy_rate = 17.1234f;


        Shareedsave();
        result = findViewById(R.id.ResultofChange);
        input = findViewById(R.id.inputRMB);

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: ");
                if(msg.what==5){
                    String str =(String)msg.obj;
                    Log.i(TAG, "handleMessage: msg="+str);
                    result.setText(str);
                }
                super.handleMessage(msg);
            }
        };

        Thread thread = new Thread(this);
        thread.start();

    }

    private void Shareedsave() {
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        dollar_rate = sharedPreferences.getFloat("dollar_rate",0.1547f);
        euro_rate = sharedPreferences.getFloat("euro_rate",0.132f);
        jpy_rate = sharedPreferences.getFloat("jpn_rate",17.1234f);
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

    public void run(){

        Log.i(TAG, "run: run....");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        URL url = null;
        Message msg = null;

        Connecttonet();

        try {
            msg = handler.obtainMessage(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        msg.obj = "Hello from run";
        handler.sendMessage(msg);
        Log.i(TAG, "run: run:消息已经发送");
    }

    private void Connecttonet() {
        URL url;
        try {
            Log.i(TAG, "访问URL...");
            url = new URL("http://www.usd-cny.com/icbc.htm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            String html = InputStream2String(in);
            Log.i(TAG, "run:html="+html);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static String InputStream2String(InputStream in) {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(in, "gb2312");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}