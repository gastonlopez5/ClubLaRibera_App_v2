package com.example.clublaribera_app_v2.request;

import android.util.Log;

import com.example.clublaribera_app_v2.modelos.Login;
import com.example.clublaribera_app_v2.modelos.Msj;
import com.example.clublaribera_app_v2.modelos.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.example.clublaribera_app_v2.MainActivity.PATH;

public class ApiClient {
    private static  MyApiInterface myApiInteface;

    public static MyApiInterface getMyApiClient(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH + "/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInteface;
    }

    public interface MyApiInterface {

        @POST("Usuarios/registrar")
        Call<Msj> registrarUsuario(@Body Usuario user);

        @POST("Usuarios/login")
        Call<String> login(@Body Login login);

        @POST("Usuarios/recuperarpass")
        Call<Msj> recuperarPass(@Body String email);
    }
}
