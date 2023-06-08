package com.valantic.myplaylist;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    @Id
    @SequenceGenerator(
            name = "track_id_seq",
            sequenceName = "track_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "track_id_seq"
    )

    private Integer id;
    @NotBlank
    private String name;
    private String artist;
    private String album;
    private String genre;
    private long duration;

    public Track(String name, String artist, String album, String genre, long duration) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.duration = duration;
    }
}
