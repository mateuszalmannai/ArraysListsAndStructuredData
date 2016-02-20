package week2;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordsWithArraysTest {

  @Test
  public void testReadWords() throws Exception {
    WordsWithArrays wordsWithArrays = new WordsWithArrays();
    wordsWithArrays.readWords();
    System.out.println("Number of words read: " + wordsWithArrays.getMyWordsSize());
    System.out.println("Array count " + wordsWithArrays.numberOfUniqueWords());
  }

  @Test
  public void testContains() throws Exception {

  }

  @Test
  public void testNumberOfUniqueWords() throws Exception {

  }
}