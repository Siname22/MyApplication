package com.example.myapplication;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MenuEmulador extends AppCompatActivity {
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_emulador);
        img = findViewById(R.id.sonido3);

        if (Menu.conSonido) {
            img.setImageResource(R.drawable.sonido);
        } else {
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

    public static boolean isDownloadManagerAvailable(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return true;
        }
        return false;
    }

    public void Emulador(View view){
        String url = "https://drive.google.com/file/d/170RDXrQ_O_FpojAygPUqd1Ch2FXSkkW2/view?usp=sharing";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Emulador para juegos Pokémon");
        request.setTitle("Emulador");
// in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");

// get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    public void Rojo(View view){
        String url = "https://drive.google.com/file/d/170RDXrQ_O_FpojAygPUqd1Ch2FXSkkW2/view?usp=sharing";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("ROM Pokémon Rojo");
        request.setTitle("Pokémon Rojo");
// in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");

// get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    public void Amarillo(View view){
        String url = "https://drive.google.com/file/d/170RDXrQ_O_FpojAygPUqd1Ch2FXSkkW2/";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("ROM Pokémon Amarillo");
        request.setTitle("Pokémon Amarillo");
// in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");

// get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    public void plata(View view){
        String url = "https://drive.google.com/file/d/170RDXrQ_O_FpojAygPUqd1Ch2FXSkkW2/";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("ROM Pokémon Plata");
        request.setTitle("Pokémon Plata");
// in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");

// get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

}