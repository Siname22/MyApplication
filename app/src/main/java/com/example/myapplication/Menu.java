package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.pokedex.Pokedex;

import java.sql.SQLOutput;

public class Menu extends AppCompatActivity {

ImageButton img;
 public static boolean conSonido = true;
 public static MediaPlayer MediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

     MediaPlayer = MediaPlayer.create(Menu.this,R.raw.pokeinicio);
        MediaPlayer.setLooping(true);
        MediaPlayer.start();
        img = findViewById(R.id.imageButton7);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(conSonido)
        {
            img.setImageResource(R.drawable.sonido);
        }
        else
        {
            System.out.println("MENU SIN SONIDO");
            img.setImageResource(R.drawable.sinsonido);
        }
    }

    public void BAyuda(View view){
        Intent ayuda = new Intent(this, Ayuda.class);
        startActivity(ayuda);
    }

    public void Poke(View view){
        Intent poke = new Intent(this, Pokedex.class);
        startActivity(poke);
    }

    public void BMapas(View view){
        Intent mapas = new Intent(this, menumapas.class);
        startActivity(mapas);
    }
    public void BEmulador(View view){
        Intent emulador = new Intent(this, MenuEmulador.class);
        startActivity(emulador);
    }

    public void BMochila(View view){
        Intent mochila = new Intent(this, MenuMochila.class);
        startActivity(mochila);
    }

    public void silencio(View view){
        if(conSonido)
        {
            img.setImageResource(R.drawable.sinsonido);
            MediaPlayer.pause();
            conSonido = false;
        }
        else
        {

            img.setImageResource(R.drawable.sonido);
            MediaPlayer.start();
            conSonido = true;
        }

    }


}