package com.valantic.myplaylist;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myPlaylist/tracks")
public class TrackController {

    private final TrackService trackService;
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public List<Track> getTracks() {
        return trackService.getTracks();
    }

    @PostMapping
    public void addTrack(@RequestBody Track newTrack) {
        trackService.addTrack(newTrack);
    }
}
