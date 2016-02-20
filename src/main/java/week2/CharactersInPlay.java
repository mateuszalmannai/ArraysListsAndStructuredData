package week2;

import duke.FileResource;

import java.util.ArrayList;
import java.util.List;

public class CharactersInPlay {
  private List<String> names;
  private List<Integer> counts;

  public CharactersInPlay() {
    names = new ArrayList<>();
    counts = new ArrayList<>();
  }

  public void update(String person) {
    int index = names.indexOf(person);
    if (index == -1) {
      names.add(person);
      counts.add(1);
    } else {
      int value = counts.get(index);
      counts.set(index, value + 1);
    }
  }

  public void findAllCharacters() {
    FileResource resource = new FileResource("data/errors.txt");
    for (String line : resource.lines()) {
      if (line.contains(".")) {
        int i = line.indexOf(".");
        update(line.substring(0, i).trim());
      }
    }
  }

  /**
   * Method to print out the names of all those characters that fall in the
   * range of speaking parts from num1 to num2 inclusive
   * @param num1
   * @param num2
   */
  public void charactersWithNumParts(int num1, int num2) {

    if (num1 > num2) {
      throw new IllegalArgumentException("num1 must be less than or equal to num2!");
    }

    findAllCharacters();

    System.out.println("************************************************");
    for (int i=0; i < names.size(); i++) {
      if (counts.get(i) >= num1 && counts.get(i) <= num2) {
        System.out.println(counts.get(i) + "\t" + names.get(i));
      }
    }
    System.out.println("************************************************");
  }

  public List<String> getNames() {
    return names;
  }

  public List<Integer> getCounts() {
    return counts;
  }
}
