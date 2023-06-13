package com.valantic.myplaylist.controller;


import com.valantic.myplaylist.model.Joke;
import com.valantic.myplaylist.service.JokeApiService;
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
