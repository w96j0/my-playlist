package com.valantic.myplaylist.service;

import com.valantic.myplaylist.model.SpotifyInfo;
import com.valantic.myplaylist.model.Track;
import com.valantic.myplaylist.model.spotifyDTO.SpotifyResponseDTO;
import com.valantic.myplaylist.repository.TrackRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class SpotifyApiService {

    private final TrackRepository trackRepository;

    private final RestTemplate restTemplate;
    private final String searchforItemURL = "https://api.spotify.com/v1/search";
//    https://api.spotify.com/v1/search?query=remaster%2520track%3Akomet%2520artist%3AApache%2520207&type=track
    public SpotifyApiService(TrackRepository trackRepository, RestTemplate restTemplate) {

        this.trackRepository = trackRepository;
        this.restTemplate = restTemplate;
    }

    public SpotifyInfo getSpotifyInfo(Integer id) {

        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(("Track id not found - " + id )) );

        String accessToken = "BQC3pAyJoF6ZAbHHGCx88LYm3Sk9xSnaXS6d8nAoBHjUbIGdR-IfVdTrTVCajstJD3Tea4XprSUBz-Hi6WMtxevVnjtSihUG1b4sudC37-SrY4w16Sk";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
        headers.set("Authorization", "Bearer " + accessToken);

        String urlWithParams = searchforItemURL + "?" +
                "query=track:{name} artist:{artist}" +
                "&type=track";

        Map<String, String> params = new HashMap<>();

        params.put("name", track.getName());
        params.put("artist", track.getArtist());

        SpotifyResponseDTO response = restTemplate.exchange(
                urlWithParams,
                HttpMethod.GET,
                requestEntity,
                SpotifyResponseDTO.class,
                params
        ).getBody();


        SpotifyInfo spotifyInfo = new SpotifyInfo();
        spotifyInfo.setOpenSpotifyURL(response.getTracks().getItems()[0].getExternalurl().getSpotify());
        spotifyInfo.setImageURL(response.getTracks().getItems()[0].getAlbum().getImages()[0].getImageURL());
        return spotifyInfo;
    }
}
