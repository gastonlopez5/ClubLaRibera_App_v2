package com.example.clublaribera_app_v2.ui.registro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.clublaribera_app.R;
import com.example.clublaribera_app.modelos.Grupo;
import com.example.clublaribera_app.modelos.TipoUsuario;
import com.example.clublaribera_app.modelos.Usuario;
import com.example.clublaribera_app.ui.login.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {

    private RegistroViewModel vm;
    private Spinner tipoUsuario;
    private Spinner categoria;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etTelefono;
    private EditText etEmail;
    private EditText etDni;
    private ImageButton btFoto;
    private Button btRegistro;
    private ImageView ivFoto;
    private Bitmap bitmapFoto = null;
    private Boolean bandera = true;
    private Usuario u;
    private ArrayList<TipoUsuario> lista = new ArrayList<TipoUsuario>();
    private ArrayList<Grupo> categorias = new ArrayList<Grupo>();
    private CardView cardViewCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()).create(RegistroViewModel.class);

        etNombre = findViewById(R.id.et_nombre);
        etApellido = findViewById(R.id.et_apellindo);
        etDni = findViewById(R.id.et_dni);
        etEmail = findViewById(R.id.et_email);
        etTelefono = findViewById(R.id.et_telefono);
        tipoUsuario = findViewById(R.id.spTipoUsuario);
        categoria = findViewById(R.id.spCategoria);
        btFoto = findViewById(R.id.btFoto);
        btRegistro = findViewById(R.id.button_signin);
        ivFoto = findViewById(R.id.ivFoto);
        cardViewCategorias = findViewById(R.id.cvCategorias);

        cargaSpinner();

        vm.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                fijarDatos(u, bitmapFoto);
            }
        });

        vm.getFoto().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                bitmapFoto = bitmap;
                ivFoto.setImageBitmap(bitmap);
            }
        });

        btFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent, 10);
            }
        });

        tipoUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(lista.get(position).getId() != 4 && lista.get(position).getId() != 0){
                    cardViewCategorias.setVisibility(View.VISIBLE);
                }
                else {
                    cardViewCategorias.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = new Usuario();
                u.setNombre(etNombre.getText().toString());
                u.setApellido(etApellido.getText().toString());
                u.setDni(etDni.getText().toString());
                u.setEmail(etEmail.getText().toString());
                u.setTelefono(etTelefono.getText().toString());
                u.setRolId(lista.get(tipoUsuario.getSelectedItemPosition()).getId());
                u.setGrupoId(categorias.get(categoria.getSelectedItemPosition()).getId());

                vm.registrarUsuario(u, bitmapFoto);
            }
        });
    }

    private void fijarDatos(Usuario u, Bitmap bitmap){
        etNombre.setText(u.getNombre());
        etApellido.setText(u.getApellido());
        etEmail.setText(u.getEmail());
        etTelefono.setText(u.getTelefono());
        etDni.setText(u.getDni());
        tipoUsuario.setSelection(u.getRolId());
        ivFoto.setImageBitmap(bitmap);
    }

    private void cargaSpinner(){

        lista.add(new TipoUsuario(0, "Elije tu rol dentro de la institución"));
        lista.add(new TipoUsuario(2, "Padre o Tutor"));
        lista.add(new TipoUsuario(3, "Profesor"));
        lista.add(new TipoUsuario(4, "Fan"));

        categorias.add(new Grupo(0, "Elije una categoría"));
        categorias.add(new Grupo(1, "Categoría 2004"));
        categorias.add(new Grupo(2, "Categoría 2005"));
        categorias.add(new Grupo(3, "Categoría 2007"));
        categorias.add(new Grupo(4, "Categoría 2008"));
        categorias.add(new Grupo(5, "Categoría 2009"));
        categorias.add(new Grupo(6, "Categoría 2011"));
        categorias.add(new Grupo(7, "Categoría 2012"));
        categorias.add(new Grupo(8, "Categoría 2013"));

        ArrayAdapter<TipoUsuario> adapter = new ArrayAdapter<TipoUsuario>(this, R.layout.support_simple_spinner_dropdown_item, lista);
        tipoUsuario.setAdapter(adapter);
        ArrayAdapter<Grupo> adapterCategoria = new ArrayAdapter<Grupo>(this, R.layout.support_simple_spinner_dropdown_item, categorias);
        categoria.setAdapter(adapterCategoria);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        vm.cargarImagen(requestCode,resultCode,data);
    }


}