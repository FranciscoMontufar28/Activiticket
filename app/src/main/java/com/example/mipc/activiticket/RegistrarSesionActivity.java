package com.example.mipc.activiticket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mipc.activiticket.Background.AzureAsyncTask;
import com.example.mipc.activiticket.models.Usuario;


public class RegistrarSesionActivity extends ActionBarActivity implements View.OnClickListener, AzureAsyncTask.AzureI {

    EditText ursR, correo, contrasena, contrasenarep;
    Button registrar, regresar;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_sesion);

        ursR = (EditText) findViewById(R.id.usrreg);
        correo = (EditText) findViewById(R.id.usremail);
        contrasena = (EditText) findViewById(R.id.usrpass);
        contrasenarep = (EditText) findViewById(R.id.usrpassrep);

        registrar= (Button) findViewById(R.id.usrbtnreg);
        regresar = (Button) findViewById(R.id.usrbtnback);
        regresar.setOnClickListener(this);
        registrar.setOnClickListener(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Cargando ...");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usrbtnreg:
                String validar = contrasena.getText().toString();
                String validarrep = contrasenarep.getText().toString();
                if (validar.equals(validarrep)) {
                    dialog.show();
                    Usuario usr = new Usuario();
                    usr.setEmail(correo.getText().toString());
                    usr.setPassword(contrasena.getText().toString());
                    usr.setPuntos(0);
                    usr.setUsername(ursR.getText().toString());
                    AzureAsyncTask task = new AzureAsyncTask(this, usr);
                    task.execute();
                }else
                {
                    Toast.makeText(this,"Las clave que ingreso no coinciden. porfavor intentelo de nuevo",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.usrbtnback:
                Intent intent = new Intent(this, VentanaInicioActivity.class);
                startActivity(intent);
                break;
        }


    }

    @Override
    public void onResult(int result) {
        dialog.dismiss();
        if(result==AzureAsyncTask.OK){
            Intent intent = new Intent(this, VentanaInicioActivity.class);
            startActivity(intent);
            //Toast.makeText(this,"Exito !",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Error !",Toast.LENGTH_SHORT).show();
        }
    }
}
