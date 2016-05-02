package com.example.mipc.activiticket;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class VentanaInicioActivity extends ActionBarActivity implements View.OnClickListener {

    Button Iniciarsesion,Ingresarsinregistro,Registrarme;
    MediaPlayer mediaPlayer;
    int randon = (int)(Math.random()*(0-10)+10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_inicio);
        Intent newinte = getIntent();
        Iniciarsesion = (Button) findViewById(R.id.iniciarsesion);
        Ingresarsinregistro = (Button) findViewById(R.id.ingresarsinregistro);
        Registrarme = (Button) findViewById(R.id.registrarme);

        Iniciarsesion.setOnClickListener(this);
        Ingresarsinregistro.setOnClickListener(this);
        Registrarme.setOnClickListener(this);
       // new ConnectBT().execute();


        if(randon>=5) {
            mediaPlayer = MediaPlayer.create(this, R.drawable.finale);
        }else{
            mediaPlayer = MediaPlayer.create(this, R.drawable.unstoppable);
        }
            //mediaPlayer = MediaPlayer.create(this, R.drawable.finale);
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

    @Override
    public void onClick(View v) {
        //mediaPlayer.stop();
        switch (v.getId())
        {
            case R.id.iniciarsesion:
                mediaPlayer.stop();
                Intent intent61 = new Intent(this, IniciarSesionActivity.class);
                startActivity(intent61);
                break;
            case R.id.ingresarsinregistro:
                mediaPlayer.stop();
                Intent intent62 = new Intent(this, SelecActiActivity.class);
                startActivity(intent62);
                break;
            case R.id.registrarme:
                mediaPlayer.stop();
                Intent intent63 = new Intent(this, RegistrarSesionActivity.class);
                startActivity(intent63);
                break;
        }

    }
    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

}
