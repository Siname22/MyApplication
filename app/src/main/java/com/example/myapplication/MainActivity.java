package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button log;
    EditText name;
    EditText password;
    EditText email;
    String emailComprovado;
    /*String nameComprovado;
    String psswdComprovado;

    boolean nValido = false;
    boolean eValido = false;
    boolean pValido = false;
    static String nombreIntroducido;
    static String psswd;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Aceptar(View view){
        Intent ayuda = new Intent(this, Advertencia.class);
        startActivity(ayuda);
    }

}