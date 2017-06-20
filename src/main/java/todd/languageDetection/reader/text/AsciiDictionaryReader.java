package todd.languageDetection.reader.text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AsciiDictionaryReader {

	public Set<String> read(String filePath) {
		Set<String> words = new HashSet<>();
		
		Path path = Paths.get("src/main/resources/" + filePath);
		List<String> contents;
		try {
			contents = Files.readAllLines(path);
			words.addAll(contents);
		} catch (IOException e) {
			throw new IllegalArgumentException("cannot read " + path, e);
		}
		
		return words;
	}

}
