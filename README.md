# LanguageDetection

Kata to create a memory efficient structure for detecting if a set of words are English.

Directions:

  - Run DictionaryScannerTest.java as a JUnit test
  - Verify the test scansDictionary() passes, but the test isConfiguredFor20MegsHeapOrLess() fails.
  - Run DictionaryScannerTest.java as a JUnit test using the JVM argument -Xmx20M
  - Verify the test scansDictionary() fails, but the test isConfiguredFor20MegsHeapOrLess() passes.
  - Test-Drive a more memory efficient DictionaryScanner, and replace the AsciiDictionaryScanner in the test
  - Verify both tests now pass
  
