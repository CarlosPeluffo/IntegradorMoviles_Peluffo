package com.peluffo.inmobiliariapeluffo.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;
import com.peluffo.inmobiliariapeluffo.request.ApiInmobiliaria;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleCrearViewModel extends AndroidViewModel {
    private MutableLiveData<byte []> byteM;
    private MutableLiveData<Inmueble> inmuebleM;
    private Context context;

    public InmuebleCrearViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<byte[]> getByteM() {
        if(byteM == null){
            byteM = new MutableLiveData<>();
        }
        return byteM;
    }

    public LiveData<Inmueble> getInmuebleM() {
        if(inmuebleM == null){
            inmuebleM = new MutableLiveData<>();
        }
        return inmuebleM;
    }

    public void cargarImagen(int requestCode, int resultCode, @Nullable Bitmap bit, int REQUEST_IMAGE_CAPTURE){
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bitmap bitmap = bit;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
            byte [] bytes = output.toByteArray();
            byteM.setValue(bytes);
        }else{
            Toast.makeText(context, "Debe cargar una imagen", Toast.LENGTH_LONG).show();
        }
    }
    public void crearInmueble(Inmueble inmueble){
        if(byteM.getValue() != null){
            SharedPreferences sp = context.getSharedPreferences("Usuarios", 0);
            String token = sp.getString("token", "no token");
            Call<Inmueble> call = ApiInmobiliaria.getMyApiClient().crearInmueble(token, inmueble);
            call.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, "Creado con éxito", Toast.LENGTH_LONG).show();
                        inmuebleM.postValue(response.body());
                    }else{
                        Toast.makeText(context, "Debe cargar una imagen", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Inmueble> call, Throwable t) {
                    Log.d("Salida", "Error "+ t.getMessage());
                }
            });
        }
    }
}