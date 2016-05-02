package com.example.mipc.activiticket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mipc.activiticket.Background.AzureAsyncTask;
import com.example.mipc.activiticket.models.Usuario;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;


public class IniciarSesionActivity extends ActionBarActivity implements View.OnClickListener, AzureAsyncTask.AzureI{

    Button Ingresarsesion,Salir;
    private MobileServiceClient mClient;

    ProgressDialog dialog;

    EditText email, password;


    AzureAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Cargando ...");

        Ingresarsesion= (Button) findViewById(R.id.ingresarsesion);
        Salir = (Button) findViewById(R.id.saliriniciar);
        Salir.setOnClickListener(this);
        Ingresarsesion.setOnClickListener(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ingresarsesion:
                validateUser();
                break;
            case R.id.saliriniciar:
                Intent intent12 = new Intent(this, VentanaInicioActivity.class);
                startActivity(intent12);

        }
    }

    public void validateUser(){
        dialog.show();
        String emailS = email.getText().toString();
        String passS = password.getText().toString();
        task = new AzureAsyncTask(this, emailS,passS);
        task.execute();

    }

    @Override
    public void onResult(int result) {
        dialog.dismiss();
        if(result == AzureAsyncTask.OK){
            Usuario usr = task.getUsr();
            Intent intent = new Intent(this, SelecActiRActivity.class);
            startActivity(intent);


            String nombre = task.getUsr().getUsername();
            Intent intent1 = new Intent(this, SelecActiRActivity.class);
            intent1.putExtra(SelecActiRActivity.ACTION_NAME,nombre);
            Log.e("nombre",""+nombre );
            startActivity(intent1);



        }else if(result == AzureAsyncTask.INCORRECT){
            Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Error al comunicarse con el servidor, vuelva a intentar",Toast.LENGTH_SHORT).show();
        }

    }
}
