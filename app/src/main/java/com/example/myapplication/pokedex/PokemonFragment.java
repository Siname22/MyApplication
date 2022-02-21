package com.example.myapplication.pokedex;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.pokedex.api.PokemonAPIService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonFragment extends Fragment {

    private static final String ARG_POSICION = "posicion";
    private static final String ARG_DATOS = "datos";

    private int posicion;
    private String datos;

    public PokemonFragment() {   }


    public static PokemonFragment newInstance(int posicion, String datos) {
        PokemonFragment fragment = new PokemonFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSICION, posicion);
        args.putString(ARG_DATOS, datos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            posicion = getArguments().getInt(ARG_POSICION);
            datos = getArguments().getString(ARG_DATOS);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        // Dependiendo de la posición se hace una operación u otra

        Log.i("datos",datos );

        switch (posicion) {
            case 1:
                // Stats del pokemon
                view = inflater.inflate(R.layout.fragment_pokemon_stats, container, false);

                try {
                    // Se recuperan los datos
                    JSONObject infoPokemon = new JSONObject(datos);
                    int peso  = infoPokemon.getInt("weight");
                    JSONArray arrayStats = infoPokemon.getJSONArray("stats");

                    TextView statsPeso = view.findViewById(R.id.statsPeso);
                    SeekBar seekBarPeso = view.findViewById(R.id.seekBarPeso);

                    TextView statsVida = view.findViewById(R.id.statsVida);
                    SeekBar seekBarVida = view.findViewById(R.id.seekBarVida);

                    TextView statsAtq = view.findViewById(R.id.statsAtq);
                    SeekBar seekBarAtq = view.findViewById(R.id.seekBarAtq);

                    TextView statsDef = view.findViewById(R.id.statsDefensa);
                    SeekBar seekBarDef = view.findViewById(R.id.seekBarDefensa);

                    TextView statsAtqSpecial = view.findViewById(R.id.statsAtqSpecial);
                    SeekBar seekBarAtqSpecial = view.findViewById(R.id.seekBarAtqSpecial);

                    TextView statsDefSpecial = view.findViewById(R.id.statsDefSpecial);
                    SeekBar seekBarDefSpecial = view.findViewById(R.id.seekBarDefSpecial);

                    TextView statsSpeed = view.findViewById(R.id.statsSpeed);
                    SeekBar seekBarSpeed = view.findViewById(R.id.seekBarSpeed);

                    statsPeso.setText("Peso: " + peso);
                    // Esto impide que se pueda cambiar el valor asignado
                    seekBarPeso.setOnTouchListener((view1, motionEvent) -> true);
                    seekBarPeso.setMax(500);
                    seekBarPeso.setProgress(peso);

                    int valor;

                    // Vida
                    valor = arrayStats.getJSONObject(0).getInt("base_stat");
                    statsVida.setText("HP: " +  valor);
                    seekBarVida.setOnTouchListener((view13, motionEvent) -> true);
                    seekBarVida.setMax(256);
                    seekBarVida.setProgress(valor);

                    // Ataque
                    valor = arrayStats.getJSONObject(1).getInt("base_stat");
                    statsAtq.setText("Atq: " +  valor);
                    seekBarAtq.setOnTouchListener((view12, motionEvent) -> true);
                    seekBarAtq.setMax(256);
                    seekBarAtq.setProgress(valor);

                    // Defensa
                    valor = arrayStats.getJSONObject(2).getInt("base_stat");
                    System.out.println("Defensa" + valor);
                    statsDef.setText("Def: " +  valor);
                    seekBarDef.setOnTouchListener((view12, motionEvent) -> true);
                    seekBarDef.setMax(256);
                    seekBarDef.setProgress(valor);

                    // Ataque Especial
                    valor = arrayStats.getJSONObject(3).getInt("base_stat");
                    statsAtqSpecial.setText("AtqEsp: " +  valor);
                    seekBarAtqSpecial.setOnTouchListener((view12, motionEvent) -> true);
                    seekBarAtqSpecial.setMax(256);
                    seekBarAtqSpecial.setProgress(valor);

                    // Defensa Especial
                    valor = arrayStats.getJSONObject(4).getInt("base_stat");
                    statsDefSpecial.setText("DefEsp.: " +  valor);
                    seekBarDefSpecial.setOnTouchListener((view12, motionEvent) -> true);
                    seekBarDefSpecial.setMax(256);
                    seekBarDefSpecial.setProgress(valor);

                    // Velocidad
                    valor = arrayStats.getJSONObject(5).getInt("base_stat");
                    statsSpeed.setText("Speed: " +  valor);
                    seekBarSpeed.setOnTouchListener((view12, motionEvent) -> true);
                    seekBarSpeed.setMax(256);
                    seekBarSpeed.setProgress(valor);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                // Habilidades del pokemon
                view = inflater.inflate(R.layout.fragment_pokemon_habilidades, container, false);
                try {
                    JSONObject infoPokemon = new JSONObject(datos);
                    JSONArray habilidades = infoPokemon.getJSONArray("abilities");
                    // oculto por defecto los campos
                    for (int i = 0; i < 4 ; i++) {
                        TextView textNombre = view.findViewById(getResources().getIdentifier("text" + (i + 1), "id", getActivity().getPackageName()));
                        TextView textInfo = view.findViewById(getResources().getIdentifier("textinfo" + (i + 1), "id", getActivity().getPackageName()));
                        textNombre.setVisibility(View.INVISIBLE);
                        textInfo.setVisibility(View.INVISIBLE);
                    }

                    // Varias habilidades (maximo de 4)
                    for (int i = 0; i < habilidades.length() && i <= 4 ; i++) {
                        // Url de la habilidad
                        String url = habilidades.getJSONObject(i).getJSONObject("ability").getString("url");
                        // se recupera la habilidad
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(url)
                                .addConverterFactory(GsonConverterFactory.create(
                                        new GsonBuilder().serializeNulls().create()
                                ))
                                .build();
                        PokemonAPIService pokemonAPIService = retrofit.create(PokemonAPIService.class);
                        Call<JsonObject> call = pokemonAPIService.getPokemonInfo();
                        int finalPosicionArray = i;
                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                try {
                                    JSONObject datos = new JSONObject(response.body().toString());
                                    // Nombre en español
                                    JSONArray array = datos.getJSONArray("names");
                                    TextView textNombre = null;
                                    for (int i = 0; i < array.length() ; i++) {
                                        if (array.getJSONObject(i).getJSONObject("language").get("name").equals("es")) {
                                            String nombre = array.getJSONObject(i).getString("name");
                                            textNombre = view.findViewById(getResources().getIdentifier("text" + (finalPosicionArray +1), "id", getActivity().getPackageName()));
                                            textNombre.setText(nombre);
                                            break;
                                        }
                                    }
                                    // Información
                                    ArrayList<String> textos = new ArrayList<>();
                                    array = datos.getJSONArray("flavor_text_entries");
                                    for (int i = 0; i < array.length() ; i++) {
                                        if (array.getJSONObject(i).getJSONObject("language").get("name").equals("es")) {
                                            if (!textos.contains(array.getJSONObject(i).getString("flavor_text").replace("\n", " "))) {
                                                textos.add(array.getJSONObject(i).getString("flavor_text").replace("\n", " "));
                                            }
                                        }
                                    }
                                    TextView textInfo = view.findViewById(getResources().getIdentifier("textinfo" + (finalPosicionArray +1), "id", getActivity().getPackageName()));
                                    textInfo.setText("");
                                    for (int i = 0; i < textos.size(); i++) {
                                        textInfo.setText(textInfo.getText() + textos.get(i) + "\n\n");
                                    }

                                    textNombre.setVisibility(View.VISIBLE);
                                    textInfo.setVisibility(View.VISIBLE);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Log.d("Error", t.toString());
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            default:
                // Información del pokemon
                view = inflater.inflate(R.layout.fragment_pokemon, container, false);
                TextView textAboutInfo = view.findViewById(R.id.textAboutInfo);
                textAboutInfo.setMovementMethod(new ScrollingMovementMethod());
                try {
                    JSONObject infoPokemon = new JSONObject(datos);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(infoPokemon.getJSONObject("species").getString("url"))
                            .addConverterFactory(GsonConverterFactory.create(
                                    new GsonBuilder().serializeNulls().create()
                            ))
                            .build();
                    PokemonAPIService pokemonAPIService = retrofit.create(PokemonAPIService.class);
                    Call<JsonObject> call = pokemonAPIService.getPokemonInfo();
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            Log.i("JSON", response.body().toString());
                            try {
                                JSONObject datos = new JSONObject(response.body().toString());
                                JSONArray array = datos.getJSONArray("flavor_text_entries");
                                ArrayList<String> textos = new ArrayList<>();
                                for (int i = 0; i < array.length() ; i++) {
                                    if (array.getJSONObject(i).getJSONObject("language").get("name").equals("es")) {
                                        if (!textos.contains(array.getJSONObject(i).getString("flavor_text").replace("\n", " "))) {
                                            textos.add(array.getJSONObject(i).getString("flavor_text").replace("\n", " "));
                                        }
                                    }
                                }
                                textAboutInfo.setText("");
                                for (int i = 0; i < textos.size(); i++) {
                                    textAboutInfo.setText(textAboutInfo.getText() + textos.get(i) + "\n\n");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.d("Error", t.toString());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        return view;
    }
}