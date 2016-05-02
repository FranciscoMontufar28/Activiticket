package com.example.mipc.activiticket.Background;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Admin on 17/11/2015.
 */
public class TemporizadorLogo extends Thread {
    public static final int SECOND = 5;
    public static final int STOP= 0;
    boolean running;
    int cont;
    Handler handler;

    public TemporizadorLogo(Handler handler)
    {

        this.handler=handler;

    }

    @Override
    public void run() {
        running = true;
        cont = 5;

        while (running) {
            try {
                Thread.sleep(1000);
                if (running) {
                    cont--;
                    Message msg5 = handler.obtainMessage();
                    msg5.what = SECOND;
                    msg5.arg1 = cont;
                    if(cont==0){
                        stoptemp();
                        msg5.what = STOP;
                    }
                    handler.sendMessage(msg5);
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
