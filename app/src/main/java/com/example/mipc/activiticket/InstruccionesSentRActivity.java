package com.example.mipc.activiticket;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mipc.activiticket.services.BluetoothService;


public class InstruccionesSentRActivity extends ActionBarActivity implements View.OnClickListener {
    Button btn_continuarSr, btn_cancelarSr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones_sent_r);
        btn_continuarSr= (Button) findViewById(R.id.btncontinuarsentr);
        btn_cancelarSr= (Button) findViewById(R.id.btncancelarsentr);
        btn_cancelarSr.setOnClickListener(this);
        btn_continuarSr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BluetoothService.class);
        switch (v.getId())
        {
            case R.id.btncontinuarsentr:

                intent.setAction(BluetoothService.ACTION_WRITE);
                String data = "SE";
                intent.putExtra(BluetoothService.EXTRA_WRITE_DATA, data);

                Intent intent2 = new Intent(this, InterfazSentRegActivity.class);
                startActivity(intent2);
                break;
            case R.id.btncancelarsentr:
                Intent intent1 = new Intent(this, SelecActiRActivity.class);
                startActivity(intent1);
                break;
        }
        startService(intent);

    }
}
