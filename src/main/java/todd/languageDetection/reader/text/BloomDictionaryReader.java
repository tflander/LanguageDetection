package todd.languageDetection.reader.text;

import todd.languageDetection.bloom.DictionaryBloomFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BloomDictionaryReader {
    public DictionaryBloomFilter read(String dictionaryFilename, int filterSizeInBits) {
        DictionaryBloomFilter bloomFilter = new DictionaryBloomFilter(filterSizeInBits);

        Path path = Paths.get("src/main/resources/" + dictionaryFilename);
        File file = path.toFile();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String word;
            while ((word = br.readLine()) != null) {
                bloomFilter.add(word.trim().toLowerCase());
            }
        } catch (Exception e) {
            throw new IllegalStateException("failure reading "+ dictionaryFilename, e);
        }

        return bloomFilter;
    }
}
