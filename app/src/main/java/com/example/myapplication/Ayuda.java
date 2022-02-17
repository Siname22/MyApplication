package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ayuda extends AppCompatActivity {

    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        img = findViewById(R.id.sonidoayuda);

        if (Menu.conSonido) {
            img.setImageResource(R.drawable.sonido);
        } else {
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