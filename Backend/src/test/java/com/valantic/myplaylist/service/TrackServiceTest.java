package com.valantic.myplaylist.service;

import com.valantic.myplaylist.model.Track;
import com.valantic.myplaylist.repository.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackServiceTest {

    @Mock
    TrackRepository trackRepository;

    TrackService trackService;

    @BeforeEach
    void setUp() {
        trackService = new TrackService(trackRepository);
    }

    @Test
    void test_addTrack() {
//        given
        Track defaultTrack = new Track(
                1,
                "Komet",
                "Apache 207 und Udo Lindenberg",
                "Komet",
                "German Pop",
                16753225);
        when(trackRepository.save(any(Track.class))).thenReturn(defaultTrack);

//        when
        Track actualTrack = trackService.addTrack(defaultTrack);
//        then
        assertEquals(defaultTrack, actualTrack);

    }
}