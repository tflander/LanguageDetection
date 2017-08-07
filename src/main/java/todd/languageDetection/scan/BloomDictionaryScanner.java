package todd.languageDetection.scan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import todd.bloomfilter.BloomFilter;

public class BloomDictionaryScanner implements DictionaryScanner {

	final static int expectedDictionarySize = 300000;
	final static int numberOfBitsPerItem = 64;

	private final BloomFilter bloomFilter;

	public BloomDictionaryScanner(String dictionaryFilename) {
		bloomFilter = initBloomFilter(dictionaryFilename);
	}
	
	private BloomFilter initBloomFilter(String dictionaryFilename) {
		
		Path path = Paths.get("src/main/resources/" + dictionaryFilename);
		File file = path.toFile();
		BloomFilter filter = new BloomFilter(computeBloomFilterSize());

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	filter.add(line);
		    }
		} catch (Exception e) {
			throw new IllegalStateException("failure reading "+ dictionaryFilename, e);
		}
		
		return filter;
	}

	private int computeBloomFilterSize() {
		return (expectedDictionarySize / 8) * numberOfBitsPerItem;
	}

	@Override
	public boolean containsAll(String... strings) {
		for (String string : strings) {
			if(!bloomFilter.hasElement(string)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean containsMost(String... strings) {
		double matches = 0.0;
		for (String string : strings) {
			if(bloomFilter.hasElement(string)) {
				matches += 1;
			}
		}
		matches /= strings.length;
		
		return matches > 0.5;
	}

}
