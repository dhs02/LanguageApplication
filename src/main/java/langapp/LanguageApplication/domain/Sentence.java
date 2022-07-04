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
import javax.persistence.Table;

@Entity
@Table(name = "SENTENCE")
public class Sentence {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SENTENCE_ID")
	private long id;
	
	// Full sentence in Japanese characters.
	@Column(name = "CHARACTER_STRING")
	private String characterString;
	
	// Translation of the sentence.
	@Column(name = "TRANSLATION")
	private String translation;
	
	// List that contains phonetic readings of characters.
	@ElementCollection
	@Column(name = "FURIGANA")
	private List<String> furigana = new ArrayList<String>();
	
	// List that contains optional notes.
	@ElementCollection
	@Column(name = "NOTES")
	private List<String> notes = new ArrayList<String>();
	
	@Column(name = "DATE_CREATED")
	private LocalDateTime dateCreated;
	
	@Column(name = "DATE_LAST_MODIFIED")
	private LocalDateTime dateLastModified;
}