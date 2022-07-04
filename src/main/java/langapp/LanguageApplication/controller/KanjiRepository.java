package langapp.LanguageApplication.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import langapp.LanguageApplication.domain.Kanji;

@Repository
public interface KanjiRepository extends CrudRepository<Kanji, Long> {
	boolean existsKanjiByKanjiReading(String kanjiReading);
}