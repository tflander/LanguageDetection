package todd.languageDetection.scan;

import todd.languageDetection.bloom.DictionaryBloomFilter;
import todd.languageDetection.reader.text.BloomDictionaryReader;

public class BloomDictionaryScanner implements DictionaryScanner {

    public BloomDictionaryScanner(String dictionaryFilename) {
        BloomDictionaryReader dictionaryReader = new BloomDictionaryReader();
        DictionaryBloomFilter filter = dictionaryReader.read(dictionaryFilename, 370000*8);
    }

    @Override
    public boolean containsAll(String... strings) {
        return false;
    }

    @Override
    public boolean containsMost(String... strings) {
        return false;
    }
}
