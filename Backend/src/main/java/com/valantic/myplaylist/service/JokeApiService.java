package com.valantic.myplaylist.service;

import com.valantic.myplaylist.model.Joke;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeApiService {

    private RestTemplate restTemplate;
    private final String JOKE_API = "https://v2.jokeapi.dev/joke/Development?type=twopart";

    public JokeApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Joke getJoke() {

        return restTemplate.getForObject(JOKE_API, Joke.class);

    }
}
