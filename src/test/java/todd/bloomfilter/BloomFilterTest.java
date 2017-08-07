package todd.bloomfilter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class BloomFilterTest {
	
	private BloomFilter bloomFilter;
	
	@Before
	public void setup() {
		bloomFilter = new BloomFilter(20);		
	}

	@Test
	public void elementDoesNotExistInNewBloomFilter() throws Exception {
		assertThat(bloomFilter.hasElement("foo")).isFalse();
	}
	
	@Test
	public void addsElementToBloomFilter() throws Exception {
		bloomFilter.add("foo");
		assertThat(bloomFilter.hasElement("foo")).isTrue();
	}

}
