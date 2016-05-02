package com.example.mipc.activiticket.Background;

import android.os.Handler;
import android.os.Message;

import com.example.mipc.activiticket.InterfazDespRegActivity;

/**
 * Created by Francisco Montufar on 22/09/2015.
 */
public class TemporizadorDespR extends Thread {
    public interface cambiarInterfaz{
        public void cambioActivity();
    }

    public static final int SECOND = 120;
    public static final int STOP= 121;
    boolean running, timeup;
    int cont;
    Handler handler;

    //InterfazDespRegActivity id2;

    public TemporizadorDespR(Handler handler)
    {
        this.handler=handler;
        //this.id2 = id2;
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
                    Message msg2 = handler.obtainMessage();
                    msg2.what = SECOND;
                    msg2.arg1 = cont;
                    if(cont==0){
                        msg2.what = STOP;
                    }
                    handler.sendMessage(msg2);
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