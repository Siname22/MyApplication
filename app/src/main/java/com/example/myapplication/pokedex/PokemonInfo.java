package com.example.myapplication.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;

public class PokemonInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info);
        Intent i = getIntent();
        int posicion = i.getIntExtra("posicion", 0);
        https://youtu.be/v0of23TxIKc
    }
}