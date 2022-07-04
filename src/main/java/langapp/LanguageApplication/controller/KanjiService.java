package langapp.LanguageApplication.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import langapp.LanguageApplication.domain.Kanji;
import langapp.LanguageApplication.exception.EntryExistsException;
import langapp.LanguageApplication.exception.EntryNotFoundException;

@Service
@Transactional
public class KanjiService {
	@Autowired
	KanjiRepository kanjiRepository;
	
	public Iterable<Kanji> getAllKanji() {
		return kanjiRepository.findAll();
	}
	
	public Kanji getKanjiById(Long id) {
		if (!kanjiRepository.findById(id).isPresent()) {
			throw new EntryNotFoundException("Kanji not found in kanji repository.");
		}
		System.out.println("Kanji found in database");
		return kanjiRepository.findById(id).get();
	}
	
	public Kanji addKanji(Kanji kanji) {
		if (kanjiRepository.existsKanjiByKanjiReading(kanji.getKanjiReading())) {
			throw new EntryExistsException("Kanji already exists in database.");
		}
		System.out.println("Kanji added to database");
		return kanjiRepository.save(kanji);
	}
	
	public void deleteKanji(Long id) {
		if (!kanjiRepository.findById(id).isPresent()) {
			throw new EntryNotFoundException("Kanji not found in kanji repository.");
		}
		System.out.println("Kanji deleted from database");
		kanjiRepository.deleteById(id);
	}
	
	public void updateKanji(Long id, Kanji kanjiDetails) {
		Kanji kanji = kanjiRepository.findById(id).get();
		if (kanjiDetails.getKanjiReading() != null && kanjiDetails.getKanjiReading() != "") {
			if (!kanjiDetails.getKanjiReading().equals(kanji.getKanjiReading())) {
				kanji.setDateLastModified(LocalDateTime.now());
			}
			kanji.setKanjiReading(kanjiDetails.getKanjiReading());
		}
		if (kanjiDetails.getKunyomi() != null) {
			if (!kanjiDetails.getKunyomi().equals(kanji.getKunyomi())) {
				kanji.setDateLastModified(LocalDateTime.now());
			}
			kanji.setKunyomi(kanjiDetails.getKunyomi());
		}
		if (kanjiDetails.getOnyomi() != null) {
			if (!kanjiDetails.getOnyomi().equals(kanji.getOnyomi())) {
				kanji.setDateLastModified(LocalDateTime.now());
			}
			kanji.setOnyomi(kanjiDetails.getOnyomi());
		}
		if (kanjiDetails.getTranslation() != null) {
			if (!kanjiDetails.getTranslation().equals(kanji.getTranslation())) {
				kanji.setDateLastModified(LocalDateTime.now());
			}
			kanji.setTranslation(kanjiDetails.getTranslation());
		}
		if (kanjiDetails.getOtherForms() != null) {
			if (!kanjiDetails.getOtherForms().equals(kanji.getOtherForms())) {
				kanji.setDateLastModified(LocalDateTime.now());
			}
			kanji.setOtherForms(kanjiDetails.getOtherForms());
		}
		if (kanjiDetails.getJlptLevel() != null) {
			if (!kanjiDetails.getJlptLevel().equals(kanji.getJlptLevel())) {
				kanji.setDateLastModified(LocalDateTime.now());
			}
			kanji.setJlptLevel(kanjiDetails.getJlptLevel());
		}
	    System.out.println("Kanji information updated.");
	    kanjiRepository.save(kanji);
	}
}