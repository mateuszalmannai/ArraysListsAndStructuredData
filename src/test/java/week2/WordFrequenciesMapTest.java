package week2;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class WordFrequenciesMapTest {


  private WordFrequenciesMap wordFrequencies;

  @Before
  public void setUp() throws Exception {
    wordFrequencies = new WordFrequenciesMap(new FileResource("data/confucius.txt"));
  }

  @Test
  public void testCountWordsMap() throws Exception {
    wordFrequencies.countWordsMap();
    Map<String, Integer> wordFrequenciesMap = wordFrequencies.getWordFrequenciesMap();
    for (String word : wordFrequenciesMap.keySet()) {
      System.out.println(wordFrequenciesMap.get(word) + "\t" + word);
    }

  }

  @Test
  public void testCountTotalNumberOfWords() throws Exception {
    wordFrequencies.countTotalNumberOfWords();
  }

  @Test
  public void testUniqueWordOccurences() throws Exception {
    wordFrequencies.countUniqueWordOccurences(200);
  }
}