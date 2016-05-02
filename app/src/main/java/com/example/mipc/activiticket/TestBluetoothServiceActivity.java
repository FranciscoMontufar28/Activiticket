package com.example.mipc.activiticket;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mipc.activiticket.receivers.BluetoothReceiver;
import com.example.mipc.activiticket.services.BluetoothService;


public class TestBluetoothServiceActivity extends ActionBarActivity implements View.OnClickListener, BluetoothReceiver.BluetoothReceiverI {

    //TextView in;
    //EditText out;

    //Button c, w,r;

    ImageView welcome;

    BluetoothReceiver receiver;

    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bluetooth_service);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        /*c = (Button) findViewById(R.id.connect);
        w = (Button) findViewById(R.id.write);
        r = (Button) findViewById(R.id.read);

        in = (TextView) findViewById(R.id.in);
        out = (EditText) findViewById(R.id.out);

        c.setOnClickListener(this);
        w.setOnClickListener(this);
        r.setOnClickListener(this);*/

        welcome = (ImageView) findViewById(R.id.bienvenido);
        welcome.setOnClickListener(this);

        address = getIntent().getStringExtra(ListaDispositivosBlue.EXTRA_ADDRESS);

        receiver =  new BluetoothReceiver(this);
        IntentFilter filter =  new IntentFilter(BluetoothReceiver.ACTION_BLUETOOTH_RECEIVER);
        registerReceiver(receiver, filter);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BluetoothService.class);
        switch (v.getId()){
            case R.id.bienvenido:
                intent.setAction(BluetoothService.ACTION_CONNECT);
                intent.putExtra(ListaDispositivosBlue.EXTRA_ADDRESS, address);

                Intent intent1 = new Intent(this, VentanaInicioActivity.class);
                startActivity(intent1);
                break;
            /*case R.id.write:
                intent.setAction(BluetoothService.ACTION_WRITE);
                String data = "SE";
                //String data = out.getText().toString();
                intent.putExtra(BluetoothService.EXTRA_WRITE_DATA, data);
                break;
            case R.id.read:
                 intent.setAction(BluetoothService.ACTION_READ);
                 //String data1 = receiver.getResultData();
                 //in.setText(data1);
                 //String data2 = fileList().toString();
                break;*/
        }

        startService(intent);
    }

    @Override
    public void onBluetooth(int type, int status, String data) {
        if(type == BluetoothReceiver.TYPE_CONNECT){
            if(status ==BluetoothReceiver.OK) {
                //in.setText(data);

                Toast.makeText(this, "Pocesando", Toast.LENGTH_SHORT).show();
            }
            else{

                Toast.makeText(this, "No se conecto", Toast.LENGTH_SHORT).show();
            }
                //Toast.makeText(this, "No se conecto", Toast.LENGTH_SHORT).show();
        }else if(type==BluetoothReceiver.TYPE_READ){
           // Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
