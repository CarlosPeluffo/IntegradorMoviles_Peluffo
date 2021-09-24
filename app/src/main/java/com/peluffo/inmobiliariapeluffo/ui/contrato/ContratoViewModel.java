package com.peluffo.inmobiliariapeluffo.ui.contrato;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;
import com.peluffo.inmobiliariapeluffo.request.ApiClient;

import java.util.List;

public class ContratoViewModel extends ViewModel {
    private MutableLiveData<List<Inmueble>> lista;

    public LiveData<List<Inmueble>> getLista() {
        if(lista == null){
            lista = new MutableLiveData<>();
        }
        return lista;
    }
    public void cargarInmueblesAlquilados(){
        ApiClient api = ApiClient.getApi();
        lista.setValue(api.obtenerPropiedadesAlquiladas());
    }
}
