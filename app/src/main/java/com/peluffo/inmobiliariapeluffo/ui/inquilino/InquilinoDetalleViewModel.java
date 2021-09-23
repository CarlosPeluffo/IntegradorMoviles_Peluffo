package com.peluffo.inmobiliariapeluffo.ui.inquilino;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peluffo.inmobiliariapeluffo.modelo.Inquilino;

public class InquilinoDetalleViewModel extends ViewModel {
    private MutableLiveData<Inquilino> inquilinoM;
    private Inquilino inquilino;

    public LiveData<Inquilino> getInquilino() {
        if(inquilinoM == null){
            inquilinoM = new MutableLiveData<>();
        }
        return inquilinoM;
    }
    public void cargar(Bundle b){
        inquilino = (Inquilino) b.getSerializable("inquilino");
        inquilinoM.setValue(inquilino);
    }
}