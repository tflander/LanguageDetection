package todd.languageDetection.bloom;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionByHashCalculatorTest {

    private PositionByHashCalculator positionByHashCalculator;

    @Before
    public void setUp() throws Exception {
        positionByHashCalculator = new PositionByHashCalculator(10);
    }

    @Test
    public void getPosForWord() throws Exception {
        assertThat(positionByHashCalculator.getPosForWord("dog")).isEqualTo(4);
        assertThat(positionByHashCalculator.getPosForWord("cat")).isEqualTo(2);
        assertThat(positionByHashCalculator.getPosForWord("pig")).isEqualTo(0);
        assertThat(positionByHashCalculator.getPosForWord("cow")).isEqualTo(9);
        assertThat(positionByHashCalculator.getPosForWord("rabbit")).isEqualTo(8);
        assertThat(positionByHashCalculator.getPosForWord("rat")).isEqualTo(7);
        assertThat(positionByHashCalculator.getPosForWord("horse")).isEqualTo(5);
        assertThat(positionByHashCalculator.getPosForWord("hedgehog")).isEqualTo(1);
        assertThat(positionByHashCalculator.getPosForWord("camel")).isEqualTo(4);
        assertThat(positionByHashCalculator.getPosForWord("bird")).isEqualTo(7);
    }

}