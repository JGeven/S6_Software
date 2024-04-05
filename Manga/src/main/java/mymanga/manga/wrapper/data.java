package mymanga.manga.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import mymanga.manga.model.Manga;

@Data
public class data {

    @JsonProperty("data")
    Manga[] data;
}
