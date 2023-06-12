package com.valantic.myplaylist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Joke {

    @JsonProperty("setup")
    private String question;
    @JsonProperty("delivery")
    private String answer;
}
