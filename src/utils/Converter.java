package utils;

public interface Converter<S, D> {
	D convert(S source);
}
