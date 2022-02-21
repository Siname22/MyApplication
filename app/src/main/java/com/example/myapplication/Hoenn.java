package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Hoenn extends AppCompatActivity {

    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoenn);

        img = findViewById(R.id.imageButton);

        if(Menu.conSonido)
        {
            img.setImageResource(R.drawable.sonido);
        }
        else
        {
            img.setImageResource(R.drawable.sinsonido);
        }
    }

    public void silencio(View view) {
        if (Menu.conSonido) {

            img.setImageResource(R.drawable.sinsonido);
            Menu.MediaPlayer.pause();
            Menu.conSonido = false;
        } else {

            img.setImageResource(R.drawable.sonido);
            Menu.MediaPlayer.start();
            Menu.conSonido = true;
        }
    }
}