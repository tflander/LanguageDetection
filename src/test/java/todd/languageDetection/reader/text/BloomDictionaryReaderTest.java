package todd.languageDetection.reader.text;

import org.junit.Before;
import org.junit.Test;
import todd.languageDetection.bloom.DictionaryBloomFilter;

import static org.assertj.core.api.Assertions.assertThat;

public class BloomDictionaryReaderTest {

    private BloomDictionaryReader bloomDictionaryReader;

    @Before
    public void setUp() throws Exception {
        bloomDictionaryReader = new BloomDictionaryReader();
    }

    @Test
    public void read() throws Exception {
        DictionaryBloomFilter filter = bloomDictionaryReader.read("words_alpha.txt", 370000*8);
        assertThat(filter.contains("pancake", "house", "bacon"));
        assertThat(filter.wordCountAdded()).isEqualTo(370101);

    }

}