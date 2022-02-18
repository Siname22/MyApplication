package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button log;
    EditText name;
    static EditText email;
    EditText password;
    boolean nValido = false;
    boolean eValido = false;
    boolean pValido = false;
    static String nombreIntroducido;
    static String psswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.EmailAddress);
        name=findViewById(R.id.txtName);
        password=findViewById(R.id.Password);

        log=findViewById(R.id.login);
        log.setEnabled(false);



        name.addTextChangedListener(new TextWatcher() {
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
        });
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

    public void Login(View view)
    {
        Intent intent=new Intent(this,Menu.class);
        startActivity(intent);
    }

}