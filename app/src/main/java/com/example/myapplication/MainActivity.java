package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

ImageButton img;
 public static boolean conSonido = true;
 public static MediaPlayer MediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     MediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.pokeinicio);
        MediaPlayer.setLooping(true);
        MediaPlayer.start();
        img = findViewById(R.id.imageButton7);

        //BUSCAR COMO SE HACE UN LISTENER Y METER DENTRO DE DICHO LISTENER EL METODO PARA SILENCIAR
        //CON ESTO DEBERIA VAER, SI NO PROBAR CON LOS ONRESUME Y DEMAS
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        MediaPlayer.start();
    }*/

    /*@Override
    protected void onPause() {
        super.onPause();
        MediaPlayer.pause();
    }*/


    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayer.stop();
        MediaPlayer.release();
    }*/

    public void BAyuda(View view){
        Intent ayuda = new Intent(this, Ayuda.class);
        startActivity(ayuda);
    }

    public void Poke(View View){
        Toast.makeText(this, "Te llevaré a la Pokédex", Toast.LENGTH_SHORT).show();
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