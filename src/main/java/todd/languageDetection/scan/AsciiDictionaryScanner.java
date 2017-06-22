package todd.languageDetection.scan;

import java.util.Set;

import todd.languageDetection.reader.text.AsciiDictionaryReader;

public class AsciiDictionaryScanner implements DictionaryScanner {

	private Set<String> words;

	public AsciiDictionaryScanner(String dictionaryFilename) {
		AsciiDictionaryReader asciiDictionaryReader = new AsciiDictionaryReader();
		words = asciiDictionaryReader.read(dictionaryFilename);
	}

	@Override
	public boolean containsAll(String... strings) {
		return getStringPercentage(strings) > 0.99999999;
	}

	@Override
	public boolean containsMost(String... strings) {
		return getStringPercentage(strings) > 0.5;
	}
	
	private double getStringPercentage(String... strings) {
		double matches = 0.0;
		for (String string : strings) {
			if(words.contains(string)) {
				matches += 1;
			}
		}
		return matches / strings.length;
	}

}
