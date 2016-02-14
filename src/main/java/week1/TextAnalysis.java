package week1;

import duke.FileResource;

import java.util.Random;

public class TextAnalysis {

  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


  public void textFingerPrint(String input) {
    String alphabet = ALPHABET.toLowerCase();
    int[] counters = new int[ALPHABET.length()];
    for (int i = 0; i < input.length(); i++) {
      char ch = input.charAt(i);
      int index = alphabet.indexOf(Character.toLowerCase(ch));
      if (index != -1) {
        counters[index] += 1;
      }
    }
    for (int i = 0; i < counters.length; i++) {
      System.out.printf(alphabet.charAt(i) + ": " + counters[i] + "\n");
    }
  }

  public void simpleSimulate(int rolls) {
    Random random = new Random();
    int twos = 0;
    int twelves = 0;

    for (int i = 0; i < rolls; i++) {
      int d1 = random.nextInt(6) + 1;
      int d2 = random.nextInt(6) + 1;
      if (d1 + d2 == 2) {
        twos += 1;
      } else if (d1 + d2 == 12) {
        twelves += 1;
      }
    }

    System.out.println("2's:\t" + twos + "\t" + 100.0 * twos / rolls);
    System.out.println("12's:\t" + twelves + "\t" + 100.0 * twelves / rolls);
  }

  public void simulate(int rolls) {
    Random random = new Random();
    int[] counts = new int[13];

    for (int i = 0; i < rolls; i++) {
      int d1 = random.nextInt(6) + 1;
      int d2 = random.nextInt(6) + 1;
      // for debugging
//      System.out.println("Roll is: " + d1 + "+" + d2 + "=" + (d1+d2));
      counts[d1 + d2]++;
    }
    for (int i = 2; i <= 12; i++) {
      System.out.println(i + ":\t" + counts[i] + "\t" + 100.0 * counts[i]/rolls);
    }
  }

  void countShakespeare() {
//    String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
    String[] plays = {"small.txt"};
    String[] common = getCommon();
    int[] counts = new int[common.length];
    for (int i = 0; i < plays.length; i++) {
      FileResource resource = new FileResource("data/" + plays[i]);
      countWords(resource, common, counts);
      System.out.println("Done with " + plays[i]);
    }

    for (int i = 0; i < common.length; i++) {
//      System.out.println(common[i] + "\t" + counts[i]);
      System.out.printf("%4s:%3d\n", common[i], counts[i]);
    }
  }

  private void countWords(FileResource resource, String[] common, int[] counts) {
    for (String word : resource.words()) {
      word = word.toLowerCase();
      int index = indexOf(common, word);
      if (index != -1) {
        counts[index]++;
      }
    }
  }

  /**
   * Return the location of word in list
   *
   * @param list
   * @param word
   * @return
   */
  private int indexOf(String[] list, String word) {
    int result = -1;
    for (int i = 0; i < list.length; i++) {
      if (list[i].equals(word)) {
        result = i;
      }
    }
    return result;
  }

  private String[] getCommon() {
    FileResource resource = new FileResource("data/common.txt");
    String[] common = new String[20];
    int index = 0;
    for (String string : resource.words()) {
      common[index] = string;
      index++;
    }
    return common;
  }

}
