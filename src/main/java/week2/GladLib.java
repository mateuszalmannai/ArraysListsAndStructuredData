package week2;

import duke.FileResource;
import duke.URLResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.*;

public class GladLib {

  private Map<String, List<String>> myMap;
  private Map<String, String> myLabelSource;

//  private List<String> adjectiveList;
//  private List<String> nounList;
//  private List<String> colorList;
//  private List<String> countryList;
//  private List<String> nameList;
//  private List<String> animalList;
//  private List<String> timeList;
//  private List<String> verbList;
//  private List<String> fruitList;
//  private List<String> usedWordList;

  private Random myRandom;

  private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
  private static String dataSourceDirectory = "data";

  public GladLib() {
    myLabelSource = new HashMap<>();
    readProperties();

    myMap = new HashMap<>();
    initializeFromSource(dataSourceDirectory);
    myRandom = new Random();
  }

  private void readProperties() {
    FileResource properties = new FileResource(".properties");
    CSVParser csvParser = properties.getCSVParser(false, ":");
    for (CSVRecord record : csvParser) {
      myLabelSource.put(record.get(0), record.get(1));
    }
  }

  public GladLib(String source) {
    initializeFromSource(source);
    myRandom = new Random();
  }

//  private void initializeFromSource(String source) {
//    adjectiveList = readIt(source + "/adjective.txt");
//    nounList = readIt(source + "/noun.txt");
//    colorList = readIt(source + "/color.txt");
//    countryList = readIt(source + "/country.txt");
//    nameList = readIt(source + "/name.txt");
//    animalList = readIt(source + "/animal.txt");
//    timeList = readIt(source + "/timeframe.txt");
//    verbList = readIt(source + "/verb.txt");
//    fruitList = readIt(source + "/fruit.txt");
//    usedWordList = new ArrayList<>();
//  }

  private void initializeFromSource(String source) {

    for (String label : myLabelSource.keySet()) {
      List<String> list = readIt(myLabelSource.get(label));
      myMap.put(label, list);
    }
    myMap.put("usedWords", new ArrayList<>());
  }

  private String randomFrom(List<String> source) {
    int index = myRandom.nextInt(source.size());
    return source.get(index);
  }

//  private String getSubstitute(String label) {
//    if (label.equals("country")) {
//      return randomFrom(countryList);
//    }
//    if (label.equals("color")) {
//      return randomFrom(colorList);
//    }
//    if (label.equals("noun")) {
//      return randomFrom(nounList);
//    }
//    if (label.equals("name")) {
//      return randomFrom(nameList);
//    }
//    if (label.equals("adjective")) {
//      return randomFrom(adjectiveList);
//    }
//    if (label.equals("animal")) {
//      return randomFrom(animalList);
//    }
//    if (label.equals("verb")) {
//      return randomFrom(verbList);
//    }
//    if (label.equals("timeframe")) {
//      return randomFrom(timeList);
//    }
//    if (label.equals("number")) {
//      return "" + myRandom.nextInt(50) + 5;
//    }
//    return "**UNKNOWN**";
//  }


  private String getSubstitute(String label) {
    String result;
    if (label.equals("number")) {
      result = "" + myRandom.nextInt(50) + 5;
    } else {
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
        if (!myMap.get("usedWords").contains(sub)) {
          isReapeated = false;
          myMap.get("usedWords").add(sub);
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

  public void makeStory() {
    System.out.println("\n");
    String story = fromTemplate("datalong/madtemplate2.txt");
    printOut(story, 60);
    System.out.println("\n\nTotal number of words replaced: " + myMap.get("usedWords").size());
  }

}
