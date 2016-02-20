package week2;

import org.junit.Before;
import org.junit.Test;
import week2.WordFrequencies;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WordFrequenciesTest {

  private WordFrequencies wordFrequencies;

  @Before
  public void setUp() throws Exception {
    wordFrequencies = new WordFrequencies();
  }

  @Test
  public void testFindUnique() throws Exception {
    wordFrequencies.findUnique();
    List<String> mywords = wordFrequencies.getMywords();
    System.out.println("# unique words: " + mywords.size());

    List<Integer> myFreqs = wordFrequencies.getMyFreqs();
    for (int i = 0; i < mywords.size(); i++) {
      System.out.println(myFreqs.get(i) + "\t" + mywords.get(i));
    }
  }

  @Test
  public void testFindIndexOfMax() throws Exception {
    int indexOfMax = wordFrequencies.findIndexOfMax();
//    assertThat(indexOfMax, is(686));
    List<Integer> myFreqs = wordFrequencies.getMyFreqs();
    List<String> mywords = wordFrequencies.getMywords();
    System.out.println("The word that occurs most often and its count are: " + mywords.get(indexOfMax) + " " + myFreqs.get(indexOfMax));
  }


}