package com.example.mipc.activiticket.Background;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.mipc.activiticket.InstuccionesSentActivity;
import com.example.mipc.activiticket.receivers.BluetoothReceiver;
import com.example.mipc.activiticket.services.BluetoothService;

/**
 * Created by Admin on 18/11/2015.
 */
public class ContadorReceiver extends Thread {


    boolean running;
    Context context;


    public ContadorReceiver(Context context) {

        this.context=context;
    }


    public void run()
    {
        running=true;
        while (running){

            try {
                Thread.sleep(500);
                if(true) {
                    Intent intent = new Intent(context, BluetoothService.class);
                    intent.setAction(BluetoothService.ACTION_READ);
                    context.startService(intent);

                    Log.e("threadcontador", "run");
                }
                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void Cancelar()
    {
        running=false;
    }

}
