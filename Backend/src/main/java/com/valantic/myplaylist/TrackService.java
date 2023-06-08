package com.valantic.myplaylist;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {
    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<Track> getTracks() {
        return trackRepository.findAll();
    }

    public void addTrack(Track newTrack) {
        if(trackRepository.existsByNameAndArtist(newTrack.getName(), newTrack.getArtist())) {
            throw new IllegalArgumentException("The Song is already existing!");
        }

        trackRepository.save(newTrack);
    }
}
