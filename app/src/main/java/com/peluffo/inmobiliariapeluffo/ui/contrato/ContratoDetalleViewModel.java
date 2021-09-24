package com.peluffo.inmobiliariapeluffo.ui.contrato;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peluffo.inmobiliariapeluffo.modelo.Contrato;

public class ContratoDetalleViewModel extends ViewModel {
   private MutableLiveData<Contrato> contratoM;
   private Contrato contrato;

    public LiveData<Contrato> getContratoM() {
        if(contratoM == null){
            contratoM = new MutableLiveData<>();
        }
        return contratoM;
    }
    public void cargar(Bundle bundle){
        contrato = (Contrato) bundle.getSerializable("contrato");
        contratoM.setValue(contrato);
    }
}