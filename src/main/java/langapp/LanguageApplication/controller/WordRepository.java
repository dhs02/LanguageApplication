package langapp.LanguageApplication.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import langapp.LanguageApplication.domain.Word;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {
	boolean existsWordByKanjiReading(String kanjiReading);
	boolean existsWordByHiraganaReading(String hiraganaReading);
	boolean existsWordByKatakanaReading(String katakanaReading);

	
	
	
	boolean existsWordByKanjiReadingAndHiraganaReading(String kanjiReading, String hiraganaReading);
	
//	boolean existsWordByRomajiReading(String romajiReading);
	boolean existsWordByKanjiReadingAndHiraganaReadingAndRomajiReading(String kanjiReading, String hiraganaReading, String romajiReading);
	boolean existsWordByKatakanaReadingAndRomajiReading(String katakanaReading, String romajiReading);
}