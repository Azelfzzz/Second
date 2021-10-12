package com.swufestu.second;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyTast implements Runnable{
    private static final String TAG = "Task";
    Handler handler;

    public MyTast(Handler handler){
        this.handler=handler;
    }
    List<String> retlist=new ArrayList<String>();

    public void run(){

        Log.i(TAG, "run: run....");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
            Log.i(TAG, "run: tittle="+doc.title());
            Elements tables = doc.getElementsByTag("table");
            //获取第一张表
            Element firsttable = tables.first();
            Elements tds = firsttable.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=5){
//                Log.i(TAG, "run: td:"+td.text());
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+1);
                retlist.add(td1.text()+"==>"+td2.text());
//                Log.i(TAG, "run: map="+map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message msg = null;
        try {
            msg = handler.obtainMessage(7);
            msg.obj = retlist;
            handler.sendMessage(msg);
            Log.i(TAG, "run: run:消息已经发送");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
