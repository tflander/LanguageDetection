package todd.languageDetection.bloom;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryBloomFilterTest {

    private DictionaryBloomFilter dictionaryBloomFilter;

    @Before
    public void setUp() throws Exception {
        dictionaryBloomFilter = new DictionaryBloomFilter(10);
    }

    @Test
    public void addWord() throws Exception {
        assertThat(dictionaryBloomFilter.hasWord("dog")).isFalse();
        dictionaryBloomFilter.add("dog");
        assertThat(dictionaryBloomFilter.hasWord("dog")).isTrue();
    }

    @Test
    public void wordCountAdded() throws Exception {
    }

    @Test
    public void contains() throws Exception {
    }

}