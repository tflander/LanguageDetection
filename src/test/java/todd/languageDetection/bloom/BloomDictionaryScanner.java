package todd.languageDetection.bloom;

import todd.languageDetection.reader.text.BloomDictionaryReader;
import todd.languageDetection.scan.DictionaryScanner;

public class BloomDictionaryScanner implements DictionaryScanner {

    private DictionaryBloomFilter filter;

    public BloomDictionaryScanner(String dictionaryFilename) {
        BloomDictionaryReader dictionaryReader = new BloomDictionaryReader();
        filter = dictionaryReader.read(dictionaryFilename, 370000 * 8);
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
            if(filter.contains(string)) {
                matches += 1;
            }
        }
        return matches / strings.length;
    }

}
