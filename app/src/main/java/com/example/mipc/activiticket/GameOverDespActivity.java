package com.example.mipc.activiticket;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mipc.activiticket.services.BluetoothService;


public class GameOverDespActivity extends ActionBarActivity implements View.OnClickListener {

    ImageView gameover;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_over_desp);
        gameover = (ImageView) findViewById(R.id.gameoverdesp);
        gameover.setOnClickListener(this);
        mediaPlayer= MediaPlayer.create(this, R.drawable.over);
        mediaPlayer.start();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BluetoothService.class);
        switch (v.getId())
        {
            case R.id.gameoverdesp:
                mediaPlayer.stop();

                intent.setAction(BluetoothService.ACTION_WRITE);
                String data = "BR";
                intent.putExtra(BluetoothService.EXTRA_WRITE_DATA, data);

                Intent intent1 = new Intent(this, VentanaInicioActivity.class);
                startActivity(intent1);

                break;
        }
        startService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}
