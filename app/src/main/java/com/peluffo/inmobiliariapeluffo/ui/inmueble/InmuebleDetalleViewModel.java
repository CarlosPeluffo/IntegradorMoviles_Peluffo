package com.peluffo.inmobiliariapeluffo.ui.inmueble;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;
import com.peluffo.inmobiliariapeluffo.request.ApiClient;

public class InmuebleDetalleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> inmuebleM;
    private Inmueble i;


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
    public void guardarEstado(Boolean b){
        ApiClient api = ApiClient.getApi();
        i.setEstado(b);
        api.actualizarInmueble(i);
    }
}
