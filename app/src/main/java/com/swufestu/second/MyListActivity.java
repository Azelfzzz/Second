package com.swufestu.second;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

public class MyListActivity extends ListActivity {
    private static final String TAG = "MyListActivity";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        List<String> list1 = new ArrayList<>();
//        for(int i = 1;i<100;i++){
//            list1.add("item"+i);
//        }
//
//        String[] list_data = {"one","two","three","four"};
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_data);
//        setListAdapter(adapter);

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==7){
                    List<String> listre = (List<String>)msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(MyListActivity.this, android.R.layout.simple_list_item_1,listre);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };

        MyTast dt=new MyTast(handler);
        Thread t=new Thread(dt);
        t.start();

    }
}