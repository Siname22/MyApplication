package com.example.myapplication.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonResult {
    @SerializedName("results")
    @Expose
    private ArrayList<PokemonResultItem> results;

    public ArrayList<PokemonResultItem> getResults() {
        return results;
    }
}