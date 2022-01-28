package com.example.myapplication;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuEmulador extends AppCompatActivity {
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_emulador);
        img = findViewById(R.id.sonido3);

        if(Menu.conSonido)
        {
            img.setImageResource(R.drawable.sonido);
        }
        else
        {
            img.setImageResource(R.drawable.sinsonido);
        }

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