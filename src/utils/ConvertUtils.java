package utils;

import java.util.ArrayList;
import java.util.List;

public abstract class ConvertUtils {

	public static <S, D> List<D> convertList(List<S> source, Converter<S, D> converter) {

		List<D> list = new ArrayList<>();

		for (S s : source) {
			list.add(converter.convert(s));
		}
		return list;
	}

	public static <S, D> D convert(S source, Converter<S, D> converter) {
		return converter.convert(source);
	}
}
