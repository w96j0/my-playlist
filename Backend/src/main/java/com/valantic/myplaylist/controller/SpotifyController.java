package com.valantic.myplaylist.controller;


import com.valantic.myplaylist.model.SpotifyInfo;
import com.valantic.myplaylist.service.SpotifyApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tracks")
public class SpotifyController {

    private final SpotifyApiService spotifyApiService;

    public SpotifyController(SpotifyApiService spotifyApiService) {
        this.spotifyApiService = spotifyApiService;
    }

    @GetMapping("/{id}/spotify-info")
    public SpotifyInfo getSpotifyInfo(@PathVariable Integer id) {
        return spotifyApiService.getSpotifyInfo(id);
    }
}
