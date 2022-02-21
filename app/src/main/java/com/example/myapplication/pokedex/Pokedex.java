package com.example.myapplication.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Menu;
import com.example.myapplication.R;
import com.example.myapplication.pokedex.api.PokemonAPIService;
import com.example.myapplication.pokedex.api.PokemonResult;
import com.example.myapplication.pokedex.api.PokemonResultItem;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pokedex extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private RecyclerView recyclerView;
    ArrayList<PokemonResultItem> pokemons;
    ImageButton img;

    SearchView searchView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
        img = findViewById(R.id.sonidopokedex);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Es un paso exclusivo de recylcerView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        //Implementacion extra: Inclusion de divisor para el listado
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.dividir_color));
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Llamada a retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();

        PokemonAPIService pokemonAPIService = retrofit.create(PokemonAPIService.class);
        Call<PokemonResult> call = pokemonAPIService.getPokemonsResulItem();
        call.enqueue(new Callback<PokemonResult>() {
            @Override
            public void onResponse(Call<PokemonResult> call, Response<PokemonResult> response) {
                ArrayList<Itm_pkm> items = new ArrayList<>();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        pokemons = response.body().getResults();
                        for (int i = 0; i < pokemons.size(); i++) {
                            items.add(new Itm_pkm(pokemons.get(i).getName(), pokemons.get(i).getUrl()));
                        }
                    }
                    // specify an adapter with the list to show
                    adapter = new CustomAdapter(items, Pokedex.this);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnClicklListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("Lo que cojo", String.valueOf(recyclerView.getChildAdapterPosition(v)));
                            Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(adapter.getItm_pkm().get(recyclerView.getChildAdapterPosition(v)).getUrl())
                                .addConverterFactory(GsonConverterFactory.create(
                                        new GsonBuilder().serializeNulls().create()
                                ))
                                .build();

                            Log.i("url", adapter.getItm_pkm().get(recyclerView.getChildAdapterPosition(v)).getUrl());

                            PokemonAPIService pokemonAPIService = retrofit.create(PokemonAPIService.class);
                            Call<JsonObject> call = pokemonAPIService.getPokemonInfo();
                            call.enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    try {
                                        Intent i = new Intent(Pokedex.this, PokemonInfo.class);
                                        Toast.makeText(getApplicationContext(),"Seleccion: " +
                                                adapter.getItm_pkm().get(recyclerView.getChildAdapterPosition(v)).
                                                        getNombre(), Toast.LENGTH_SHORT).show();
                                        String json = response.body().toString();

                                        // Como algunos pokemon fallan sin ningún tipo de traza de error
                                        // intento esto para ver si es por el tamaño del json

                                        JSONObject jsonObjectOriginal = new JSONObject(json);
                                        JSONObject jsonObject = new JSONObject(json);
                                        jsonObject.put("weight", jsonObjectOriginal.getInt("weight"));
                                        jsonObject.put("abilities", jsonObjectOriginal.getJSONArray("abilities"));
                                        jsonObject.put("stats", jsonObjectOriginal.getJSONArray("stats"));
                                        jsonObject.put("sprites", jsonObjectOriginal.getJSONObject("sprites"));

                                        Log.i("JSON", jsonObject.toString());

                                        i.putExtra("datos", jsonObject.toString());
                                        startActivity(i);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {
                                    Log.d("Error", t.toString());
                                }
                            });
                        }
                    });
                } else {
                    Log.d("Error", "Something happened");
                }
            }

            @Override
            public void onFailure(Call<PokemonResult> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
        searchView.setOnQueryTextListener(this) ;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (Menu.conSonido) {
            img.setImageResource(R.drawable.sonido);
        } else {
            img.setImageResource(R.drawable.sinsonido);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
    public void silencio(View view) {
        if (Menu.conSonido) {
            img.setImageResource(R.drawable.sinsonido);
            Menu.MediaPlayer.pause();
            Menu.conSonido = false;
        } else {

            img.setImageResource(R.drawable.sonido);
            Menu.MediaPlayer.start();
            Menu.conSonido = true;
        }

    }
}