package com.example.myapplication.pokedex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonInfo extends AppCompatActivity
{
    TextView nombrePokemon;
    TextView codePokemon;
    ImageView imagenPokemon;

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

    @SuppressLint("LongLogTag")
    void consumirAPI() throws JSONException {
        // Se recuperan los datos enviados
        Intent intent = getIntent();
        String datos = intent.getStringExtra("datos");

        // Se convierten los datos a JSON

        JSONObject infoPokemon = new JSONObject(datos);
        String pkmName = infoPokemon.getString("name");

        // Se enlazan los controles
        nombrePokemon = findViewById(R.id.namePokemonInfo);
        codePokemon = findViewById(R.id.codePokemon);
        imagenPokemon = findViewById(R.id.imagePokemonInfo);

        // Se carga la imagen del pokemon
        Picasso.get().load(
                infoPokemon.getJSONObject("sprites").
                        getJSONObject("other").
                        getJSONObject("official-artwork").getString("front_default")
        ).into(imagenPokemon);
        // Datos del pokemom
        nombrePokemon.setText(pkmName);
        codePokemon.setText(infoPokemon.getString("id"));

        ViewPager2 mViewPager2 = findViewById(R.id.mViewPager2);
        TabLayout mTabLayout = findViewById(R.id.mTabLayout);
        // Se cargan los fragmentos
        List<Fragment> fragmentList = new ArrayList<>();
         String[] titles = {"INFO", "STATS", "HABILIDADES"};
        for (int i = 0; i < titles.length ; i++) {
            fragmentList.add(PokemonFragment.newInstance(i, datos));
        }
        mViewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragmentList.get(position);
            }
            @Override
            public int getItemCount() {
                return fragmentList.size();
            }
        });
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        });
        tabLayoutMediator.attach();
    }
}