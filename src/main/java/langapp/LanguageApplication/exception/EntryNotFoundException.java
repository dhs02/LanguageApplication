package langapp.LanguageApplication.exception;

public class EntryNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EntryNotFoundException(String arg) {
		super(arg);
	}
}