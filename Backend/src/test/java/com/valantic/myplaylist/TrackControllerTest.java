package com.valantic.myplaylist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    void test_getTracks() throws Exception {
//        given
        Track track1 = new Track(1,
                "title1",
                "artist1",
                "album1",
                "genre1",
                1L);

        trackRepository.save(track1);

        String expectedJson = """ 
                [{
                "id":1,"name":"title1","artist":"artist1","album":"album1","genre":"genre1","duration":1
                }]
                """;
//        when + then
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    void test_addTrack_Avoid_Duplicate_Song() {

    }

    @Test
    void test_addTrack_name_NotBlank() throws Exception {
        String testTrackJson = """ 
                {
                "id":1,
                "name":,
                "artist":"Apache 207 und Udo Lindenberg",
                "album":"Komet",
                "genre":"German Pop",
                "duration":16753225
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testTrackJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void test_addTrack() throws Exception {

        String testTrackJson = """ 
                {
                "id":1,
                "name":"Komet",
                "artist":"Apache 207 und Udo Lindenberg",
                "album":"Komet",
                "genre":"German Pop",
                "duration":16753225
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testTrackJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        assertTrue(trackRepository.existsById(1));
    }
}