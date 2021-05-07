package com.example.clublaribera_app_v2.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.clublaribera_app_v2.MainActivity;
import com.example.clublaribera_app_v2.R;
import com.example.clublaribera_app_v2.ui.forgotpass.ForgotPassActivity;
import com.example.clublaribera_app_v2.ui.registro.RegistroActivity;

public class LoginActivity extends AppCompatActivity {
    private Button logeo;
    private Button registro;
    private Button recuperarPass;
    private EditText email;
    private EditText password;
    private LoginViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);

        logeo = (Button)findViewById(R.id.button_signin);
        registro = findViewById(R.id.button_signup);
        recuperarPass = findViewById(R.id.button_forgot_password);
        email = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);

        vm.getMsg1().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent logeo = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(logeo);
                finish();
            }
        });

        vm.getMsg2().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        recuperarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPass = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(forgotPass);
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(registro);
            }
        });

        logeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.validar(email.getText().toString(), password.getText().toString());
            }
        });
    }
}