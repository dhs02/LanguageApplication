package langapp.LanguageApplication.exception;

public class EntryExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EntryExistsException(String arg) {
		super(arg);
	}
}