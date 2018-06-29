package com.pokemon.service;

import com.pokemon.dto.PokemonDto;

import java.io.IOException;

public interface PokemonService {


    PokemonDto getPokemonDto(String switcher, String id) throws IOException;
}
