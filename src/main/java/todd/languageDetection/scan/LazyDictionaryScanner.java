package todd.languageDetection.scan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LazyDictionaryScanner implements DictionaryScanner {

	private final String dictionaryFilename;

	public LazyDictionaryScanner(String dictionaryFilename) {
		this.dictionaryFilename = dictionaryFilename;
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
		Path path = Paths.get("src/main/resources/" + dictionaryFilename);
		File file = path.toFile();

		double matches = 0.0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
				for (String string : strings) {
					if(line.equalsIgnoreCase(string)) {
						matches += 1;
					}
				}
		    }
		} catch (Exception e) {
			throw new IllegalStateException("failure reading "+ dictionaryFilename, e);
		}
		
		return matches / strings.length;
	}
	
}
