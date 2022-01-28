package com.example.myapplication.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.pokedex.api.PokemonAPIService;
import com.example.myapplication.pokedex.api.PokemonResult;
import com.example.myapplication.pokedex.api.PokemonResultItem;
import com.example.myapplication.Menu;
import com.example.myapplication.R;
import com.example.myapplication.pokedex.api.PokemonAPIService;
import com.example.myapplication.pokedex.api.PokemonResult;
import com.example.myapplication.pokedex.api.PokemonResultItem;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pokedex extends AppCompatActivity {

    private String rutaImagenes;
    private RecyclerView recyclerView;
    ArrayList<PokemonResultItem> pokemons;
    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        recyclerView = findViewById(R.id.recyclerView);

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
                    CustomAdapter adapter = new CustomAdapter(items, Pokedex.this);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnClicklListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(),"Seleccion: " +
                                            pokemons.get(recyclerView.getChildAdapterPosition(view)).
                                                    getName(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Pokedex.this, PokemonInfo.class);
                            i.putExtra("posicion", pokemons.get(recyclerView.
                                    getChildAdapterPosition(view)).getUrl());
                            startActivity(i);
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

        img = findViewById(R.id.sonidopokedex);

        if (Menu.conSonido) {
            img.setImageResource(R.drawable.sonido);
        } else {
            img.setImageResource(R.drawable.sinsonido);
        }
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

        /*
        try {
            urlaaConsumir = new URL("https://pokeapi.co/api/v2/pokemon/?limit=898");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream iS = null;
                try {
                    HttpURLConnection connection = null;
                    connection = (HttpURLConnection) urlaaConsumir.openConnection();
                    iS = connection.getInputStream();
                    InputStreamReader iSR = new InputStreamReader(iS);
                    BufferedReader bf = new BufferedReader(iSR);
                    String linea = "";
                    String resultado = "";
                    while ((linea = bf.readLine()) != null) {
                        resultado += linea;
                    }
                    Log.i("RESULTADO", resultado);
                    //parsea todo el resultado en formato JSON
                    JSONObject jsonResultado = new JSONObject(resultado);
                    JSONArray array = jsonResultado.getJSONArray("results");
                    Log.i("results", array.toString());
                    String nombre;
                    String url;
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject pkm = array.getJSONObject(i);
                        Log.i("results", pkm.toString());
                        nombre = pkm.getString("name");
                        url = pkm.getString("url");
                        Log.i("name", nombre);
                        Log.i("url", url);
                        nombres.add(nombre);
                        urls.add(url);
                        pokemons.add(new itm_pkm(nombre, url));
                    }
                } catch (IOException | JSONException ioException) {
                    ioException.printStackTrace();
                }
            }
        }).start();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < nombres.size(); i++) {
                    pokemons.add(new itm_pkm(nombres.get(i), urls.get(i)));
                }
                CustomAdapter adapter = new CustomAdapter(Pokedex.this, pokemons);
                recyclerView.setAdapter(adapter);

            }
        });

    }
    */

