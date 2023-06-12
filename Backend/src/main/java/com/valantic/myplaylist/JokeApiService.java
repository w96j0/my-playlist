package com.valantic.myplaylist;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeApiService {

    RestTemplate restTemplate = new RestTemplate();
    private final String jokeAPI = "https://v2.jokeapi.dev/joke/Development?type=twopart";

    public Joke getJoke() {

        return restTemplate.getForObject(jokeAPI, Joke.class);

    }
}
