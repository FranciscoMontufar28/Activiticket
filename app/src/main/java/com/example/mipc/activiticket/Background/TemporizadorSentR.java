package com.example.mipc.activiticket.Background;

import android.os.Handler;
import android.os.Message;

import com.example.mipc.activiticket.InterfazSentRegActivity;

/**
 * Created by Francisco Montufar on 22/09/2015.
 */
public class TemporizadorSentR extends Thread{

    public static final int SECOND = 120;
    public static final int STOP= 121;
    boolean running;
    int cont;
    Handler handler;
    //InterfazSentRegActivity id4;

    public TemporizadorSentR(Handler handler)
    {

        this.handler=handler;

    }

    @Override
    public void run() {
        running = true;
        cont = 120;

        while (running) {
            try {
                Thread.sleep(1000);
                if (running) {
                    cont--;
                    Message msg4 = handler.obtainMessage();
                    msg4.what = SECOND;
                    msg4.arg1 = cont;
                    if(cont==0){
                        msg4.what = STOP;
                    }
                    handler.sendMessage(msg4);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void stoptemp()
    {

        running=false;

    }
}
