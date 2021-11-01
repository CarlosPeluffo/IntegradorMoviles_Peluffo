package com.peluffo.inmobiliariapeluffo;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.peluffo.inmobiliariapeluffo.request.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> visibleM;
    private MutableLiveData<Boolean> estadoM;
    private Context context;
    private int activador = 0;

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

    public LiveData<Boolean> getEstadoM() {
        if(estadoM == null){
            estadoM = new MutableLiveData<>();
        }
        return estadoM;
    }

    public void iniciarSesion(String m, String c){
        Call<String> call = ApiInmobiliaria.getMyApiClient().login(m,c);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    visibleM.postValue(View.INVISIBLE);
                    SharedPreferences sharedPreferences = context.getSharedPreferences("Usuarios", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", "Bearer " + response.body());
                    if (editor.commit()) {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }else{
                    visibleM.postValue(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Salida", t.getMessage()+"");
            }
        });
    }
    public void sensorG(float x){
        if(x > 1 || x < -1){
            activador++;
        }
        if(activador > 20){
            activador = 0;
            estadoM.setValue(true);
        }
    }
}
