package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class itm_pkm extends AppCompatActivity {

    //variables con imagen y con drawable
    String nombre;
    String imgPokemon;

    public itm_pkm(String nombre,String imgPokemon) {
        this.nombre = nombre;
        this.imgPokemon = imgPokemon;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImgPokemon() {
        return imgPokemon;
    }

}
