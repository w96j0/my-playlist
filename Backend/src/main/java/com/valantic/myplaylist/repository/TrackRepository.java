package com.valantic.myplaylist.repository;

import com.valantic.myplaylist.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    boolean existsByNameAndArtist (String name, String artist);

}
