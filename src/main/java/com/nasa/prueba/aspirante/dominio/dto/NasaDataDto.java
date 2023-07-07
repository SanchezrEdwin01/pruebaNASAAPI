package com.nasa.prueba.aspirante.dominio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class NasaDataDto {
    private NasaApiCollection collection;

    @Data
    public static class NasaApiCollection {
        private String version;
        private String href;
        private List<NasaApiItem> items;
        private NasaApiMetadata metadata;
        private List<NasaApiLink> links;
    }

    @Data
    public static class NasaApiItem {
        private String href;
        private List<NasaApiData> data;
        private List<NasaApiSubLink> links;
    }

    @Data
    public static class NasaApiData {
        private String center;
        private String title;
        private List<String> keywords;
        private String nasa_id;
        private String date_created;
        private String media_type;
        private String description;
        @JsonIgnore
        private String photographer;
        @JsonIgnore
        private String location;
        @JsonIgnore
        private String album;
    }

    @Data
    public static class NasaApiLink {
        private String href;
        private String rel;
        private String prompt;
    }

    @Data
    public static class NasaApiSubLink {
        private String href;
        private String rel;
        private String render;
    }

    @Data
    public static class NasaApiMetadata {
        private float total_hits;
    }
}
