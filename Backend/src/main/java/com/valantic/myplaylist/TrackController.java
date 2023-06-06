package com.valantic.myplaylist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myPlaylist/tracks")
public class TrackController {

    public TrackController() {
    }

    @GetMapping
    public String getTracks() {
        return "dummy Test";
    }
}
