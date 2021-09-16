package com.peluffo.inmobiliariapeluffo.ui.contrato;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContratoViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ContratoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Contrato fragment");
    }

    public LiveData<String> getmText() {
        return mText;
    }
}
