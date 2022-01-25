package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuEmulador extends AppCompatActivity {
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_emulador);
        img = findViewById(R.id.sonido3);
    }

    public void silencio(View view){
        if(com.example.pokeinicio.MainActivity.conSonido)
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