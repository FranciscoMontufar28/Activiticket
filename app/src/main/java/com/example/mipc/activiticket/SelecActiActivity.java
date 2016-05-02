package com.example.mipc.activiticket;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mipc.activiticket.Background.TemporizadorDesp;
import com.example.mipc.activiticket.services.BluetoothService;

public class SelecActiActivity extends ActionBarActivity implements View.OnClickListener {
    Button Sentadillas,Desplazamientos,Aleatorio;
    ImageView Homesinregistro;
    MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_acti);

        Sentadillas = (Button) findViewById(R.id.sentadillas);
        Desplazamientos = (Button) findViewById(R.id.desplazamientos);
        Aleatorio = (Button) findViewById(R.id.aleatorio);
        Homesinregistro = (ImageView) findViewById(R.id.homeS);

        mediaPlayer= MediaPlayer.create(this,R.drawable.knightlife);

        Sentadillas.setOnClickListener(this);
        Desplazamientos.setOnClickListener(this);
        Aleatorio.setOnClickListener(this);
        Homesinregistro.setOnClickListener(this);

        mediaPlayer.start();


    }


    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    int randon = (int)(Math.random()*(0-10)+10);
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sentadillas:
                mediaPlayer.stop();
                Intent intent41 = new Intent(this, InstuccionesSentActivity.class);
                startActivity(intent41);
                break;
            case R.id.desplazamientos:
                mediaPlayer.stop();
                Intent intent42 = new Intent(this, InstruccionesDespActivity.class);
                startActivity(intent42);
                break;
            case R.id.aleatorio:
                mediaPlayer.stop();
                if(randon>=5) {
                    Intent intent43 = new Intent(this, InstuccionesSentActivity.class);
                    startActivity(intent43);
                }else{
                    Intent intent43 = new Intent(this, InstruccionesDespActivity.class);
                    startActivity(intent43);
                }
                break;
            case R.id.homeS:
                mediaPlayer.stop();
                Intent intent44 = new Intent(this, VentanaInicioActivity.class);
                startActivity(intent44);
                break;
        }

    }

}
