package mymanga.manga.service.impl;

import mymanga.manga.model.Manga;
import mymanga.manga.repository.MangaRepository;
import mymanga.manga.service.MangaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaServiceImpl implements MangaService {
    private final MangaRepository mangaRepository;

    public MangaServiceImpl(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    public List<Manga> findAll() {
        return mangaRepository.findAll();
    }
}
