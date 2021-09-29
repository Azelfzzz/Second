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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RMBchange extends AppCompatActivity implements Runnable{
    private static final String TAG = "activity_rmbchange";
    float result1=0;
    float dollar_rate;
    float euro_rate;
    float jpy_rate;
    TextView result;
    EditText input;
    Handler handler;
    Map<String, String> map = new HashMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmbchange);
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: ");
                if(msg.what==5){
                    Bundle bundle = msg.getData();
                    String dollar = bundle.getString("dollar");
                    String euro = bundle.getString("euro");
                    String jpny = bundle.getString("jpny");
                    dollar_rate = 100/Float.parseFloat(dollar);
                    euro_rate = 100/Float.parseFloat(euro);
                    jpy_rate = 100/Float.parseFloat(jpny);
                }
                super.handleMessage(msg);
            }
        };

        Thread thread = new Thread(this);
        thread.start();

//        dollar_rate = 0.1547f;
//        euro_rate = 0.132f;
//        jpy_rate = 17.1234f;
//        dollar_rate = 100/Float.parseFloat(map.get("美元"));
//        euro_rate = 100/Float.parseFloat(map.get("欧元"));
//        jpy_rate = 100/Float.parseFloat(map.get("日元"));
//        Log.i(TAG, "onCreate: dollar:"+map.get("美元"));

        //WritetoRate();
        Shareedsave();
        result = findViewById(R.id.ResultofChange);
        input = findViewById(R.id.inputRMB);
    }

    private void WritetoRate() {
        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("dollar_rate",dollar_rate);
        editor.putFloat("euro_rate",euro_rate);
        editor.putFloat("jpn_rate",jpy_rate);
        editor.apply();
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

        /*URL url = null;
        Connecttonet();*/

        //JSOUP
        try {
            Document doc = Jsoup.connect("http://www.usd-cny.com/").get();
            Log.i(TAG, "run: tittle="+doc.title());
            Elements tables = doc.getElementsByTag("table");
            //获取第一张表
            Element firsttable = tables.first();
            //Log.i(TAG, "run: firsttable:"+firsttable);
//            Elements ths = firsttable.getElementsByTag("th");
//            for(Element th :ths){
////                Log.i(TAG, "run: th="+th);
////                Log.i(TAG, "run: th.html:"+th.html());      //显示<b>xxxx</b>
////                Log.i(TAG, "run: th.text:"+th.text());      //显示xxxx
//            }
//            Element th2 = ths.get(1);
//            Log.i(TAG, "run: th2:"+th2);
            Elements tds = firsttable.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=5){
//                Log.i(TAG, "run: td:"+td.text());
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+1);
                map.put(td1.text(),td2.text());
//                Log.i(TAG, "run: map="+map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message msg = null;
        try {
            msg = handler.obtainMessage(5);
            Bundle bdl = new Bundle();
            bdl.putString("dollar",map.get("美元"));
            bdl.putString("euro",map.get("欧元"));
            bdl.putString("jpny",map.get("日元"));
            msg.setData(bdl);
            handler.sendMessage(msg);
            Log.i(TAG, "run: run:消息已经发送");
            RMBchange runnable = new RMBchange();
            handler.postDelayed(runnable, 24*60*60*1000);//每两秒执行一次runnable.
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override//保存
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("Result",result1);
    }

    @Override//还原
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result1 = savedInstanceState.getFloat("Result",0.0f);
    }
}