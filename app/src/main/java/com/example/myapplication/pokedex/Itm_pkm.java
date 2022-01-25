package com.example.myapplication.pokedex;

import androidx.appcompat.app.AppCompatActivity;

public class Itm_pkm extends AppCompatActivity {
    //url de la uubicacion de las imagenes
    private String rutaImagenes = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/";

    //variables
    String nombre;
    String imgPokemon;

    private String url;
    private int code_Pokemon;


    public Itm_pkm(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;

        //Obtengo el codigo
        String codigoObtenido = url.substring(0, url.length() - 1);
        codigoObtenido = codigoObtenido.substring(codigoObtenido.lastIndexOf("/") + 1);
        this.code_Pokemon = Integer.parseInt(codigoObtenido);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImgPokemon() {

        return rutaImagenes + code_Pokemon + ".png";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode_Pokemon() {
        return code_Pokemon;
    }

    public void setCode_Pokemon(int code_Pokemon) {
        this.code_Pokemon = code_Pokemon;
    }
}
