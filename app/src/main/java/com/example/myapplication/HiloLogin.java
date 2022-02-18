package com.example.myapplication;


import static com.example.myapplication.MainActivity.nombreIntroducido;
import static com.example.myapplication.MainActivity.psswd;

import android.widget.EditText;

public class HiloLogin implements Runnable
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
}
