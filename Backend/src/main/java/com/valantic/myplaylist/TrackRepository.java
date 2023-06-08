package com.valantic.myplaylist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    boolean existsByNameAndArtist (String name, String artist);

}
