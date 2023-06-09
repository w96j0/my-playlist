package com.valantic.myplaylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @BeforeEach
    void setUp() {
        trackRepository.deleteAll();
    }

    @Test
    void test_getTracks() throws Exception {
//        given
        Track track1 = new Track(
                "title1",
                "artist1",
                "album1",
                "genre1",
                1L);

        trackRepository.save(track1);

        String expectedJson = """ 
                [{
                "name":"title1","artist":"artist1","album":"album1","genre":"genre1","duration":1
                }]
                """;
//        when + then
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    void test_addTrack_Avoid_Duplicate_Song() throws Exception {
        Track defaultTrack = new Track(
                1,
                "Komet",
                "Apache 207 und Udo Lindenberg",
                "Komet",
                "German Pop",
                16753225);
        trackRepository.save(defaultTrack);

        String testTrackJson = """ 
                {
                "name": "Komet",
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
    void test_addTrack_Name_NotBlank() throws Exception {
        String testTrackJson = """ 
                {
                "id":1,
                "name":"",
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
    void test_addTrack_Artist_NotBlank() throws Exception {
        String testTrackJson = """ 
                {
                "name":"Komet",
                "artist":"",
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
                .andExpect(status().isOk())
                .andExpect(content().json(testTrackJson));

        assertTrue(trackRepository.existsByNameAndArtist("Komet", "Apache 207 und Udo Lindenberg"));

    }

    @Test
    void test_deleteTrack() throws Exception {
//        given
        Track testTrack = new Track(
                "Komet",
                "Apache 207 und Udo Lindenberg",
                "Komet",
                "German Pop",
                16753225
        );

        trackRepository.save(testTrack);

//        when
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/" + testTrack.getId()))
//        then
                .andDo(print())
                .andExpect(status().isOk());

        assertFalse(trackRepository.existsById(testTrack.getId()));

    }
}