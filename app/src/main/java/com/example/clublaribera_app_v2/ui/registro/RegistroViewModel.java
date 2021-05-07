package com.example.clublaribera_app_v2.ui.registro;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.clublaribera_app.MainActivity;
import com.example.clublaribera_app.R;
import com.example.clublaribera_app.modelos.Msj;
import com.example.clublaribera_app.modelos.Usuario;
import com.example.clublaribera_app.request.ApiClient;
import com.example.clublaribera_app.ui.login.LoginActivity;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class RegistroViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Bitmap> foto;
    private MutableLiveData<String> error;
    private String msj1 = "Ya existe un usuario registrado con el email elegido!";
    private String msj3 = "Debe completar todos los campos";
    private ArrayList<String> permitidos;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getError(){
        if (error==null){
            error = new MutableLiveData<>();
        }
        return error;
    }

    public LiveData<Bitmap> getFoto(){
        if (foto == null){
            foto = new MutableLiveData<>();
        }
        return foto;
    }

    public void registrarUsuario(Usuario u, Bitmap bitmap){
        if (u.getNombre().length() != 0 && u.getApellido().length() != 0 && u.getTelefono().length() != 0
                && u.getEmail().length() != 0 && u.getDni().length() != 0
                && u.getRolId() != 0){

            if(bitmap != null){u.setFotoPerfil(encodeImage(bitmap));}

            Gson gson = new Gson();
            String JSON = gson.toJson(u);

            //u.setClave("4321");

            if (u.getRolId() == 4){
                u.setEstado(true);
                u.setGrupoId(11);
            }

            Call<Msj> dato = ApiClient.getMyApiClient().registrarUsuario(u);
            dato.enqueue(new Callback<Msj>() {
                @Override
                public void onResponse(Call<Msj> call, Response<Msj> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(context, response.body().getMensaje(), Toast.LENGTH_LONG).show();

                        Intent logeo = new Intent(context, LoginActivity.class);
                        logeo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(logeo);
                    }
                    else {
                        error.postValue(msj1);
                    }
                }

                @Override
                public void onFailure(Call<Msj> call, Throwable t) {
                    Log.d("salida",t.getMessage());
                    t.printStackTrace();
                }
            });

        } else {
            error.setValue(msj3);
        }
    }

    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    public void cargarImagen(int requestCode, int resultCode, @Nullable Intent data){
        if (resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            InputStream imageStream = null;

            try {
                imageStream = context.getContentResolver().openInputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

            //Rutina para optimizar la foto,
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.PNG,100, baos);
            foto.setValue(selectedImage);
        }
    }
}
