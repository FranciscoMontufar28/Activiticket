package com.example.mipc.activiticket.Background;

import android.os.Handler;
import android.os.Message;



/**
 * Created by Francisco Montufar on 21/09/2015.
 */
public class TemporizadorSent extends Thread {

    public static final int SECOND = 120;
    public static final int STOP= 0;
    boolean running;
    int cont;
    Handler handler;

    public TemporizadorSent(Handler handler)
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
                if (running&&cont>0) {
                    cont--;
                    Message msg3 = handler.obtainMessage();
                    msg3.what = SECOND;
                    msg3.arg1 = cont;

                    if (cont==0)
                    {
                        //running = false;
                        //cont = 120;
                        stoptemp();
                        msg3.what = STOP;
                        msg3.arg1 = cont;

                    }

                    handler.sendMessage(msg3);
                }


            } catch (InterruptedException e) {
               e.printStackTrace();
            }

        }
    }
    public void stoptemp()
    {
        running = false;
        cont = 0;
    }
}

