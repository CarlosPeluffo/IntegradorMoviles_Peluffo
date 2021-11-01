package com.peluffo.inmobiliariapeluffo.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;
import com.peluffo.inmobiliariapeluffo.request.ApiInmobiliaria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmuebleM;
    private Context context;
    private Inmueble i;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Inmueble> getInmuebleM() {
        if(inmuebleM == null){
            inmuebleM = new MutableLiveData<>();
        }
        return inmuebleM;
    }
    public void cargar(Bundle b){
        i = (Inmueble) b.getSerializable("inmueble");
        inmuebleM.setValue(i);
    }
    public void guardarEstado(int id){
        SharedPreferences sp = context.getSharedPreferences("Usuarios", 0);
        String token = sp.getString("token", "no token");
        Call<Inmueble> inmuebleCall = ApiInmobiliaria.getMyApiClient().disponibilidad(token, id);
        inmuebleCall.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Guardado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {

            }
        });
    }
}
