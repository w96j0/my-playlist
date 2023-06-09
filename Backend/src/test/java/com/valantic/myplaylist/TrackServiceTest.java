package com.valantic.myplaylist;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
class TrackServiceTest {

    @Mock
    TrackRepository trackRepository;

    @Autowired
    TrackService trackService = new TrackService(trackRepository);

    @Test
    void test_getTrack() {
//        given
        Track defaultTrack = new Track(
                1,
                "Komet",
                "Apache 207 und Udo Lindenberg",
                "Komet",
                "German Pop",
                16753225);
        trackService.addTrack(defaultTrack);
//        when
        String returnValue = trackService.getTrack(defaultTrack.getId()).toString();
        String expectedValue = "Optional[Track(id=1, name=Komet, artist=Apache 207 und Udo Lindenberg, album=Komet, genre=German Pop, duration=16753225)]";
//        then
        assertEquals(returnValue, expectedValue);

    }
}