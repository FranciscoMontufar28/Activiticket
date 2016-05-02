package com.example.mipc.activiticket.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Francisco Montufar on 10/10/2015.
 */
public class BluetoothReceiver extends BroadcastReceiver {


    public static final int TYPE_CONNECT = 0;
    public static final int TYPE_WRITE = 1;
    public static final int TYPE_READ = 2;

    public static final int OK=0;
    public static final int FAIL=1;

    public static final String ACTION_BLUETOOTH_RECEIVER="com.example.mipc.activiticket.reciver";

    public static final String EXTRA_STATUS="status";
    public static final String EXTRA_TYPE="type";
    public static final String EXTRA_IN_DATA="data";

    public interface  BluetoothReceiverI {
        void onBluetooth(int type, int status, String data);
    }
    BluetoothReceiverI receiverI;

    public BluetoothReceiver(BluetoothReceiverI receiverI){
        this.receiverI = receiverI;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        int status =  intent.getIntExtra(EXTRA_STATUS,0);
        int type = intent.getIntExtra(EXTRA_TYPE,0);
        //String data =null;

        if(type==TYPE_READ) {
            String data = intent.getStringExtra(EXTRA_IN_DATA);

           receiverI.onBluetooth(type,status,data);
        }

    }
}
