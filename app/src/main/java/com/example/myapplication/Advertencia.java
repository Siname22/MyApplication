package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;


public class Advertencia extends AppCompatActivity {

    Button log;
    EditText name;
    EditText password;
    static EditText email;

    String emailComprovado;
    String nameComprovado;
    String psswdComprovado;

      /*boolean nValido = false;
    boolean eValido = false;
    boolean pValido = false;
    static String nombreIntroducido;
    static String psswd;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertencia);

        email=findViewById(R.id.EmailAddress);
        name=findViewById(R.id.txtName);
        password=findViewById(R.id.Password);

        log=findViewById(R.id.login);


    }


    public void Login(View view)
    {

        if (name.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || !(email.getText().toString().contains("@")))
        {
            Toast.makeText(this, "ERROR:Invalid name, password or email", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sussecfull login", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Menu.class);
            startActivity(intent);
        }


    }
}