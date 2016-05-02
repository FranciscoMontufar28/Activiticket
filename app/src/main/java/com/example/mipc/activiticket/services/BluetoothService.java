package com.example.mipc.activiticket.services;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import com.example.mipc.activiticket.ListaDispositivosBlue;
import com.example.mipc.activiticket.receivers.BluetoothReceiver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Francisco Montufar on 10/10/2015.
 */
public class BluetoothService extends Service {

    public static final String ACTION_WRITE="action_write";
    public static final String ACTION_READ="action_read";
    public static final String ACTION_CONNECT="action_connect";

    //public static final String EXTRA_ADDRESS="address";
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    //public static final String EXTRA_UUID="00001101-0000-1000-8000-00805F9B34FB";
    public static final String EXTRA_WRITE_DATA="data";

    String address = null;

    HandlerService handlerService;
    BluetoothAdapter adapter;
    BluetoothSocket socket;
    BluetoothManager manager;


    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("HandlerThreadBluetooth", Thread.MAX_PRIORITY);
        thread.start();


        handlerService = new HandlerService(thread.getLooper());
        manager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Message msg = handlerService.obtainMessage();
        msg.obj = intent;
        handlerService.sendMessage(msg);

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




    private class HandlerService extends Handler{

        public HandlerService(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Intent intent = (Intent) msg.obj;
            if(ACTION_CONNECT.equals(intent.getAction())){
                connectBluetooth(intent);
            }else if(ACTION_WRITE.equals(intent.getAction())){
                writeBluetooth(intent);
            }else{
                readBluetooth();
            }
        }


    }

    private void readBluetooth() {
        Intent receiver = new Intent(BluetoothReceiver.ACTION_BLUETOOTH_RECEIVER);
        receiver.putExtra(BluetoothReceiver.EXTRA_TYPE, BluetoothReceiver.TYPE_READ);
        try {
            ByteArrayOutputStream stream =  new ByteArrayOutputStream();
            stream.write(socket.getInputStream().read());
            String data = new String(stream.toByteArray());
            receiver.putExtra(BluetoothReceiver.EXTRA_STATUS, BluetoothReceiver.OK);
            receiver.putExtra(BluetoothReceiver.EXTRA_IN_DATA, data);
        } catch (IOException e) {
            e.printStackTrace();
            receiver.putExtra(BluetoothReceiver.EXTRA_STATUS, BluetoothReceiver.FAIL);
        }
        sendBroadcast(receiver);
    }

    private void writeBluetooth(Intent intent) {
        Intent receiver = new Intent(BluetoothReceiver.ACTION_BLUETOOTH_RECEIVER);
        receiver.putExtra(BluetoothReceiver.EXTRA_TYPE, BluetoothReceiver.TYPE_WRITE);
        try {
            String data = intent.getStringExtra(EXTRA_WRITE_DATA);
            socket.getOutputStream().write(data.getBytes());
            receiver.putExtra(BluetoothReceiver.EXTRA_STATUS, BluetoothReceiver.OK);
        } catch (IOException e) {
            e.printStackTrace();
            receiver.putExtra(BluetoothReceiver.EXTRA_STATUS, BluetoothReceiver.FAIL);
        }

        sendBroadcast(receiver);
    }

    private void connectBluetooth(Intent intent) {
        Intent receiver = new Intent(BluetoothReceiver.ACTION_BLUETOOTH_RECEIVER);
        receiver.putExtra(BluetoothReceiver.EXTRA_TYPE, BluetoothReceiver.TYPE_CONNECT);

        try {
            if (socket == null) {
                address = intent.getStringExtra(ListaDispositivosBlue.EXTRA_ADDRESS);
                //UUID myUUID = UUID.fromString(intent.getStringExtra(EXTRA_UUID));

                adapter = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                BluetoothDevice dispositivo = adapter.getRemoteDevice(address);//connects to the device's address and checks if it's available
                socket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                socket.connect();//start connection
                receiver.putExtra(BluetoothReceiver.EXTRA_STATUS, BluetoothReceiver.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
            receiver.putExtra(BluetoothReceiver.EXTRA_STATUS, BluetoothReceiver.FAIL);
        }
        sendBroadcast(receiver);
    }
}
