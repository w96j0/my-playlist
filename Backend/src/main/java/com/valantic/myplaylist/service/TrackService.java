package com.valantic.myplaylist.service;

import com.valantic.myplaylist.repository.TrackRepository;
import com.valantic.myplaylist.model.Track;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TrackService {
    private TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<Track> getTracks() {
        return trackRepository.findAll();
    }

    public Track addTrack(Track newTrack) {
        if(trackRepository.existsByNameAndArtist(newTrack.getName(), newTrack.getArtist())) {
            throw new IllegalArgumentException("The Song '" + newTrack.getName()
                                        + "' by '" + newTrack.getArtist() + "' is already existing!");
        }

        return trackRepository.save(newTrack);
    }

    public void deleteTrack(Integer id) {
        if(!trackRepository.existsById(id)) {
            throw new NoSuchElementException("ID: " + id + " doesn't exist.");
        }

        trackRepository.deleteById(id);
    }

    public Track updateTrack(Integer id, Track updatedTrack) {
        Track oldTrack = trackRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException(("Track id not found - " + id )) );
        oldTrack.setName(updatedTrack.getName());
        oldTrack.setArtist(updatedTrack.getArtist());
        oldTrack.setAlbum(updatedTrack.getAlbum());
        oldTrack.setGenre(updatedTrack.getGenre());
        oldTrack.setDuration(updatedTrack.getDuration());
        if(trackRepository.existsByNameAndArtist(oldTrack.getName(), oldTrack.getArtist())) {
            throw new IllegalArgumentException("The Song '" + oldTrack.getName()
                    + "' by '" + oldTrack.getArtist() + "' is already existing!");
        }

        trackRepository.save(oldTrack);

        return oldTrack;
    }
}
