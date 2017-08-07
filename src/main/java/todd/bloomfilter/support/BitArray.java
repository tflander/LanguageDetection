package todd.bloomfilter.support;

public class BitArray {

	private final int sizeInBytes;
	private byte[] bytes;

	public BitArray(int sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
		bytes = new byte[sizeInBytes];
	}

	public int getSizeInBytes() {
		return sizeInBytes;
	}

	public long getSizeInBits() {
		return sizeInBytes * 8;
	}

	public boolean isSet(long bitOffset) {
		ByteAndBitOffset byteAndBitOffset = new ByteAndBitOffset(bitOffset);
		byte byteToCheck = bytes[byteAndBitOffset.byteOffset];
		byte mask = (byte) (1 << byteAndBitOffset.bitOffsetInByte);
		byteToCheck &= mask;
		return byteToCheck > 0;
	}

	public void setBit(long bitOffset) {
		ByteAndBitOffset byteAndBitOffset = new ByteAndBitOffset(bitOffset);
		byte modifiedByte = bytes[byteAndBitOffset.byteOffset];
		byte mask = (byte) (1 << byteAndBitOffset.bitOffsetInByte);
		modifiedByte |= mask;
		bytes[byteAndBitOffset.byteOffset] = modifiedByte;
	}

	private class ByteAndBitOffset {
		
		public ByteAndBitOffset(long bitOffset) {
			if(bitOffset >= getSizeInBits() || bitOffset < 0) {
				throw new IllegalStateException("bitOffset " + bitOffset + " is out of range.");
			}
			
			byteOffset = (int) (bitOffset/8);
			bitOffsetInByte = (int) (bitOffset % 8);			
		}
		
		public int byteOffset;
		public int bitOffsetInByte;
	}


}
