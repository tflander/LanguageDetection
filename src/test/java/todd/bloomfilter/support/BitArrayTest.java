package todd.bloomfilter.support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BitArrayTest {

	private BitArray bitArray;

	@Before
	public void setup() {
		bitArray = new BitArray(4);
	}
	
	@Test
	public void checkSizeOfOneByteBitArray() {
		bitArray = new BitArray(1);
		assertEquals(1, bitArray.getSizeInBytes());
		assertEquals(8, bitArray.getSizeInBits());
	}
	
	@Test
	public void bitArrayIsInitializedEmpty() throws Exception {
		for(int i = 0; i < bitArray.getSizeInBits(); ++i) {
			assertFalse(bitArray.isSet(i));
		}
	}

	@Test
	public void setBit() throws Exception {
		bitArray.setBit(20);
		
		for(long i = 0; i < bitArray.getSizeInBits(); ++i) {
			if(i == 20) {
				assertTrue(bitArray.isSet(i));				
			} else {
				assertFalse(bitArray.isSet(i));
			}
		}
	}
	
	@Test(expected = IllegalStateException.class)
	public void setFailsOnOutOfRangeBit() throws Exception {
		bitArray.setBit(40);
	}
	
	@Test(expected = IllegalStateException.class)
	public void setFailsOnNegativeBit() throws Exception {
		bitArray.setBit(-1);
	}
}
