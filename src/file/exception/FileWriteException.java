package file.exception;

public class FileWriteException extends RuntimeException {
	public FileWriteException() {
		super();
	}

	public FileWriteException(String message) {
		super(message);
	}
}
