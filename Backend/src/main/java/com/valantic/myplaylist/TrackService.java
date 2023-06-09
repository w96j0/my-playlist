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

    public Track addTrack(Track newTrack) {
        if(trackRepository.existsByNameAndArtist(newTrack.getName(), newTrack.getArtist())) {
            throw new IllegalArgumentException("The Song" + newTrack.getName()
                                        + "by" + newTrack.getArtist() + "is already existing!");
        }

        return trackRepository.save(newTrack);
    }

    public void deleteTrack(Integer id) {
        trackRepository.deleteById(id);
    }
}
