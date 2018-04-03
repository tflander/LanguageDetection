package todd.languageDetection.bloom;

import java.util.BitSet;

public class DictionaryBloomFilter {

    private BitSet bitSet;
    private PositionByHashCalculator posByHash;
    private int wordCountAdded;

    public DictionaryBloomFilter(int numBits) {
        bitSet = new BitSet(numBits);
        posByHash = new PositionByHashCalculator(numBits);
        wordCountAdded = 0;
    }

    public int wordCountAdded() {
        return wordCountAdded;
    }

    public boolean contains(String... words) {
        for(String word : words) {
            if(!bitSet.get(posByHash.getPosForWord(word))) {
                return false;
            }
        }
        return true;
    }

    public boolean hasWord(String word) {
        return bitSet.get(posByHash.getPosForWord(word));
    }

    public void add(String word) {
        wordCountAdded++;
        bitSet.set(posByHash.getPosForWord(word));
    }
}
