package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class menumapas extends AppCompatActivity {

    ImageButton img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menumapas);
        img = findViewById(R.id.sonido1);

        if(Menu.conSonido)
        {
            img.setImageResource(R.drawable.sonido);
        }
        else
        {
            img.setImageResource(R.drawable.sinsonido);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(Menu.conSonido)
        {
            img.setImageResource(R.drawable.sonido);
        }
        else
        {
            System.out.println("MENU SIN SONIDO");
            img.setImageResource(R.drawable.sinsonido);
        }
    }

    public void kanto(View view){
        Intent ayuda = new Intent(this,Kanto.class);
        startActivity(ayuda);
    }
    public void johto(View view){
        Intent ayuda = new Intent(this,Johto.class);
        startActivity(ayuda);
    }
    public void hoenn(View view){
        Intent ayuda = new Intent(this,Hoenn.class);
        startActivity(ayuda);
    }
    public void sinnoh(View view){
        Intent ayuda = new Intent(this,Sinnoh.class);
        startActivity(ayuda);
    }

    public void silencio(View view){
        if(Menu.conSonido)
        {

            img.setImageResource(R.drawable.sinsonido);
            Menu.MediaPlayer.pause();
            Menu.conSonido = false;
        }
        else
        {

            img.setImageResource(R.drawable.sonido);
           Menu.MediaPlayer.start();
         Menu.conSonido = true;
        }

    }
}