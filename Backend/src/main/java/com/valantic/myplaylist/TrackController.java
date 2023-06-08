package com.valantic.myplaylist;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
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
    public Optional<Track> addTrack(@Valid @RequestBody Track newTrack) {

        trackService.addTrack(newTrack);

        return trackService.getTrack(newTrack.getId());
    }
}
