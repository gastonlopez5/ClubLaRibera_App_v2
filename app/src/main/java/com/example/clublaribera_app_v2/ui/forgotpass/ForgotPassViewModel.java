package com.example.clublaribera_app_v2.ui.forgotpass;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.util.MarkEnforcingInputStream;
import com.example.clublaribera_app_v2.modelos.Login;
import com.example.clublaribera_app_v2.modelos.Msj;
import com.example.clublaribera_app_v2.request.ApiClient;
import com.example.clublaribera_app_v2.ui.login.LoginActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassViewModel extends AndroidViewModel {

    private Context context;
    private String error1 = "Usuario no registrado";
    private String error2 = "Debe ingresar un email valido";
    MutableLiveData<String> msg1;

    public ForgotPassViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getMsg1(){
        if (msg1 == null){
            msg1 = new MutableLiveData<String>();
        }
        return msg1;
    }

    public void recuperarPass(String email) {
        if (email.length() != 0){

            Call<Msj> dato= ApiClient.getMyApiClient().recuperarPass(email);
            dato.enqueue(new Callback<Msj>() {
                @Override
                public void onResponse(Call<Msj> call, Response<Msj> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, response.body().getMensaje(), Toast.LENGTH_LONG).show();

                        Intent login = new Intent(context, LoginActivity.class);
                        context.startActivity(login);
                    }else{
                        msg1.postValue(error1);
                    }
                }

                @Override
                public void onFailure(Call<Msj> call, Throwable t) {

                    Log.d("salida",t.getMessage());
                    t.printStackTrace();
                }
            });
        }
        else {
            msg1.setValue(error2);
        }
    }
}
