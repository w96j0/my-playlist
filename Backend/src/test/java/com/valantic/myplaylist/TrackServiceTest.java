package com.valantic.myplaylist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class TrackServiceTest {

    @Mock
    private TrackRepository trackRepository;

    @AfterEach
    void tearDown() {
        trackRepository.deleteAll();
    }

    @Test
    void getTracks() {
//        given
        Track newTrack = new Track(1,
                "Komet",
                "Apache 207 und Udo Lindenberg",
                "Komet",
                "German Pop",
                16753225);
//        when
        trackRepository.save(newTrack);
//        then

    }

    @Test
    void addTrack() {
    }
}