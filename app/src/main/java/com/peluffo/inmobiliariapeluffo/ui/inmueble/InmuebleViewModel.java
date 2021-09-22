package com.peluffo.inmobiliariapeluffo.ui.inmueble;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;
import com.peluffo.inmobiliariapeluffo.request.ApiClient;

import java.util.List;

public class InmuebleViewModel extends ViewModel {
    private MutableLiveData<List<Inmueble>> lista;

    public LiveData<List<Inmueble>> getLista() {
        if(lista == null){
            lista = new MutableLiveData<>();
        }
        return lista;
    }
    public void cargarInmueble(){
        ApiClient api = ApiClient.getApi();
        lista.setValue(api.obtnerPropiedades());
    }
}
