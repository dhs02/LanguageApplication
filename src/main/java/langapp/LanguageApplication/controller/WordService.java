package langapp.LanguageApplication.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import langapp.LanguageApplication.domain.Kanji;
import langapp.LanguageApplication.domain.Word;
import langapp.LanguageApplication.exception.EntryExistsException;
import langapp.LanguageApplication.exception.EntryNotFoundException;

@Service
@Transactional
public class WordService {
	@Autowired
	WordRepository wordRepository;
	
	@Autowired
	KanjiRepository kanjiRepository;
	
	// Return all entries
	public Iterable<Word> getAllWords() {
		return wordRepository.findAll();
	}
	
	// Return a single entry with a specific ID
	public Word getWordById(Long id) {
		if (!wordRepository.findById(id).isPresent()) {
			throw new EntryNotFoundException("Word not found in word repository.");
		}
		System.out.println("Word found in database");
		return wordRepository.findById(id).get();
	}
	
	// Check whether a string contains a Word, based on 3 different readings
	public List<Word> getWordsFromString(String sentence) {
		List<Word> wordList = new ArrayList<Word>();

		for (Word word : wordRepository.findAll()) {
			if (word.getKanjiReading() != null && !word.getKanjiReading().isEmpty()) {
				if (sentence.contains(word.getKanjiReading())) {
					wordList.add(word);
				}
			}
			else if (word.getKanjiReading() == null || word.getKanjiReading().isEmpty()){
				if ( (word.getHiraganaReading() != null && !word.getHiraganaReading().isEmpty()) && 
					 (word.getKatakanaReading() == null || word.getKatakanaReading().isEmpty()) ) {
					if (sentence.contains(word.getHiraganaReading())) {
						wordList.add(word);
					}
				}
				else if ( (word.getKatakanaReading() != null && !word.getKatakanaReading().isEmpty()) && 
					 (word.getHiraganaReading() == null || word.getHiraganaReading().isEmpty()) ) {
					if (sentence.contains(word.getKatakanaReading())) {
						wordList.add(word);
					}
				}
			}
		}
		
//		if (wordList.isEmpty()) {
//			throw new EntryNotFoundException("Words not found in word repository.");
//		}
		return wordList;
	}
	
	// Checks whether a word already exists, based on the input of 3 fields. If it doesn't, it will add a new Word to the database.
	public Word saveWord(Word word) {
		if ((word.getKanjiReading() == null || word.getKanjiReading().isEmpty()) &&
			(word.getHiraganaReading() == null || word.getHiraganaReading().isEmpty()) &&
			(word.getKatakanaReading() == null || word.getKatakanaReading().isEmpty())) {
			throw new EntryExistsException("Please enter a valid value.");
		}
		if (wordRepository.existsWordByKanjiReading(word.getKanjiReading())) {
			if ((word.getKanjiReading() != null && !word.getKanjiReading().isEmpty()) &&
				wordRepository.existsWordByKanjiReadingAndHiraganaReading(word.getKanjiReading(), word.getHiraganaReading())) {
				throw new EntryExistsException("Word already exists in database.");
			}
		}
		if (wordRepository.existsWordByHiraganaReading(word.getHiraganaReading())) {
			if ((word.getHiraganaReading() != null && !word.getHiraganaReading().isEmpty()) &&
				wordRepository.existsWordByKanjiReadingAndHiraganaReading(word.getKanjiReading(), word.getHiraganaReading())) {
				throw new EntryExistsException("Word already exists in database.");
			}
		}
		if (wordRepository.existsWordByKatakanaReading(word.getKatakanaReading())) {
			if (word.getKatakanaReading() != null && !word.getKatakanaReading().isEmpty()) {
				throw new EntryExistsException("Word already exists in database.");
			}
		}
		System.out.println("Word added to database.");
		return wordRepository.save(word);
	}
	
	public void deleteWord(Long id) {
		if (!wordRepository.findById(id).isPresent()) {
			throw new EntryNotFoundException("Word not found in word repository.");
		}
		System.out.println("Word deleted from database");
		wordRepository.deleteById(id);
	}
	
