package file;

import java.util.List;

public interface FileService<T> {

	List<T> read();

	void write(List<T> dataList, FileWriteMode fileWriteMode);
}
