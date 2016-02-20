package week2;

import duke.DirectoryResource;
import duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsInFiles {

  private Map<String, List<String>> words;


  public WordsInFiles() {
    words = new HashMap<>();
  }

  /**
   * Method adds all the words from f into the map
   * If a word is not in the map then a new List is created with this word
   * If a word is already in the map then the current filename is added to
   * its List, unless the filename already is in the list
   *
   * @param file
   */
  private void addWordsFromFile(File file) {
    String fileName = file.getName();
    FileResource resource = new FileResource("data/" + fileName);
    for (String word : resource.words()) {
      if (!words.containsKey(word)) {
        List<String> newList = new ArrayList<>();
        newList.add(fileName);
        words.put(word, newList);
      } else {
        if (!words.get(word).contains(fileName)) {
          words.get(word).add(fileName);
        }
      }
    }
  }

  /**
   * Method puts all of the words from a file in a list of files in to the map
   */
  public void buildWordFileMap() {
    words.clear();
    DirectoryResource directoryResource = new DirectoryResource();
    for (File file : directoryResource.selectedFiles()) {
      addWordsFromFile(file);
    }
  }

  /**
   * Method returns the maximum number of files any word appears in
   * considering all the words from a group of files.
   *
   * @return
   */
  public int maxNumber() {
    int max = 0;
    for (String word : words.keySet()) {
      int size = words.get(word).size();
      if (size > max) {
        max = size;
      }
    }
    return max;
  }

  /**
   * Method returns a List of words that appear in exactly number of files.
   *
   * @param number
   * @return
   */
  public List wordsInNumFiles(int number) {
    List<String> list = new ArrayList<>();
    for (String word : words.keySet()) {
      if (words.keySet().size() == number) {
        list.add(word);
      }
    }
    return list;
  }

  /**
   * Print the names of the files the word appears in, one filename per line
   *
   * @param word
   */
  public void printFiles(String word) {
    if (words.containsKey(word)) {
      for (String file : words.get(word)) {
        System.out.println(file);
      }
    }
  }

  public void printAll() {
    for (String word : words.keySet()) {
      System.out.println(word + "\t");
      for (String fName : words.get(word)) {
        System.out.println(fName+ ", ");
      }
      System.out.println();
    }
  }
}
