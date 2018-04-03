package todd.languageDetection.scan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/*
 * Important!  Run this test with the JVM argument -Xmx20M 
 */
public class DictionaryScannerTest {
	
	private static final Integer MAX_MEMORY_IN_MB = 20;
	
	private DictionaryScanner createScanner() {
		// TODO: use a more memory efficient DictionaryScanner to allow running in 20MB
		//  It also must run fast
//		return new AsciiDictionaryScanner("words_alpha.txt");
// 		return new LazyDictionaryScanner("words_alpha.txt");
		return new BloomDictionaryScanner("words_alpha.txt");
	}

	/*********************************************************
	*** Important!  Do not modify any code below this line ***
	 ********************************************************* */

	@Test
	public void scansDictionary() throws Exception {
		DictionaryScanner scanner = createScanner();
		assertThat(scanner.containsAll("pancake", "house", "bacon")).isTrue();
		assertThat(scanner.containsAll("pancake", "house", "bacon", "kjhkjh")).isFalse();
		assertThat(scanner.containsMost("pancake", "house", "bacon", "kjhkjh")).isTrue();
	}
	
	@Test
	public void runsFast() throws Exception {
		DictionaryScanner scanner = createScanner();
		LocalDateTime start = LocalDateTime.now();
		for(int i=0; i < 100; ++i) {
			assertThat(scanner.containsAll("pancake", "house", "bacon")).isTrue();			
		}
		
		Duration elapsed = Duration.between(start, LocalDateTime.now());
		assertThat(elapsed.toMillis()).isLessThan(1000);
	}
	
	@Test
	public void isConfiguredFor20MegsHeapOrLess() throws Exception {
		validateMaxHeapSizeIsAtMost(MAX_MEMORY_IN_MB);
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
			fail("unexpected error matching regex");
		}
	}

	private String getMemoryArgument() {
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();
		return arguments.stream()
				.filter(argument -> argument.startsWith("-Xmx"))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("-Xmx argument not set. Hint -- uncomment memory setting in build.gradle"));
	}
	
	private void validateMemorySizeAndUnit(Integer memorySize, String memoryUnit, Integer maxMemoryInMb) {
		if(!memoryUnit.equalsIgnoreCase("M")) {
			fail("Memory Unit must be M, found " + memoryUnit);
		}
		if(memorySize > maxMemoryInMb) {
			fail("Memory Size must be at most " + maxMemoryInMb + ", found " + memorySize);
		}
	}


}
