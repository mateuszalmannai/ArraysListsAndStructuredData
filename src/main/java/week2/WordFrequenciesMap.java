package week2;

import duke.FileResource;

import java.util.HashMap;
import java.util.Map;

public class WordFrequenciesMap {

  private final FileResource resource;
  private Map<String, Integer> wordFrequenciesMap;

  public WordFrequenciesMap(FileResource resource) {
    this.resource = resource;
    wordFrequenciesMap = new HashMap<>();
  }

  public void countWordsMap() {
    for (String word : resource.words()) {
      word = word.toLowerCase();
      if (!wordFrequenciesMap.containsKey(word)) {
        wordFrequenciesMap.put(word, 1);
      } else {
        wordFrequenciesMap.put(word, wordFrequenciesMap.get(word) + 1);
      }
    }
  }

  public void countTotalNumberOfWords() {
    int total = 0;
    for (String word : resource.words()) {
      total++;
    }
    System.out.println("Total Number of Words: " + total);
  }

  public void countUniqueWordOccurences(int occurenceThreshold) {
    for (String word : resource.words()) {
      word = word.toLowerCase();
      if (wordFrequenciesMap.keySet().contains(word)) {
        wordFrequenciesMap.put(word, wordFrequenciesMap.get(word) + 1);
      } else {
        wordFrequenciesMap.put(word, 1);
      }
    }

    for (String word : wordFrequenciesMap.keySet()) {
      int occurences = wordFrequenciesMap.get(word);
      if (occurences > occurenceThreshold) {
//        System.out.println(occurences + "\t" + word);
        System.out.printf("%4d:\t%s\n", occurences, word);
      }
    }
  }

  public Map<String, Integer> getWordFrequenciesMap() {
    return wordFrequenciesMap;
  }
}