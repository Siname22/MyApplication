package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button log;
    Button register;
    EditText name;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.EmailAddress);
        name=findViewById(R.id.txtName);
        password=findViewById(R.id.Password);

        log=findViewById(R.id.login);
        register=findViewById(R.id.register);



    }
    void activarButtons()
    {
        if (email.getText().equals("") | name.getText().equals("")|password.getText().equals(""))
        {
            log.setEnabled(true);
            register.setEnabled(false);
        }
    }

    public void Register(View view){

        Intent registro = new Intent(this, Register.class);
        startActivity(registro);
    }
    public void Login(View view){

        Intent log = new Intent(this, Menu.class);
        startActivity(log);
    }
}