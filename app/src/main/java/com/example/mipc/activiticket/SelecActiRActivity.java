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
import android.widget.TextView;

import com.example.mipc.activiticket.Background.AzureAsyncTask;
import com.example.mipc.activiticket.models.Usuario;


public class SelecActiRActivity extends ActionBarActivity implements View.OnClickListener, AzureAsyncTask.AzureI{

    Button DesplazamientosRegistro, SentadillasRegistro, Aleatorioregistro;
    ImageView Homeregistro;
    TextView Nomusuario;
    AzureAsyncTask task;
    MediaPlayer mediaPlayer;
    public static final String ACTION_NAME = "Usuario";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_acti_r);

        DesplazamientosRegistro= (Button) findViewById(R.id.desplazamientosR);
        SentadillasRegistro= (Button) findViewById(R.id.sentadillasR);
        Aleatorioregistro= (Button) findViewById(R.id.aleatorioR);
        Homeregistro= (ImageView) findViewById(R.id.homeR);
        Nomusuario = (TextView) findViewById(R.id.nombreusuarioSA);
        mediaPlayer= MediaPlayer.create(this,R.drawable.knightlife);


        //String Data = getIntent().getExtras().get(ACTION_NAME).toString();

        Nomusuario.setText("usr registrado");

        DesplazamientosRegistro.setOnClickListener(this);
        SentadillasRegistro.setOnClickListener(this);
        Aleatorioregistro.setOnClickListener(this);
        Homeregistro.setOnClickListener(this);

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
            case R.id.sentadillasR:
                mediaPlayer.stop();
                Intent intent51 = new Intent(this, InstruccionesSentRActivity.class);
                startActivity(intent51);
                break;
            case R.id.desplazamientosR:
                mediaPlayer.stop();
                Intent intent52 = new Intent(this, InstruccionesDespRActivity.class);
                startActivity(intent52);
                break;
            case R.id.aleatorioR:
                mediaPlayer.stop();
                if(randon>=5) {
                    Intent intent53 = new Intent(this, InstruccionesSentRActivity.class);
                    startActivity(intent53);
                }else{
                    Intent intent53 = new Intent(this, InstruccionesDespRActivity.class);
                    startActivity(intent53);
                }
                break;
            case R.id.homeR:
                mediaPlayer.stop();
                Intent intent54 = new Intent(this, VentanaInicioActivity.class);
                startActivity(intent54);
                finish();
                break;
        }

    }

   @Override
    public void onResult(int result) {

        String nomnbreusuario = task.getUsr().getUsername().toString();
        Nomusuario.setText(nomnbreusuario);
    }
}
