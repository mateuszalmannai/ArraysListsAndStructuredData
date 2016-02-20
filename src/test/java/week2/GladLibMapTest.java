package week2;

import org.junit.Test;

import static org.junit.Assert.*;

public class GladLibMapTest {

  @Test
  public void testMakeStory() throws Exception {
    GladLibMap gladLibMap = new GladLibMap();
    gladLibMap.makeStory();
    System.out.println("Total number of words possible: " + gladLibMap.totalWordsInMap());
    System.out.println("Total number of words possible: " + gladLibMap.totalWordsTest());
    System.out.println("Total number of words considered: " + gladLibMap.totalWordsConsidered());
  }
}