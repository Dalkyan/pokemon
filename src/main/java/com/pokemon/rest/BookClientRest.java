package com.pokemon.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.dto.BookUkLibDto;
import com.pokemon.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController("/app")
public class BookClientRest {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    BookService bookService;

    @RequestMapping("/book")
    public JsonNode getOurBook() throws IOException {
        BookUkLibDto book = new BookUkLibDto();
        book.setContent(restTemplate.getForObject(
//            String quote = restTemplate.getForObject(
                "http://bnb.data.bl.uk/doc/resource/007446989.json", String.class));
//            String result = book.getContent().split(",\"result")[0] + "}";
//            log.error(result);
////            "format":"linked-data-api","version":"0.2",
//            ObjectMapper mapper = new ObjectMapper();
//            JsonFactory factory = mapper.getFactory();
//            JsonParser parser = factory.createParser(result);
//            JsonNode actualObj = mapper.readTree(parser);

   return      bookService.returnParsedBookContent(book);
//        todo client to pokemon api https://pokeapi.co/ get info about Bulbasaur

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
