package week2;

import duke.FileResource;
import duke.StorageResource;

public class WordsWithArrays {
  StorageResource myWords;

  public WordsWithArrays() {
    myWords = new StorageResource();
  }

  public void readWords() {
    myWords.clear();
    FileResource resource = new FileResource("data/confucius.txt");
    for (String word : resource.words()) {
      myWords.add(word.toLowerCase());
    }
  }

  public boolean contains(String[] list, String word, int number) {
    boolean result = false;
    for (int i = 0; i < number; i++) {
      if (list[i].equals(word)) {
        result = true;
      }
    }
    return result;
  }

  public int numberOfUniqueWords() {
    int numStored = 0;
    String[] words = new String[myWords.size()];
    for (String string : myWords.data()) {
      if (!contains(words, string, numStored)) {
        words[numStored] = string;
        numStored++;
      }
    }
    return numStored;
  }

  public int getMyWordsSize() {
    return myWords.size();
  }
}
