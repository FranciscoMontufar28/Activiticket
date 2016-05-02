package com.example.mipc.activiticket;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mipc.activiticket.services.BluetoothService;


public class InstruccionesDespRActivity extends ActionBarActivity implements View.OnClickListener {

    Button btn_continuarDr, btn_cancelarDr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones_desp_r);
        btn_continuarDr= (Button) findViewById(R.id.btncontinuardespr);
        btn_cancelarDr= (Button) findViewById(R.id.btncancelardespr);

        btn_continuarDr.setOnClickListener(this);
        btn_cancelarDr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BluetoothService.class);
        switch (v.getId())
        {
            case R.id.btncontinuardespr:
                intent.setAction(BluetoothService.ACTION_WRITE);
                String data = "DE";
                intent.putExtra(BluetoothService.EXTRA_WRITE_DATA, data);

                Intent intent2=new Intent(this, InterfazDespRegActivity.class);
                startActivity(intent2);
                break;
            case R.id.btncancelardespr:
                Intent intent1 = new Intent(this, SelecActiRActivity.class);
                startActivity(intent1);
                break;
        }
        startService(intent);

    }
}
