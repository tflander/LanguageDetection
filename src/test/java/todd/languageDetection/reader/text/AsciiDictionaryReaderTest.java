package todd.languageDetection.reader.text;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class AsciiDictionaryReaderTest {

	private AsciiDictionaryReader asciiDictionaryReader;

	@Before
	public void setup() {
		asciiDictionaryReader = new AsciiDictionaryReader();
	}
	
	@Test
	public void readsEnglishDictionary() {
		Set<String> englishWords = asciiDictionaryReader.read("words_alpha.txt");
		assertThat(englishWords).contains("pancake", "house", "bacon");
		assertThat(englishWords.size()).isEqualTo(370101);
	}

	@Test (expected=IllegalArgumentException.class)
	public void failsIfDictionaryNotFound() {
		asciiDictionaryReader.read("missingFile.txt");
	}
			
}
