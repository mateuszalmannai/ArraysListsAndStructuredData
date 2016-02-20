package week2;

import duke.FileResource;

import java.util.ArrayList;
import java.util.List;

public class WordFrequencies {
  private List<String> mywords;
  private List<Integer> myFreqs;
  private FileResource resource;

  public WordFrequencies() {
    resource = new FileResource("data/errors.txt");
    mywords = new ArrayList<>();
    myFreqs = new ArrayList<>();
  }

  public void findUnique() {

    // not sure if the following two lines are necessary as we initialize in the constructor
    mywords.clear();
    myFreqs.clear();

    for (String word : resource.words()) {
      word = word.toLowerCase();
      int index = mywords.indexOf(word);
      if (index == -1) {
        mywords.add(word);
        myFreqs.add(1);
      } else {
        int value = myFreqs.get(index);
        myFreqs.set(index, value + 1);
      }
    }
  }

  public int findIndexOfMax() {

    findUnique();
    int maxIndex = 0;
    int maxSoFar = myFreqs.get(maxIndex);
    for (int i = 0; i < myFreqs.size(); i++) {
      Integer currFreq = myFreqs.get(i);
      if (currFreq > maxSoFar) {
        maxIndex = i;
        maxSoFar = currFreq;
      }
    }
    return maxIndex;
  }

  public List<String> getMywords() {
    return mywords;
  }

  public List<Integer> getMyFreqs() {
    return myFreqs;
  }

}

