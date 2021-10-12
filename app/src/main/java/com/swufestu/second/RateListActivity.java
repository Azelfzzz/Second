package com.swufestu.second;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RateListActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        ListView mylist = findViewById(R.id.mylist1);
//        String[] list_data = {"one","two","three","four"};
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_data);

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==2){
                    List<String> listre = (List<String>)msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(RateListActivity.this, android.R.layout.simple_list_item_1,listre);
                    mylist.setAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };

//        MyTast dt=new MyTast(handler);
//        Thread t=new Thread(dt);
//        t.start();

        RateTask dt=new RateTask(handler);
        Thread t=new Thread(dt);
        t.start();

//        RMBchange rate = new RMBchange();
//        rate.setHandler(handler);
//        Thread t2 = new Thread(rate);
//        t2.start();
    }
}