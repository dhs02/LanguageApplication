package langapp.LanguageApplication.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import langapp.LanguageApplication.controller.WordService;
import langapp.LanguageApplication.domain.Word;

@RestController
@RequestMapping("/api/words")
public class WordEndpoint {
	@Autowired
	WordService wordService;
	
	@GetMapping("/")
	public Iterable<Word> getWords() {
		return wordService.getAllWords();
	}
	
	@GetMapping("/{id}")
	public Word getWord(@PathVariable String id) {
		return wordService.getWordById(Long.parseLong(id));
	}
	
	@GetMapping("/sentence/{sentence}")
	public List<Word> getWordsFromString(@PathVariable String sentence) {
		return wordService.getWordsFromString(sentence);
	}
	
	@PostMapping("/")
	public Word addSingleWord(@RequestBody Word word) {
		return wordService.saveWord(word);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSingleWord(@PathVariable String id) {
		wordService.deleteWord(Long.parseLong(id));
	}
	
	@PutMapping("/{id}")
	public void updateSingleWord(@PathVariable String id, @RequestBody Word wordDetails) {
		wordService.updateWord(Long.parseLong(id), wordDetails);
	}

	// Add Kanji to Word
	// @GetMapping("/kanji/{wordId}/{kanjiId}")
	@GetMapping("/kanji/{wordId}")
	public void addKanji(@PathVariable String wordId) {
		wordService.addKanjiToList(wordService.getWordById(Long.parseLong(wordId)));
	}
}