package com.peluffo.inmobiliariapeluffo.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.peluffo.inmobiliariapeluffo.modelo.*;

import java.time.LocalDateTime;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public class ApiInmobiliaria {
    private static final String URLBASE ="http://192.168.1.105:5001/api/";
    private static PostInterface postInterface;

    public static PostInterface getMyApiClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        postInterface = retrofit.create(PostInterface.class);
        return postInterface;
    }
    public interface PostInterface{
        @FormUrlEncoded
        @POST("Propietarios/login")
        Call<String> login(@Field("Usuario") String usuario, @Field("Clave") String clave);

        @GET("Propietarios")
        Call<Propietario> perfil(@Header("Authorization") String Token);

        @PUT("Propietarios")
        Call<Propietario> actualizarProp(@Header("Authorization") String token, @Body Propietario p);

        @GET("Inmuebles")
        Call<List<Inmueble>> inmuebles(@Header("Authorization") String token);

        @PUT("Inmuebles/{id}")
        Call<Inmueble> disponibilidad(@Header("Authorization") String token, @Path("id") int idIn);

        @POST("Inmuebles")
        Call<Inmueble> crearInmueble(@Header("Authorization") String token, @Body Inmueble inmueble);

        @GET("Contratos")
        Call<List<Contrato>> contratos(@Header("Authorization") String token);

        @GET("Contratos/{id}")
        Call<Contrato> unContrato(@Header("Authorization") String token, @Path("id") int idC);

        @GET("Pagos/{id}")
        Call<List<Pago>> pagos(@Header("Authorization") String token, @Path("id") int idC);
    }
}
