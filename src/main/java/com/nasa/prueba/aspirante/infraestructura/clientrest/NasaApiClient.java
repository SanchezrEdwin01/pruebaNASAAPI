package com.nasa.prueba.aspirante.infraestructura.clientrest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.prueba.aspirante.dominio.dto.NasaDataDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
public class NasaApiClient {
    private static final String NASA_API_BASE_URL = "https://images-api.nasa.gov";

    private final RestTemplate restTemplate;

    public NasaApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public NasaDataDto searchNasaData(String searchTerm) {
        String apiUrl = NASA_API_BASE_URL + "/search";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", searchTerm);

        String fullUrl = builder.toUriString();
        System.out.println("Full URL: " + fullUrl);

        try {
            fullUrl = URLDecoder.decode(fullUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(fullUrl, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseJson = responseEntity.getBody();
            System.out.println("API response: " + responseJson);

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                NasaDataDto nasaDataDto = objectMapper.readValue(responseJson, NasaDataDto.class);

                if (!nasaDataDto.getCollection().getItems().isEmpty()) {
                    NasaDataDto.NasaApiItem firstItem = nasaDataDto.getCollection().getItems().get(0);
                    if (!firstItem.getData().isEmpty()) {
                        NasaDataDto.NasaApiData firstData = firstItem.getData().get(0);
                    }
                }

                return nasaDataDto;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
