package com.peluffo.inmobiliariapeluffo.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peluffo.inmobiliariapeluffo.modelo.Propietario;
import com.peluffo.inmobiliariapeluffo.request.ApiClient;

public class PerfilViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private MutableLiveData<Boolean> estadoM;
    private MutableLiveData<Propietario> propietarioM;
    private MutableLiveData<String> textB;

    public PerfilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Perfil");
    }

    public LiveData<Boolean> getEstadoM() {
        if(estadoM == null){
            estadoM = new MutableLiveData<>();
        }
        return estadoM;
    }

    public LiveData<String> getmText() {
        return mText;
    }

    public LiveData<Propietario> getPropietarioM(){
        if(propietarioM == null){
            propietarioM = new MutableLiveData<>();
        }
        return propietarioM;
    }

    public LiveData<String> getTextB() {
        if(textB == null){
            textB = new MutableLiveData<>();
        }
        return textB;
    }

    public void cargarProp(){
        ApiClient api = ApiClient.getApi();
        propietarioM.setValue(api.obtenerUsuarioActual());
    }
    public void cambiarEstado(String t, Propietario p){
        if(t.equals("Editar")){
        estadoM.setValue(true);
        textB.setValue("Guardar");
        }else {
            ApiClient api = ApiClient.getApi();
            api.actualizarPerfil(p);
            estadoM.setValue(false);
            textB.setValue("Editar");
        }
    }
}
