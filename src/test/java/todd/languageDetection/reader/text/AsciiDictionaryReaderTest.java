package todd.languageDetection.reader.text;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;


/*
 * Important!  Run this test with the JVM argument -Xmx20M 
 */
public class AsciiDictionaryReaderTest {

	private static final Integer MAX_MEMORY_IN_MB = 20;
	private AsciiDictionaryReader asciiDictionaryReader;

	@Before
	public void setup() {
		// TODO: change AsciiDictionaryReader to a more memory efficient implementation
		asciiDictionaryReader = new AsciiDictionaryReader();
	}
	
	@Test
	public void isConfiguredFor20MegsHeapOrLess() throws Exception {
		validateMaxHeapSizeIsAtMost(MAX_MEMORY_IN_MB);
	}
	
	@Test
	public void readsEnglishDictionary() {
		Set<String> englishWords = asciiDictionaryReader.read("words_alpha.txt");
		assertThat(englishWords).contains("pancake", "house", "bacon");
		assertThat(englishWords.size()).isEqualTo(370101);
	}

	@Test (expected=IllegalArgumentException.class)
	public void failsIfDictionaryNotFound() {
		asciiDictionaryReader.read("missingFile.txt");
	}

	private void validateMaxHeapSizeIsAtMost(Integer maxMemoryInMb) {
		String memoryConfig = getMemoryArgument();
		Pattern memorySizeOptionPattern = Pattern.compile("-Xmx(\\d+)(.*)");
		Matcher matcher = memorySizeOptionPattern.matcher(memoryConfig);
		if(matcher.matches()) {
			Integer memorySize = new Integer(matcher.group(1));
			String memoryUnit = matcher.group(2);
			validateMemorySizeAndUnit(memorySize, memoryUnit, maxMemoryInMb);
		} else {
			throw new IllegalStateException("unexpected error matching regex");
		}
	}

	private String getMemoryArgument() {
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();
		return arguments.stream()
				.filter(argument -> argument.startsWith("-Xmx"))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("-Xmx argument not set"));
	}
	
	private void validateMemorySizeAndUnit(Integer memorySize, String memoryUnit, Integer maxMemoryInMb) {
		if(!memoryUnit.equalsIgnoreCase("M")) {
			throw new IllegalStateException("Memory Unit must be M, found " + memoryUnit);
		}
		if(memorySize > maxMemoryInMb) {
			throw new IllegalStateException("Memory Size must be at most " + maxMemoryInMb + ", found " + memorySize);
		}
	}

			
}
