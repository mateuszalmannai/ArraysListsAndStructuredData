package week1;

/**
 * We will find the character that occurs most often and assume it's an 'e'
 */
public class CaesarBreaker {

  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int LENGTH_OF_ALPHABET = ALPHABET.length();
  private static final int INDEX_OF_E = 4;

  /**
   * Array frequencies has a relationship between
   * index and value, frequencies[8] is how many
   * times 'i' occurs, i.e. the frequency of 'i'
   *
   * @param encrypted
   * @return
   */
  public String decrypt(String encrypted) {
    int[] frequencies = countLetters(encrypted);
    int maxIndex = maxIndex(frequencies);
    int distanceKey = maxIndex - INDEX_OF_E;
    // wrap around if necessary
    if (maxIndex < INDEX_OF_E) {
      distanceKey = LENGTH_OF_ALPHABET - (INDEX_OF_E - maxIndex);
    }
    return CaesarCipher.encrypt(encrypted, LENGTH_OF_ALPHABET - distanceKey);
  }

  /**
   * Returns the index of the largest entry in frequencies
   * This is the location we will assume the letter e was shifted to
   * When looking for the maximal value, we return the index of the
   * maximal value, rather than the value itself
   * We use the distance from 'e' or 4 to shift
   *
   * @param frequencies
   * @return
   */
  int maxIndex(int[] frequencies) {
    int maxIndex = 0;
    for (int i = 0; i < frequencies.length; i++) {
      if (frequencies[i] > frequencies[maxIndex]) {
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  /**
   * Method counts the occurences of every character in
   * the input string, with A being stored in the first
   * location in the array returned
   *
   * @param message
   * @return counters
   */
  int[] countLetters(String message) {
    String alphabet = ALPHABET.toLowerCase();
    int[] counters = new int[LENGTH_OF_ALPHABET];
    for (int i = 0; i < message.length(); i++) {
      char ch = Character.toLowerCase(message.charAt(i));
      int index = alphabet.indexOf(ch);
      if (index != -1) {
        counters[index]++;
      }
    }
    return counters;
  }

  public void eyeballDecrypt(String encrypted) {
    for (int i = 0; i < LENGTH_OF_ALPHABET; i++) {
      String attempt = CaesarCipher.encrypt(encrypted, i);
      System.out.println(i + "\t" + attempt);
    }
  }

  /**
   * Method returns a new string that is every other
   * character from the passed message starting from
   * the start position.
   *
   * @param message
   * @param start
   * @return
   */
  String halfOfString(String message, int start) {
    StringBuilder result = new StringBuilder();
    for (int i = start; i < message.length(); i += 2) {
      result.append(message.charAt(i));
    }
    return result.toString();
  }

  int getKey(String cypher) {
    int[] frequencies = countLetters(cypher);
    int maxIndex = maxIndex(frequencies);
    return maxIndex >= 4 ? maxIndex - 4: maxIndex + LENGTH_OF_ALPHABET - 4;
  }

  String decryptTwoKeys(String encrypted) {
    String oneHalfString = halfOfString(encrypted, 0);
    String otherHalfString = halfOfString(encrypted, 1);
    int keyOne = getKey(oneHalfString);
    int keyTwo = getKey(otherHalfString);
    System.out.println("Key one: " + keyOne + " Key two: " + keyTwo);
    return CaesarCipher.encryptTwoKeys(encrypted, LENGTH_OF_ALPHABET - keyOne, LENGTH_OF_ALPHABET - keyTwo);
  }

}
