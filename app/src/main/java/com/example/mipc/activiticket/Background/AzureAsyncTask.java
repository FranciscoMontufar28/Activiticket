package com.example.mipc.activiticket.Background;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;

import com.example.mipc.activiticket.SelecActiRActivity;
import com.example.mipc.activiticket.models.Usuario;
import com.example.mipc.activiticket.services.BluetoothService;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Francisco Montufar on 10/10/2015.
 */
public class AzureAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    public static final int OK=1;
    public static final int INCORRECT=2;
    public static final int FAIL=0;

    private static final int REGISTER=1;
    private static final int LOGIN=2;
    private static final int SCORE=3;

    public interface AzureI{
        void onResult(int result);
    }

    int type;

    Usuario usr;
    String email, pass;

    private MobileServiceClient mClient;
    private AzureI azureI;

    public AzureAsyncTask(Context context, Usuario usr) {
        azureI = (AzureI) context;
        initClient(context);
        this.usr = usr;
        if(usr.getId() == null)
            type = REGISTER;
        else
            type = SCORE;
    }

    public AzureAsyncTask(Context context, String email, String pass) {
        azureI = (AzureI) context;
        initClient(context);
        this.email = email;
        this.pass = pass;
        type = LOGIN;
    }

    private void initClient(Context context){
        try {
            mClient = new MobileServiceClient(
                    "https://activiticket.azure-mobile.net/",
                    "aKQKTOeHHszApdmLHSELnMLvDgWiSc27",
                    context
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Integer doInBackground(Integer... params) {
        switch (type){
            case REGISTER: return registerUser();
            case LOGIN: return loginUser();
            case SCORE: return uploadScore();
            default: return OK;
        }
    }

    private Integer uploadScore() {
        try {
            mClient.getTable(Usuario.class).update(usr).get();
            return OK;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return FAIL;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return FAIL;
        }
    }

    private Integer loginUser() {
        try {
            MobileServiceList<Usuario> data  =mClient.getTable(Usuario.class).where().field("email").eq(email).and().field("password").eq(pass).execute().get();
            if(data.size()>0) {
                usr = data.get(0);
                return OK;
            }else
                return INCORRECT;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return FAIL;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return FAIL;
        }
    }

    private Integer registerUser() {
        int status = 0;
        try {
            Usuario usuario = mClient.getTable(Usuario.class).insert(usr).get();
            usr.setId(usuario.getId());
            return  OK;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return FAIL;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return FAIL;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        azureI.onResult(integer);
    }

    public Usuario getUsr() {

        return usr;
    }


}
