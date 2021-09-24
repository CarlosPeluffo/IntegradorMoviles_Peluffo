package com.peluffo.inmobiliariapeluffo.ui.contrato;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peluffo.inmobiliariapeluffo.modelo.Contrato;
import com.peluffo.inmobiliariapeluffo.modelo.Pago;
import com.peluffo.inmobiliariapeluffo.request.ApiClient;

import java.util.List;

public class PagoViewModel extends ViewModel {
    private MutableLiveData<List<Pago>> pagosM;

    public LiveData<List<Pago>> getPagosM() {
        if(pagosM == null){
            pagosM = new MutableLiveData<>();
        }
        return pagosM;
    }

    public void cargarPagos(Bundle bundle){
        ApiClient api = ApiClient.getApi();
        Contrato contrato = (Contrato) bundle.getSerializable("pagos");
        pagosM.setValue(api.obtenerPagos(contrato));
    }
}