package todd.languageDetection.scan;

public interface DictionaryScanner {

	boolean containsAll(String... strings);

	boolean containsMost(String... strings);

}