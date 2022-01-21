package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Pokedex extends AppCompatActivity {

    URL urlaaConsumir;
    static ArrayList<String> urls = new ArrayList<>();
    ArrayList<itm_pkm> pokemons = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    //static ArrayList<String> urlsImg = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        recyclerView = findViewById(R.id.recyclerView);

        //Es un paso exclusivo de recylcerView
        recyclerView.setLayoutManager(new GridLayoutManager(this,3,
                LinearLayoutManager.VERTICAL,false));

        //Implementacion extra: Inclusion de divisor para el listado
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL );
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.dividir_color));
        recyclerView.addItemDecoration(dividerItemDecoration);
        DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL );
        dividerItemDecoration2.setDrawable(getDrawable(R.drawable.dividir_color));
        recyclerView.addItemDecoration(dividerItemDecoration);




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
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject pkm = array.getJSONObject(i);
                        nombres.add(pkm.getString("name"));
                        System.out.println(nombres.get(i));
                        urls.add(pkm.getString("url"));
                        System.out.println(urls.get(i));


                        //pokemons.add(new itm_pkm(nombres.get(i), urls.get(i)));
                    }
                } catch (IOException | JSONException ioException) {
                    ioException.printStackTrace();
                }
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


            // AdaptadorPersonalizado adaptadorPersonalizado = new AdaptadorPersonalizado(pokemons,
            //this);

            //listView.setAdapter(adaptadorPersonalizado);


        }).start();
    }
}