package com.example.mipc.activiticket.Background;

import android.nfc.Tag;
import android.os.Message;
import android.os.Handler;
import android.util.Log;

import com.example.mipc.activiticket.InterfazDespActivity;



/**
 * Created by Francisco Montufar on 20/09/2015.
 */
public class TemporizadorDesp extends Thread{

    public static final int SECOND = 120;
    public static final int STOP= 121;
    boolean running;
    int cont;
    Handler handler;

    //InterfazDespActivity id;

    public TemporizadorDesp(Handler handler)
    {
        running = true;
        this.handler=handler;
        //this.id = id;
    }


    public void stoptemp()
    {

        running=false;
        Log.e("haur", "stopTemp "+running);
    }


    @Override
    public void run() {

        cont = 120;
        Log.i("haur","Run "+running);

        while (running) {
            try {
                Log.i("haur","while "+running);
                Thread.sleep(1000);
                if (cont>0 && running) {
                    Log.e("haur", "cont>0 "+running);
                    cont--;
                    Message msg1 = handler.obtainMessage();
                    msg1.what = SECOND;
                    msg1.arg1 = cont;
                    if(cont==0){

                        msg1.what = STOP;
                    }

                    handler.sendMessage(msg1);
                }
                else{
                    Log.e("haur", "");
                }

            } catch (InterruptedException e) {
                Log.e("haur", "thread catch");
                e.printStackTrace();
            }

        }

    }


}




