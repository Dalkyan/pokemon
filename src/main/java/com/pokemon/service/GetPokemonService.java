package com.pokemon.service;

import com.pokemon.dto.PokemonDto;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class GetPokemonService implements PokemonService {

    @Autowired
    ParseService parseService;


    @Override
//    public String getString(@PathVariable String switcher, @PathVariable String id) {
    public PokemonDto getPokemonDto(@PathVariable String switcher, @PathVariable String id) throws IOException {

        CloseableHttpClient httpClient
                = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory
                = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        ResponseEntity<String> response
                = new RestTemplate(requestFactory).exchange(
                "http://pokeapi.co/api/v2/"+switcher+"/"+id+"/", HttpMethod.GET, null, String.class);
//        return response.getBody();
//    }
//
//    public PokemonDto getPokemonDto(@PathVariable String switcher, @PathVariable String id) throws IOException {

        return parseService.returnParsedContentAsPokemonDto(response.getBody());
    }
}

