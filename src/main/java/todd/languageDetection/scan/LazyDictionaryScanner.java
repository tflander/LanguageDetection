package todd.languageDetection.scan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import todd.languageDetection.reader.text.AsciiDictionaryReader;

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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return matches / strings.length;
	}
	
}
