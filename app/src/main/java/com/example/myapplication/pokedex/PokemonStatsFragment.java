package com.example.myapplication.pokedex;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonStatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonStatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "peso";
    private static final String ARG_PARAM2 = "arrayStats";
    private static final String ARG_PARAM3 = "vida";


    // TODO: Rename and change types of parameters
    private String peso;
    private JSONArray arrayStats;
    private String vida;


    public PokemonStatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param peso Parameter 1.
     * @param arrayStats Parameter 2.

     * @return A new instance of fragment PokemonStatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonStatsFragment newInstance(String peso, String arrayStats) {

        PokemonStatsFragment fragment = new PokemonStatsFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, peso);
        args.putString(ARG_PARAM2, arrayStats);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            peso = getArguments().getString(ARG_PARAM1);
            try {
                arrayStats = new JSONArray(getArguments().getString(ARG_PARAM2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_stats, container, false);
        TextView statsPeso = view.findViewById(R.id.statsPeso);
        SeekBar seekBarPeso = view.findViewById(R.id.seekBarPeso);

        TextView statsVida = view.findViewById(R.id.statsVida);
        SeekBar seekBarVida = view.findViewById(R.id.seekBarVida);

        TextView statsAtq = view.findViewById(R.id.statsAtq);
        SeekBar seekBarAtq = view.findViewById(R.id.seekBarAtq);

        statsPeso.setText("Peso: " + peso);
        // Esto impide que se pueda cambiar el valor asignado
        seekBarPeso.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        seekBarPeso.setMax(10000);
        seekBarPeso.setProgress(Integer.parseInt(peso));


        int valor = 0;
        try {
            // Vida
            valor = arrayStats.getJSONObject(0).getInt("base_stat");
            statsVida.setText("Vida: " +  valor);
            seekBarVida.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            seekBarVida.setMax(1000);
            seekBarVida.setProgress(valor);

            // Ataque
            valor = arrayStats.getJSONObject(1).getInt("base_stat");
            statsAtq.setText("Vida: " +  valor);
            seekBarAtq.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            seekBarAtq.setMax(1000);
            seekBarAtq.setProgress(valor);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println();
        return view;

    }
}