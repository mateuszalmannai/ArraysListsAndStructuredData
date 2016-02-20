package week2;

import org.junit.Test;

import java.util.List;

public class WordsInFilesTest {
  @Test
  public void testName() throws Exception {
    WordsInFiles wordsInFiles = new WordsInFiles();
    wordsInFiles.buildWordFileMap();
    List list = wordsInFiles.wordsInNumFiles(4);
    System.out.println("The maximum number of appearances is " + wordsInFiles.maxNumber());
    System.out.println("Number of words in 5 files is " + list.size());
    wordsInFiles.printFiles("sea");
    System.out.println("***********************");
    wordsInFiles.printFiles("tree");
  }
}