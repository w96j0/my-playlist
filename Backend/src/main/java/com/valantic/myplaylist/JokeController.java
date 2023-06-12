package com.valantic.myplaylist;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {

    private final JokeApiService jokeApiService;

    public JokeController(JokeApiService jokeApiService) {
        this.jokeApiService = jokeApiService;
    }

    @GetMapping
    public Joke getJoke() {
        return jokeApiService.getJoke();
    }



}
