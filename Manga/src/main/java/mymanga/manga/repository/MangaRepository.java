package mymanga.manga.repository;

import mymanga.manga.model.Manga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends MongoRepository<Manga, String> {
}
