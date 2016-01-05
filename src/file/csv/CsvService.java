package file.csv;

import file.FileService;
import file.FileWriteMode;
import file.csv.data.CsvData;
import file.exception.FileReadException;
import file.exception.FileWriteException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvService implements FileService<CsvData> {

	private final String fileName;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	public CsvService(String fileName) {
		this.fileName = fileName;
	}

	@Override public void write(List<CsvData> csvDataList, FileWriteMode fileWriteMode) {
		writeOpen(fileWriteMode.getValue());
		for (CsvData csvData : csvDataList) {
			try {
				bufferedWriter.write(csvData.convertPlainText());
				bufferedWriter.newLine();
			} catch (IOException e) {
				throw new FileWriteException("WriteException");
			}
		}
		writeClose();
	}

	private void writeOpen(boolean append) {
		try {
			this.fileWriter = new FileWriter(new File(fileName), append);
		} catch (IOException e) {
			throw new FileWriteException("WriteOpenException");
		}
		this.bufferedWriter = new BufferedWriter(fileWriter);
	}

	private void writeClose() {
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fileWriter.close();
		} catch (IOException e) {
			throw new FileWriteException("WriteCloseException");
		}
	}

	@Override public List<CsvData> read() {

		if (!readOpen()) {
			return null;
		}
		List<CsvData> result = new ArrayList<>();
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				result.add(CsvData.from(line));
			}
		} catch (IOException e) {
			throw new FileReadException("ReadException");
		}
		readClose();
		return result;
	}

	private boolean readOpen() {
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		}

		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			throw new FileReadException("FileNotFoundException");
		}
		bufferedReader = new BufferedReader(fileReader);
		return true;
	}

	private void readClose() {
		try {
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			throw new FileReadException("FileCloseException");
		}
	}
}
