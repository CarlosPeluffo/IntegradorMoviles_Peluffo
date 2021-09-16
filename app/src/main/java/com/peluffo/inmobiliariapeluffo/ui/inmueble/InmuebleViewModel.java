package com.peluffo.inmobiliariapeluffo.ui.inmueble;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InmuebleViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public InmuebleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Inmueble fragment");
    }

    public LiveData<String> getmText() {
        return mText;
    }
}
