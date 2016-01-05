package file;

public enum FileWriteMode {
	APPEND,
	OVERWRITE;

	public boolean getValue() {
		return this == APPEND;
	}
}
