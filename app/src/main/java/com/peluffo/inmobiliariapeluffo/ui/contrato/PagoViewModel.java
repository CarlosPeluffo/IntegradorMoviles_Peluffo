package com.peluffo.inmobiliariapeluffo.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.peluffo.inmobiliariapeluffo.modelo.Contrato;
import com.peluffo.inmobiliariapeluffo.modelo.Pago;
import com.peluffo.inmobiliariapeluffo.request.ApiInmobiliaria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pago>> pagosM;
    private Context context;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<List<Pago>> getPagosM() {
        if(pagosM == null){
            pagosM = new MutableLiveData<>();
        }
        return pagosM;
    }

    public void cargarPagos(Bundle bundle){
        Contrato contrato = (Contrato) bundle.getSerializable("pagos");
        SharedPreferences sp = context.getSharedPreferences("Usuarios", 0);
        String token = sp.getString("token", "no token");
        Call<List<Pago>> call = ApiInmobiliaria.getMyApiClient().pagos(token, contrato.getId());
        call.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    pagosM.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                    Log.d("Salida", t.getMessage());
            }
        });
    }
}