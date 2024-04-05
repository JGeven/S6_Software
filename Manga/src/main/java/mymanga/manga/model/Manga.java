package mymanga.manga.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Manga {

    @JsonProperty("data")
    Manga data;
    @JsonProperty("mal_id")
    private int mal_id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("synopsis")
    private String synopsis;

}
