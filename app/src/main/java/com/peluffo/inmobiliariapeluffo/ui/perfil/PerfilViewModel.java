package com.peluffo.inmobiliariapeluffo.ui.perfil;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.peluffo.inmobiliariapeluffo.MainActivity;
import com.peluffo.inmobiliariapeluffo.modelo.Propietario;
import com.peluffo.inmobiliariapeluffo.request.ApiInmobiliaria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<String> mText;
    private MutableLiveData<Boolean> estadoM;
    private MutableLiveData<Propietario> propietarioM;
    private MutableLiveData<String> textB;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Perfil");
        this.context = application.getApplicationContext();
    }


    public LiveData<Boolean> getEstadoM() {
        if(estadoM == null){
            estadoM = new MutableLiveData<>();
        }
        return estadoM;
    }

    public LiveData<String> getmText() {
        return mText;
    }

    public LiveData<Propietario> getPropietarioM(){
        if(propietarioM == null){
            propietarioM = new MutableLiveData<>();
        }
        return propietarioM;
    }

    public LiveData<String> getTextB() {
        if(textB == null){
            textB = new MutableLiveData<>();
        }
        return textB;
    }

    public void cargarProp(){
        SharedPreferences sp = context.getSharedPreferences("Usuarios", 0);
        String token = sp.getString("token", "no token");
        Call<Propietario> call = ApiInmobiliaria.getMyApiClient().perfil(token);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()) {
                    propietarioM.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

            }
        });
    }
    public void cambiarEstado(String t, final Propietario p){
        if(t.equals("Editar")){
        estadoM.setValue(true);
        textB.setValue("Guardar");
        }else {
            SharedPreferences sp = context.getSharedPreferences("Usuarios", 0);
            String token = sp.getString("token", "no token");
            Call<Propietario> callAct = ApiInmobiliaria.getMyApiClient().actualizarProp(token, p);
            callAct.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(context, "Guardado con éxito", Toast.LENGTH_LONG).show();
                        estadoM.postValue(false);
                        textB.postValue("Editar");
                        propietarioM.setValue(response.body());
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Log.d("Salida", t.getMessage()+"Throwable");
                }
            });
        }
    }
}
