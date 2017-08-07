package todd.bloomfilter.support;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashTest {

	@Test
	public void oneCharFnvHash() {
		assertEquals(-5808590958014384226L, Hash.fnv("A"));
	}
	
	@Test
	public void createsRangedHash() throws Exception {
		int filterSize = 1 << 20;
		assertEquals(936034L, Math.abs(Hash.fnv("A") % filterSize));
	}

}
