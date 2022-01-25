package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class menumapas extends AppCompatActivity {

    ImageButton img;
    boolean conSonido = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menumapas);
        img = findViewById(R.id.sonido1);
    }
    public void silencio(View view){
        if(com.example.MainActivity.conSonido)
        {

            img.setImageResource(R.drawable.sinsonido);
            com.example.pokeinicio.MainActivity.MediaPlayer.pause();
            com.example.pokeinicio.MainActivity.conSonido = false;
        }
        else
        {

            img.setImageResource(R.drawable.sonido);
            com.example.pokeinicio.MainActivity.MediaPlayer.start();
            com.example.pokeinicio.MainActivity.conSonido = true;
        }

    }
}