package com.example.nutdanai.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Nutdanai on 9/27/2016.
 */

public class MyService extends Service {
    private MyThread myThread;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        myThread = new MyThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service Started... ",Toast.LENGTH_SHORT).show();
        if(!myThread.isAlive()){
            myThread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service Stopped... ",Toast.LENGTH_SHORT).show();
        myThread.finish = true;
    }


    private class MyThread extends Thread{
        private final static String TAG = "ServiceDemo";
        private final static int DELAY = 3000;
        private int i =0;
        private boolean finish = false;


        public void run (){
            while (true){
                Log.d(TAG,Integer.toString(i++));
                try{
                    sleep(DELAY);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                if(finish){
                    return;
                }
            }
        }
    }
}