	public void updateWord(Long id, Word wordDetails) {
		Word word = wordRepository.findById(id).get();
		
		if (wordDetails.getKanjiReading() != null && wordDetails.getKanjiReading() != "") {
			if (!wordDetails.getKanjiReading().equals(word.getKanjiReading())) {
				word.setDateLastModified(LocalDateTime.now());
			}
			word.setKanjiReading(wordDetails.getKanjiReading());
		}
		if (wordDetails.getHiraganaReading() != null && wordDetails.getHiraganaReading() != "") {
			if (!wordDetails.getHiraganaReading().equals(word.getHiraganaReading())) {
				word.setDateLastModified(LocalDateTime.now());
			}
			word.setHiraganaReading(wordDetails.getHiraganaReading());
		}
		if (wordDetails.getKatakanaReading() != null && wordDetails.getKatakanaReading() != "") {
			if (!wordDetails.getKatakanaReading().equals(word.getKatakanaReading())) {
				word.setDateLastModified(LocalDateTime.now());
			}
			word.setKatakanaReading(wordDetails.getKatakanaReading());
		}
		if (wordDetails.getRomajiReading() != null && wordDetails.getRomajiReading() != "") {
			if (!wordDetails.getRomajiReading().equals(word.getRomajiReading())) {
				word.setDateLastModified(LocalDateTime.now());
			}
			word.setRomajiReading(wordDetails.getRomajiReading());
		}
		if (wordDetails.getTranslation() != null && !wordDetails.getTranslation().isEmpty()) {
			if (!wordDetails.getTranslation().equals(word.getTranslation())) {
				word.setDateLastModified(LocalDateTime.now());
			}
			word.setTranslation(wordDetails.getTranslation());
		}
		if (wordDetails.getOtherForms() != null && !wordDetails.getOtherForms().isEmpty()) {
			if (!wordDetails.getOtherForms().equals(word.getOtherForms())) {
				word.setDateLastModified(LocalDateTime.now());
			}
			word.setOtherForms(wordDetails.getOtherForms());
		}
		if (wordDetails.getPartOfSpeech() != null) {
			if (!wordDetails.getPartOfSpeech().equals(word.getPartOfSpeech())) {
				word.setDateLastModified(LocalDateTime.now());
			}
			word.setPartOfSpeech(wordDetails.getPartOfSpeech());
		}
		if (wordDetails.getJlptLevel() != null) {
			if (!wordDetails.getJlptLevel().equals(word.getJlptLevel())) {
				word.setDateLastModified(LocalDateTime.now());
			}
			word.setJlptLevel(wordDetails.getJlptLevel());
		}
	    System.out.println("Word information updated.");
	    wordRepository.save(word);
	}
	
	
	// Kanji Actions
	
	public void addKanjiToList(Word word) {
		for (Character kanji : word.getKanjiReading().toCharArray()) {
			for (Kanji k : kanjiRepository.findAll()) {
				if (kanji.toString().equals(k.getKanjiReading()) && !word.getKanjiList().contains(k)) {
					word.getKanjiList().add(k);
					System.out.println("Kanji added to Word");
				} else {
					System.out.println("Kanji already exists in list of current word.");
				}
			}
		}
	}
	
//	public void addKanjiToWord(Long wordId, Long kanjiId) {
//		Word currentWord = wordRepository.findById(wordId).get();
//		Kanji currentKanji = kanjiRepository.findById(kanjiId).get();
//		
//		if (currentWord.getKanjiList().isEmpty()) {
//			currentWord.getKanjiList().add(currentKanji);
//			wordRepository.save(currentWord);
//			System.out.println("Kanji added to Word");
//		} 
	
	
//		else {
//			for (Kanji k : currentWord.getKanjiList()) {
//				if (!currentKanji.getKanji().equals(k.getKanji())) {
//					currentWord.getKanjiList().add(currentKanji);
//					wordRepository.save(currentWord);
//					System.out.println("Kanji added to Word");
//				} else {
//					System.out.println("Kanji already exists in list of current word.");
//				}
//			}
//		}
//	}
}