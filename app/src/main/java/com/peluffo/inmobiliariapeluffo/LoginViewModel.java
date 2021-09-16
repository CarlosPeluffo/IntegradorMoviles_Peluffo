package com.peluffo.inmobiliariapeluffo;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peluffo.inmobiliariapeluffo.modelo.Propietario;
import com.peluffo.inmobiliariapeluffo.request.ApiClient;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> visibleM;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }


    public LiveData<Integer> getVisibleM(){
        if(visibleM == null){
            visibleM = new MutableLiveData<>();
        }
        return visibleM;
    }

    public void iniciarSesion(String m, String c){
        ApiClient api = ApiClient.getApi();
        Propietario p = api.login(m,c);
        if(p != null) {
            visibleM.setValue(View.INVISIBLE);
            Intent intent = new Intent(context, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("propietario", p);
            intent.putExtra("propietario", bundle);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else{
            visibleM.setValue(View.VISIBLE);
        }
    }
}
