package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class itm_pkm extends AppCompatActivity {

    //variables con imagen y con drawable
    String nombre;
    int imgPokemon;

    public itm_pkm(String nombre) {
        this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }

    public int getImgPokemon() {
        return imgPokemon;
    }

}
