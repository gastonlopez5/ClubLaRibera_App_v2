package com.example.clublaribera_app_v2.ui.forgotpass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clublaribera_app.R;
import com.example.clublaribera_app.ui.login.LoginViewModel;

public class ForgotPassActivity extends AppCompatActivity {

    private Button enviar;
    private TextView email;
    private ForgotPassViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ForgotPassViewModel.class);

        enviar = findViewById(R.id.button_send);
        email = findViewById(R.id.et_email);

        vm.getMsg1().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.recuperarPass(email.getText().toString());
            }
        });
    }
}