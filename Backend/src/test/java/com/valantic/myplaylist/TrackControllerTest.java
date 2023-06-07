package com.valantic.myplaylist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrackRepository trackRepository;

    private final String URL = "/api/tracks";

    @Test
    void getTracks() throws Exception {
//        given
        Track track1 = new Track( 1,
                "title1",
                "artist1",
                "album1",
                "genre1",
                1L);

        trackRepository.save(track1);

        String expectedJson  = """
                1,
                "title1",
                "artist1",
                "album1",
                "genre1",
                1L
                """;
//        when
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(print())
                .andExpect(status().isOk());


//        then
    }
}