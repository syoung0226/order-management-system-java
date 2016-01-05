package file.csv.data;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class CsvData {

	private static final String DELIMITER = ",";

	private List<String> dataList;

	public static CsvData from(String plainText) {

		CsvData result = new CsvData();
		String[] strings = plainText.split(DELIMITER);
		result.setDataList(Arrays.asList(strings));
		return result;
	}

	public String convertPlainText() {

		StringBuilder stringBuilder = new StringBuilder();
		for (String s : dataList) {
			stringBuilder.append(s).append(DELIMITER);
		}

		return stringBuilder.substring(0, stringBuilder.length() - 1);
	}
}
