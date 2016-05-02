package com.example.mipc.activiticket;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;

import com.example.mipc.activiticket.Background.ContadorReceiver;
import com.example.mipc.activiticket.Background.TemporizadorDespR;
import com.example.mipc.activiticket.receivers.BluetoothReceiver;
import com.example.mipc.activiticket.services.BluetoothService;


public class InterfazDespRegActivity extends ActionBarActivity implements View.OnClickListener,BluetoothReceiver.BluetoothReceiverI {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg2){
            if(msg2.what == TemporizadorDespR.SECOND)
            {
                int second=msg2.arg1;
                reloj.setText(""+ second);
            }else{
                GameoverDespR();
            }
        }
    };

    Button salirdesplazaR;
    TextView reloj, contadorsentadillas, Nomusuario;
    TemporizadorDespR thread;
    ContadorReceiver thread1;
    MediaPlayer music;
    BluetoothReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_desp_reg);
        Nomusuario = (TextView) findViewById(R.id.nombreusuarioDesp);
        reloj = (TextView) findViewById(R.id.relojdespr);
        contadorsentadillas = (TextView) findViewById(R.id.contadordespr);
        salirdesplazaR = (Button) findViewById(R.id.btnsalirdespR);
        salirdesplazaR.setOnClickListener(this);
        music= MediaPlayer.create(this, R.drawable.good);

        //nombre = usr.getUsername();
        Nomusuario.setText("Usuario registrado");
        thread=null;
        thread1=null;

        receiver =  new BluetoothReceiver(this);
        IntentFilter filter =  new IntentFilter(BluetoothReceiver.ACTION_BLUETOOTH_RECEIVER);
        registerReceiver(receiver, filter);


        if(thread1 == null){
            thread1=new ContadorReceiver(this);
            thread1.start();
        }

        if(thread == null){
            thread = new TemporizadorDespR(handler);
            thread.start();
            music.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        music.stop();
    }

    @Override
    protected void onDestroy() {
        music.stop();
        super.onDestroy();
    }


    public void GameoverDespR(){
        thread=null;
        music.stop();
        reloj.setText("120");
        Intent intent2 = new Intent(this, GameOverDespRActivity.class);
        startActivity(intent2);
        //Log.i("tag", "cambio");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BluetoothService.class);
        switch (v.getId()) {
            case R.id.btnsalirdespR:
                intent.setAction(BluetoothService.ACTION_WRITE);
                String data = "BR";
                intent.putExtra(BluetoothService.EXTRA_WRITE_DATA, data);
                if(thread !=null)
                {
                    thread.stoptemp();
                    thread=null;
                    thread1=null;
                    music.stop();
                }
                Intent intent71 = new Intent(this, SelecActiRActivity.class);
                startActivity(intent71);
                finish();
                break;
        }
        startService(intent);
    }


    @Override
    public void onBluetooth(int type, int status, String data) {
        if(type==BluetoothReceiver.TYPE_READ){

            String dato = data.toString();
            //contadorsentadillas.setText(""+dato);
            if(dato.equals("a")) {
                contadorsentadillas.setText("30");
            }else if (dato.equals("b")) {
                contadorsentadillas.setText("29");
            }else if (dato.equals("c")) {
                contadorsentadillas.setText("28");
            }else if (dato.equals("d")) {
                contadorsentadillas.setText("27");
            }else if (dato.equals("e")) {
                contadorsentadillas.setText("26");
            }else if (dato.equals("f")) {
                contadorsentadillas.setText("25");
            }else if (dato.equals("g")) {
                contadorsentadillas.setText("24");
            }else if (dato.equals("h")) {
                contadorsentadillas.setText("23");
            }else if (dato.equals("i")) {
                contadorsentadillas.setText("22");
            }else if (dato.equals("j")) {
                contadorsentadillas.setText("21");
            }else if (dato.equals("k")) {
                contadorsentadillas.setText("20");
            }else if (dato.equals("l")) {
                contadorsentadillas.setText("19");
            }else if (dato.equals("m")) {
                contadorsentadillas.setText("18");
            }else if (dato.equals("n")) {
                contadorsentadillas.setText("17");
            }else if (dato.equals("o")) {
                contadorsentadillas.setText("16");
            }else if (dato.equals("p")) {
                contadorsentadillas.setText("15");
            }else if (dato.equals("q")) {
                contadorsentadillas.setText("14");
            }else if (dato.equals("r")) {
                contadorsentadillas.setText("13");
            }else if (dato.equals("s")) {
                contadorsentadillas.setText("12");
            }else if (dato.equals("t")) {
                contadorsentadillas.setText("11");
            }else if (dato.equals("u")) {
                contadorsentadillas.setText("10");
            }else if (dato.equals("v")) {
                contadorsentadillas.setText("9");
            }else if (dato.equals("w")) {
                contadorsentadillas.setText("8");
            }else if (dato.equals("x")) {
                contadorsentadillas.setText("7");
            }else if (dato.equals("y")) {
                contadorsentadillas.setText("6");
            }else if (dato.equals("z")) {
                contadorsentadillas.setText("5");
            }else if (dato.equals("1")) {
                contadorsentadillas.setText("4");
            }else if (dato.equals("2")) {
                contadorsentadillas.setText("3");
            }else if (dato.equals("3")) {
                contadorsentadillas.setText("2");
            }else if (dato.equals("4")) {
                contadorsentadillas.setText("1");
            }else {
                contadorsentadillas.setText("0");
                thread.stoptemp();
                thread1.Cancelar();
                thread=null;
                thread1=null;
                music.stop();
                Intent intent = new Intent(this, WinActivity.class);
                startActivity(intent);
            }

            //Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
        }

    }
}
