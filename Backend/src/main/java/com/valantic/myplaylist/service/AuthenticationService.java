package com.valantic.myplaylist.service;


import com.valantic.myplaylist.model.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    @Value("${com.valantic.playlist.client-id}")
    private String username;
    @Value("${com.valantic.playlist.client-secret}")
    private String password;

    private final RestTemplate restTemplate;
    private final String authenticationAPI = "https://accounts.spotify.com/api/token";

    public AuthenticationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");

        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);

        AuthenticationDTO response = restTemplate.exchange(
                authenticationAPI,
                HttpMethod.POST,
                requestEntity,
                AuthenticationDTO.class
        ).getBody();

        if(response != null) {
            return response.getAccessToken();
        } else {
            throw new HttpMessageNotReadableException("Token ist null");
        }
    }
}
