package file.exception;

public class FileReadException extends RuntimeException {
	public FileReadException() {
		super();
	}

	public FileReadException(String message) {
		super(message);
	}
}
