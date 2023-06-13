package com.valantic.myplaylist.model.spotifyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Images {

    @JsonProperty("url")
    private String imageURL;

}
