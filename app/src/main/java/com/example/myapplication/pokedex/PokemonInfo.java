package com.example.myapplication.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

public class PokemonInfo extends AppCompatActivity {

    TextView nombrePokemon;
    TextView codePokemon;
    ImageView imagenPokemon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info);
        nombrePokemon = findViewById(R.id.namePokemonInfo);
        codePokemon = findViewById(R.id.codePokemon);
        imagenPokemon = findViewById(R.id.imagePokemonInfo);


        Intent i = getIntent();
        String  datos = i.getStringExtra("datos");
        Log.i("datos",datos );

        JsonObject infoPokemon = JsonParser.parseString(datos).getAsJsonObject();
        infoPokemon.get("sprites").getAsJsonObject().get("other").getAsJsonObject().
                get("official-artwork").getAsJsonObject().get("front_default").getAsString();

        Log.i("imagen", infoPokemon.get("sprites").getAsJsonObject().get("other").
                getAsJsonObject().get("official-artwork").getAsJsonObject().get("front_default").
                getAsString());

        Picasso.get().load(infoPokemon.get("sprites").getAsJsonObject().get("other").
                getAsJsonObject().get("official-artwork").getAsJsonObject().get("front_default").
                getAsString()).into(imagenPokemon);
        nombrePokemon.setText(infoPokemon.get("name").getAsString());
        Log.i("nombre", infoPokemon.get("name").getAsString());

        codePokemon.setText(infoPokemon.get("id").getAsString());
        Log.i("codigo", infoPokemon.get("id").getAsString());

    }
}