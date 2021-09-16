package com.peluffo.inmobiliariapeluffo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.peluffo.inmobiliariapeluffo.modelo.Propietario;

public class Login extends AppCompatActivity {
    private EditText mail, contraseña;
    private Button btLogin;
    private TextView tvMensaje;
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciarVista();
        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        loginViewModel.getVisibleM().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvMensaje.setVisibility(integer);
            }
        });
    }
    private void iniciarVista(){
        btLogin = findViewById(R.id.btLogin);
        tvMensaje = findViewById(R.id.tvMensaje);
        mail = findViewById(R.id.etMail);
        contraseña = findViewById(R.id.etPassword);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.iniciarSesion(mail.getText().toString(),
                        contraseña.getText().toString());
            }
        });
    }
}