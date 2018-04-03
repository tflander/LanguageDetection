package todd.languageDetection.bloom;

public class PositionByHashCalculator {

    private final int numBits;

    public PositionByHashCalculator(int numBits) {
        this.numBits = numBits;
    }

    public int getPosForWord(String word) {
        return Math.abs(word.hashCode()) % numBits;
    }
}
