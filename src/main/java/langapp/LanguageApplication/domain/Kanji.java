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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import langapp.LanguageApplication.constant.JlptLevel;

@Entity
@Table(name = "KANJI")
public class Kanji {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "KANJI_ID")
	private long id;
	
	@Column(name = "KANJI")
	private String kanjiReading;
	
	@ElementCollection
	@Column(name = "KUN_YOMI")
	private List<String> kunyomi = new ArrayList<String>();
	
	@ElementCollection
	@Column(name = "ON_YOMI")
	private List<String> onyomi = new ArrayList<String>();
	
	@ElementCollection
	@Column(name = "TRANSLATION")
	private List<String> translation = new ArrayList<String>();
	
	@ElementCollection
	@Column(name = "OTHER_FORMS")
	private List<String> otherForms = new ArrayList<String>();

	@Column(name = "JOYO_KANJI")
	private boolean joyoKanji;
	
	@Column(name = "JLPT_LEVEL")
	private JlptLevel jlptLevel;
	
	@Column(name = "DATE_CREATED")
	private LocalDateTime dateCreated;
	
	@Column(name = "DATE_LAST_MODIFIED")
	private LocalDateTime dateLastModified;
	
	@ManyToMany(mappedBy = "kanjiList")
	@JsonIgnoreProperties("kanjiList")
	private List<Word> wordList = new ArrayList<Word>();
	
	public Kanji() {
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
	public void setKanjiReading(String kanji) {
		this.kanjiReading = kanji;
	}
	public List<String> getKunyomi() {
		return kunyomi;
	}
	public void setKunyomi(List<String> kunyomi) {
		this.kunyomi = kunyomi;
	}
	public List<String> getOnyomi() {
		return onyomi;
	}
	public void setOnyomi(List<String> onyomi) {
		this.onyomi = onyomi;
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
	public boolean isJoyoKanji() {
		return joyoKanji;
	}
	public void setJoyoKanji(boolean joyoKanji) {
		this.joyoKanji = joyoKanji;
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
	public List<Word> getWordList() {
		return wordList;
	}
	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}
}