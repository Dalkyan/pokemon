package com.pokemon.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.dto.PokemonDto;
import com.pokemon.service.BookService;
import com.pokemon.service.PokemonService;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class PokemonRest {


   private PokemonService pokemonService;

   @Autowired
    public PokemonRest(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

        @RequestMapping(value = "/{switcher}/{id}/", method = RequestMethod.GET)
    public PokemonDto getPokemonDetail(@PathVariable String switcher, @PathVariable String id) throws IOException {
            return pokemonService.getPokemonDto(switcher, id);
    }


    public String JsonToString(JsonNode jsonNode) {
        try {
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(jsonNode.toString(), Object.class);

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (Exception e) {
            e.printStackTrace();
            return "cannot parse";
        }
    }


}
