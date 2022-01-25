package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuMochila extends AppCompatActivity {

    ImageButton img;
    boolean conSonido = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mochila);
        img = findViewById(R.id.sonido);
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