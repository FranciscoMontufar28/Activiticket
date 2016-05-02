package com.example.mipc.activiticket;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mipc.activiticket.services.BluetoothService;


public class InstruccionesDespActivity extends ActionBarActivity implements View.OnClickListener {

    Button btn_continuarD, btn_cancelarD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones_desp);
        btn_continuarD= (Button) findViewById(R.id.btncontinuardesp);
        btn_cancelarD= (Button) findViewById(R.id.btncancelardesp);

        btn_continuarD.setOnClickListener(this);
        btn_cancelarD.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BluetoothService.class);
        switch (v.getId())
        {
            case R.id.btncontinuardesp:

                intent.setAction(BluetoothService.ACTION_WRITE);
                String data = "DE";
                intent.putExtra(BluetoothService.EXTRA_WRITE_DATA, data);

                Intent intent2=new Intent(this, InterfazDespActivity.class);
                startActivity(intent2);
                break;
            case R.id.btncancelardesp:
                Intent intent1 = new Intent(this, SelecActiActivity.class);
                startActivity(intent1);
                break;
        }
            startService(intent);
    }
}
