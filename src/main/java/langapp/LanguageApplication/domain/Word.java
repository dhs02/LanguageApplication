package langapp.LanguageApplication.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import langapp.LanguageApplication.constant.JlptLevel;
import langapp.LanguageApplication.constant.PartOfSpeech;

@Entity
@Table(name = "WORD")
public class Word {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "WORD_ID")
	private long id;
	
	// Reading of the word in logographic Chinese characters.
	@Column(name = "KANJI_READING")
	private String kanjiReading;
	
	// Reading of the word in Japanese syllables, using hiragana.
	@Column(name = "HIRAGANA_READING")
	private String hiraganaReading;
	
	// Reading of the word in Japanese syllables, using katakana.
	@Column(name = "KATAKANA_READING")
	private String katakanaReading;
	
	// Transliteration of the word in Latin characters.
	@Column(name = "ROMAJI_READING")
	private String romajiReading;
	
	// List that contains translations of the word.
	@ElementCollection
	@Column(name = "TRANSLATION")
	private List<String> translation = new ArrayList<String>();
	
	// List that contains other forms of the word.
	@ElementCollection
	@Column(name = "OTHER_FORMS")
	private List<String> otherForms = new ArrayList<String>();
	
	// Defines to what part of speech the word belongs (e.g. noun, adjective, etc.).
	@Column(name = "PART_OF_SPEECH")
	private PartOfSpeech partOfSpeech;
	
	// Specifies the difficulty level.
	@Column(name = "JLPT_LEVEL")
	private JlptLevel jlptLevel;
	
	@Column(name = "DATE_CREATED")
	private LocalDateTime dateCreated;
	
	@Column(name = "DATE_LAST_MODIFIED")
	private LocalDateTime dateLastModified;
	
	@ManyToMany
    @JoinTable(name = "WORD_KANJI", joinColumns = {@JoinColumn(name = "WORD_ID")}, inverseJoinColumns = {@JoinColumn(name = "KANJI_ID")})
	@JsonIgnoreProperties("wordList")
	private List<Kanji> kanjiList = new ArrayList<Kanji>();
	
	// @ManyToMany
	// private List<String> sentences;

	public Word() {
		this.dateCreated = LocalDateTime.now();
		this.dateLastModified = LocalDateTime.now();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKanjiReading() {
		return kanjiReading;
	}
	public void setKanjiReading(String kanjiReading) {
		this.kanjiReading = kanjiReading;
	}
	public String getHiraganaReading() {
		return hiraganaReading;
	}
	public void setHiraganaReading(String hiraganaReading) {
		this.hiraganaReading = hiraganaReading;
	}
	public String getKatakanaReading() {
		return katakanaReading;
	}
	public void setKatakanaReading(String katakanaReading) {
		this.katakanaReading = katakanaReading;
	}
	public String getRomajiReading() {
		return romajiReading;
	}
	public void setRomajiReading(String romajiReading) {
		this.romajiReading = romajiReading;
	}
	public List<String> getTranslation() {
		return translation;
	}
	public void setTranslation(List<String> translation) {
		this.translation = translation;
	}
	public List<String> getOtherForms() {
		return otherForms;
	}
	public void setOtherForms(List<String> otherForms) {
		this.otherForms = otherForms;
	}
	public PartOfSpeech getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public JlptLevel getJlptLevel() {
		return jlptLevel;
	}
	public void setJlptLevel(JlptLevel jlptLevel) {
		this.jlptLevel = jlptLevel;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public LocalDateTime getDateLastModified() {
		return dateLastModified;
	}
	public void setDateLastModified(LocalDateTime dateLastModified) {
		this.dateLastModified = dateLastModified;
	}
	public List<Kanji> getKanjiList() {
		return kanjiList;
	}
	public void setKanjiList(List<Kanji> kanjiList) {
		this.kanjiList = kanjiList;
	}
}