package com.example.myapplication.pokedex.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonAPIService {
    @GET("pokemon/?limit=988")
    Call<PokemonResult> getPokemonsResulItem();

    @GET(".")
    Call<JsonObject> getPokemonInfo();
}
