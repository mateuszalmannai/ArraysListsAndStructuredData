package week1;

import duke.FileResource;
import org.junit.Test;

public class WordLengthsTest {

  @Test
  public void testWordLenths() throws Exception {
    WordLengths wordLengths = new WordLengths();
    int[] counts = wordLengths.countWordLengths(new FileResource("data/manyWords.txt"), new int[31]);
    for (int i = 0; i < counts.length; i++) {
      System.out.println(counts[i] + " words of length: " + i);
    }
  }
}