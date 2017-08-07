package todd.bloomfilter;

import todd.bloomfilter.support.BitArray;
import todd.bloomfilter.support.Hash;

public class BloomFilter {

	private BitArray bitArray;

	public BloomFilter(int sizeInBytes) {
		bitArray = new BitArray(sizeInBytes);
	}

	public boolean hasElement(String string) {
		return bitArray.isSet(getOffsetFor(string));
	}

	public void add(String string) {
		bitArray.setBit(getOffsetFor(string));
	}

	private long getOffsetFor(String string) {
		return Math.abs(Hash.fnv(string) % bitArray.getSizeInBits());
	}

}
