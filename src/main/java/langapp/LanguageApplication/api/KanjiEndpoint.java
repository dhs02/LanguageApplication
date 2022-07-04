package langapp.LanguageApplication.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import langapp.LanguageApplication.controller.KanjiService;
import langapp.LanguageApplication.domain.Kanji;

@RestController
@RequestMapping("/api/kanji")
public class KanjiEndpoint {
	@Autowired
	KanjiService kanjiService;
	
	@GetMapping("/")
	public Iterable<Kanji> getKanji() {
		return kanjiService.getAllKanji();
	}
	@GetMapping("/{id}")
	public Kanji getKanji(@PathVariable String id) {
		return kanjiService.getKanjiById(Long.parseLong(id));
	}
	@PostMapping("/")
	public Kanji addSingleKanji(@RequestBody Kanji kanji) {
		return kanjiService.addKanji(kanji);
	}
	@DeleteMapping("/{id}")
	public void deleteSingleKanji(@PathVariable String id) {
		kanjiService.deleteKanji(Long.parseLong(id));
	}
	@PutMapping("/{id}")
	public void updateSingleKanji(@PathVariable String id, @RequestBody Kanji kanjiDetails) {
		kanjiService.updateKanji(Long.parseLong(id), kanjiDetails);
	}
}