package com.example.mipc.activiticket;


import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.mipc.activiticket.Background.ContadorReceiver;
import com.example.mipc.activiticket.receivers.BluetoothReceiver;
import com.example.mipc.activiticket.services.BluetoothService;


public class InstuccionesSentActivity extends ActionBarActivity implements View.OnClickListener{

    Button btn_continuarS, btn_cancelarS;
    BluetoothReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_instucciones_sent);

        btn_continuarS= (Button) findViewById(R.id.btncontinuarsent);
        btn_cancelarS= (Button) findViewById(R.id.btncancelarsent);
        btn_continuarS.setOnClickListener(this);
        btn_cancelarS.setOnClickListener(this);


    }


   @Override
    public void onClick(View v) {
       Intent intent = new Intent(this, BluetoothService.class);
        switch (v.getId())
        {
            case R.id.btncontinuarsent:

                intent.setAction(BluetoothService.ACTION_WRITE);
                String data = "SE";
                intent.putExtra(BluetoothService.EXTRA_WRITE_DATA, data);

                Intent intent2 = new Intent(this, InterfazSentActivity.class);
                startActivity(intent2);
                break;
            case R.id.btncancelarsent:
                Intent intent1 = new Intent(this, SelecActiActivity.class);
                startActivity(intent1);
                break;
        }
       startService(intent);


    }
}

