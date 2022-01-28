package com.example.myapplication.pokedex.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonAPIService {
    @GET("pokemon/?limit=988")
    Call<PokemonResult> getPokemonsResulItem();
}
