package com.peluffo.inmobiliariapeluffo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity {
    private EditText mail, contraseña;
    private Button btLogin;
    private TextView tvMensaje;
    private LoginViewModel loginViewModel;
    private ImageView ivLogin;
    private SensorManager sensorManager;
    private LeerSensor leerSensor;
    private List<Sensor> listaSensores;
    //private Llamada llamada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciarVista();
        leerSensor = new LeerSensor();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        loginViewModel.getVisibleM().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvMensaje.setVisibility(integer);
            }
        });
        loginViewModel.getEstadoM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:466624542742"));
                startActivity(i);
                Toast.makeText(Login.this, "Llamando Inmobiliaria", Toast.LENGTH_LONG).show();
            }
        });
    }

  /*  @Override
    protected void onResume() {
        super.onResume();
        this.llamada = new Llamada();
        loginViewModel.getEstadoM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d("salida", "observer");
                registerReceiver(llamada, new IntentFilter("android.hardware.usb.action.USB_STATE"));
            }
        });
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(leerSensor, listaSensores.get(0), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(leerSensor);
    }

    private void iniciarVista(){
        ivLogin = findViewById(R.id.ivLogin);
        btLogin = findViewById(R.id.btLogin);
        tvMensaje = findViewById(R.id.tvMensaje);
        mail = findViewById(R.id.etMail);
        contraseña = findViewById(R.id.etPassword);
        ivLogin.setImageResource(R.drawable.logo);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.iniciarSesion(mail.getText().toString(),
                        contraseña.getText().toString());
            }
        });
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1000);
        }
    }
    private class LeerSensor implements SensorEventListener{
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            loginViewModel.sensorG(sensorEvent.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
}