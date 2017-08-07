package todd.bloomfilter.support;

public class Hash {

	static long fnvPrime64 = 1099511628211L;
	static long fnvOffsetBasis64 = Long.parseUnsignedLong("14695981039346656037");
	
	public static long fnv(String string) {
		long hash = fnvOffsetBasis64;
		
		for(int i = 0; i < string.length(); ++i) {
			hash *= fnvPrime64;
			hash ^= string.charAt(i);
		}
		return hash;
	}

}
