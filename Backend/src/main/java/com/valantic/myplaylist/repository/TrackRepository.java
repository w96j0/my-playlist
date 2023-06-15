package com.valantic.myplaylist.repository;

import com.valantic.myplaylist.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    boolean existsByNameAndArtist (String name, String artist);
    @Query("SELECT t.id FROM Track t WHERE t.name = ?1 AND t.artist = ?2")
    Integer findByNameAndArtist (String name, String artist);


}
