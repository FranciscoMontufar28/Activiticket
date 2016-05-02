package com.example.mipc.activiticket;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mipc.activiticket.Background.TemporizadorLogo;


public class VentanaLogoActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg5){
            if(msg5.what == TemporizadorLogo.SECOND)
            {
                int second=msg5.arg1;
            }else{
                terminar();
            }

        }
    };


    TemporizadorLogo thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_logo);

        if(thread == null){
            thread = new TemporizadorLogo(handler);
            thread.start();
        }

    }

    public void terminar(){

        thread=null;
        Intent intent2 = new Intent(this, ListaDispositivosBlue.class);
        startActivity(intent2);



    }

}
