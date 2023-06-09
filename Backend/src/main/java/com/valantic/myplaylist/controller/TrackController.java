package com.valantic.myplaylist.controller;

import com.valantic.myplaylist.model.Track;
import com.valantic.myplaylist.service.TrackService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Track addTrack(@Valid @RequestBody Track newTrack) {
        return trackService.addTrack(newTrack);
    }

    @DeleteMapping("{id}")
    public void deleteTrack(@PathVariable Integer id) {
        trackService.deleteTrack(id);
    }

    @PutMapping("{id}")
    public Track updateTrack(@PathVariable Integer id, @Valid @RequestBody Track updatedTrack) {
        return trackService.updateTrack(id, updatedTrack);
    }

}
