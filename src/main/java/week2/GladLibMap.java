package week2;

import duke.FileResource;
import duke.URLResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.*;

public class GladLibMap {

  private Map<String, List<String>> myMap;
  private Map<String, String> myLabelSource;
  private List<String> usedWords;
  private List<String> usedLabels;


  private Random myRandom;

  private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
  private static String dataSourceDirectory = "data";

  public GladLibMap() {
    myLabelSource = new HashMap<>();
    readProperties();

    myMap = new HashMap<>();
    initializeFromSource(dataSourceDirectory);
    myRandom = new Random();
    usedLabels = new ArrayList<>();
    usedWords = new ArrayList<>();
  }

  private void readProperties() {
    FileResource properties = new FileResource(".properties");
    CSVParser csvParser = properties.getCSVParser(false, ":");
    for (CSVRecord record : csvParser) {
      myLabelSource.put(record.get(0), record.get(1));
    }
  }

  public GladLibMap(String source) {
    initializeFromSource(source);
    myRandom = new Random();
  }

  private void initializeFromSource(String source) {

    for (String label : myLabelSource.keySet()) {
      List<String> list = readIt(myLabelSource.get(label));
      myMap.put(label, list);
    }
  }

  private String randomFrom(List<String> source) {
    int index = myRandom.nextInt(source.size());
    return source.get(index);
  }

  private String getSubstitute(String label) {
    String result;
    if (label.equals("number")) {
      result = "" + myRandom.nextInt(50) + 5;
    } else {
      if (!usedLabels.contains(label)) {
        usedLabels.add(label);
      }
      result = randomFrom(myMap.get(label));
    }

    return result;
  }

  private String processWord(String word) {
    String result = null;
    int first = word.indexOf("<");
    int last = word.indexOf(">", first);
    boolean isNotChevron = first == -1 || last == -1;
    if (isNotChevron) {
      result = word;
    } else {
      String prefix = word.substring(0, first);
      String suffix = word.substring(last + 1);
      boolean isReapeated = true;
      while (isReapeated) {
        String sub = getSubstitute(word.substring(first + 1, last));
        if (!usedWords.contains(sub)) {
          isReapeated = false;
          usedWords.add(sub);
          result = prefix + sub + suffix;
        }
      }
    }
    return result;
  }

  private void printOut(String s, int lineWidth) {
    int charsWritten = 0;
    for (String w : s.split("\\s+")) {
      if (charsWritten + w.length() > lineWidth) {
        System.out.println();
        charsWritten = 0;
      }
      System.out.print(w + " ");
      charsWritten += w.length() + 1;
    }
  }

  private String fromTemplate(String source) {
    String story = "";
    if (source.startsWith("http")) {
      URLResource resource = new URLResource(source);
      for (String word : resource.words()) {
        story = story + processWord(word) + " ";
      }
    } else {
      FileResource resource = new FileResource(source);
      for (String word : resource.words()) {
        story = story + processWord(word) + " ";
      }
    }
    return story;
  }

  private List<String> readIt(String source) {
    List<String> list = new ArrayList<>();
    if (source.startsWith("http")) {
      URLResource resource = new URLResource(source);
      for (String line : resource.lines()) {
        list.add(line);
      }
    } else {
      FileResource resource = new FileResource(source);
      for (String line : resource.lines()) {
        list.add(line);
      }
    }
    return list;
  }

  /**
   * @return the total number of words that are possible to pick from
   */
  public int totalWordsInMap() {
    int count = 0;
    for (List<String> words : myMap.values()) {
      for (String word : words) {
          count++;
      }
    }
    return count;
  }

  public int totalWordsTest() {
    int sum = 0;
    for (String category : myMap.keySet()) {
      List<String> words = myMap.get(category);
      sum += words.size();
    }
    return sum;
  }

  public int totalWordsConsidered() {
    int count = 0;
    for (String label : usedLabels) {
      for (String word : myMap.get(label)) {
        count ++;
      }
    }
    return count;
  }

  public void makeStory() {
    System.out.println("\n");
    String story = fromTemplate("datalong/madtemplate3.txt");
    printOut(story, 60);
    System.out.println("\n\nTotal number of words replaced: " + usedWords.size());
  }

}
