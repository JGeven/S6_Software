package mymanga.manga.service;

import mymanga.manga.model.Manga;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MangaService {
    List<Manga> findAll();
}
