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
    private final AuthenticationService authenticationService;
    private final String searchforItemURL = "https://api.spotify.com/v1/search";
    public SpotifyApiService(TrackRepository trackRepository, RestTemplate restTemplate, AuthenticationService authenticationService) {

        this.trackRepository = trackRepository;
        this.restTemplate = restTemplate;
        this.authenticationService = authenticationService;
    }

    public SpotifyInfo getSpotifyInfo(Integer id) {

        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(("Track id not found - " + id )) );

        String accessToken = getAccessToken();

        SpotifyResponseDTO response = getSpotifyResponseDTO(track, accessToken);


        SpotifyInfo spotifyInfo = new SpotifyInfo();
        spotifyInfo.setOpenSpotifyURL(response.getTracks().getItems()[0].getExternalURL().getSpotify());
        spotifyInfo.setImageURL(response.getTracks().getItems()[0].getAlbum().getImages()[0].getImageURL());
        return spotifyInfo;
    }

    private String getAccessToken() {
        return authenticationService.getAccessToken();
    }

    private SpotifyResponseDTO getSpotifyResponseDTO(Track track, String accessToken) {
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
        return response;
    }
}
