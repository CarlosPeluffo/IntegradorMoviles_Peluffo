package com.peluffo.inmobiliariapeluffo.ui.inquilino;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InquilinoViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public InquilinoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Inquilino fragment");
    }

    public LiveData<String> getmText() {
        return mText;
    }
}
