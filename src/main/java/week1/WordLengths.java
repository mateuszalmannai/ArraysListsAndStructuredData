package week1;

import duke.FileResource;

public class WordLengths {

  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();

  public int[] countWordLengths(FileResource resource, int[] counts) {
    String alphabet = ALPHABET.toLowerCase();
    int maxIndex = 0;
    for (String word : resource.words()) {
      int currentWordLength = 0;
      for (int i = 0; i < word.length(); i++) {
        char ch = Character.toLowerCase(word.charAt(i));
        if (alphabet.indexOf(ch) != -1) {
          currentWordLength++;
        }
      }
      counts[currentWordLength]++;
//      System.out.println(word + ": " + currentWordLength);
      maxIndex = indexOfMax(counts);
    }
    System.out.println("Most common word length in file: " + maxIndex);
    return counts;
  }

  private int indexOfMax(int[] values) {
    int maxIndex = 0;
    for (int i = 0; i < values.length; i++) {
      if (values[i] > values[maxIndex]) {
        maxIndex = i;
      }
    }
    return maxIndex;
  }
}
