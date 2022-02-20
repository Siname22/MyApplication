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


        //log.setEnabled(false);



        /*name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                nombreIntroducido = editable.toString();
                if(!nombreIntroducido.equals(""))
                {
                    nValido = true;
                    if(pValido && eValido)
                    {
                        log.setEnabled(true);
                    }

                }
                else
                {
                    nValido = false;
                    log.setEnabled(false);
                }


            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String emailIntroducido = editable.toString();
                char[] caracs = emailIntroducido.toCharArray();
                eValido = false;
                log.setEnabled(false);
                for (int i = 0; i < caracs.length; i++) {
                    if(caracs[i] == '@' && !name.getText().toString().equals("") && !password.getText().toString().equals(""))
                    {
                        System.out.println("sdfsdfsd");
                        eValido = true;
                        if(pValido && nValido)
                        {
                            log.setEnabled(true);
                        }
                        else
                            {
                                log.setEnabled(false);
                            }
                        break;
                    }
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                 psswd = editable.toString();

                if(!psswd.equals(""))
                {
                    pValido = true;
                    if(eValido && nValido)
                    {
                        log.setEnabled(true);
                    }
                }
                else
                {
                    pValido = false;
                    log.setEnabled(false);
                }
            }
        });*/

    }


      /*static class activarEmail implements Runnable
    {
        EditText email=MainActivity.email;
        @Override
        public void run()
        {
            while (!nombreIntroducido.equals("")&&!psswd.equals(""))
            {
                email.setEnabled(true);
            }
        }
    }*/

    void comprovarEmail()
    {
        char[] caracs = email.getText().toString().toCharArray();
        for (int i = 0; i < caracs.length; i++) {
            if(caracs[i] == '@')
            {
                emailComprovado= Arrays.toString(caracs);
            }else {
                emailComprovado="";
            }
        }
    }

    public void Login(View view)
    {
        comprovarEmail();

        if (name.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals(""))
        {
            Toast.makeText(this, "ERROR:Invalid name, password or email", Toast.LENGTH_SHORT).show();
        }else if (!name.getText().toString().equals("") && !emailComprovado.equals("") && !password.getText().toString().equals("")){
            Toast.makeText(this, "Sussecfull login", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Menu.class);
            startActivity(intent);
        }


    }
}