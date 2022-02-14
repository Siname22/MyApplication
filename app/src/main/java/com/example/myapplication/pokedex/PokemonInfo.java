package com.example.myapplication.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonInfo extends AppCompatActivity
{

    TextView nombrePokemon;
    TextView codePokemon;
    ImageView imagenPokemon;
    TabItem statsTab;
    TabLayout tabLayout;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info);
        try {
            consumirAPI();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void consumirAPI() throws JSONException {
        String pkmName;
        int pesoPokemon = 0;
        String hp;
        nombrePokemon = findViewById(R.id.namePokemonInfo);
        codePokemon = findViewById(R.id.codePokemon);
        imagenPokemon = findViewById(R.id.imagePokemonInfo);
        tabLayout = findViewById(R.id.tabLayout);
        statsTab = tabLayout.findViewById(R.id.statsPokemon);
        manager = getSupportFragmentManager();
        Intent i = getIntent();
        String  datos = i.getStringExtra("datos");
        Log.i("datos",datos );

        JSONObject infoPokemon = null;
        infoPokemon = new JSONObject(datos);
        pkmName = infoPokemon.getString("name");
        pesoPokemon = infoPokemon.getInt("weight");
        //hp = infoPokemon.getJSONObject()

        //int statsHp = infoPokemon.get("stats").getAsJsonObject().get("base_stat").getAsInt();
        JSONArray arrayStats = infoPokemon.getJSONArray("stats");
        for (int j = 0; j < arrayStats.length(); j++) {
            System.out.println(arrayStats.getJSONObject(j).getInt("base_stat"));
            System.out.println(arrayStats.getJSONObject(j).getJSONObject("stat").getString("name"));
        }

        Log.i("peso", String.valueOf(pesoPokemon));
        //Log.i("hp", hp);
        //Log.i("stats", String.valueOf(statsHp));
        Picasso.get().load(
                infoPokemon.getJSONObject("sprites").
                        getJSONObject("other").
                        getJSONObject("official-artwork").getString("front_default")
        ).into(imagenPokemon);


        nombrePokemon.setText(pkmName);

        Log.i("nombre", pkmName);

        codePokemon.setText(infoPokemon.getString("id"));
        Log.i("codigo", infoPokemon.getString("id"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0)
                {
                    System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    FragmentTransaction transaction = manager.beginTransaction();
                    PokemonFragment fragment = PokemonFragment.newInstance(pkmName);
                    transaction.replace(R.id.fragmentContainerView, fragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition() == 1)
                {
                    System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    FragmentTransaction transaction = manager.beginTransaction();
                    PokemonStatsFragment fragment = new PokemonStatsFragment();
                    transaction.replace(R.id.fragmentContainerView, fragment).commit();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition() == 2)
                {
                    System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    FragmentTransaction transaction = manager.beginTransaction();
                    //PokemonHabilidadesFragment fragment = new PokemonHabilidadesFragment();
                    //transaction.replace(R.id.fragmentContainerView, fragment).commit();
                }
            }
        });
    }



}