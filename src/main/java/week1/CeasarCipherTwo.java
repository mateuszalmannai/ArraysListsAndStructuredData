package week1;

public class CeasarCipherTwo {
  private static String alphabet;
  private String shiftedAlphabet1;
  private String shiftedAlphabet2;
  private int key1;
  private int key2;

  public CeasarCipherTwo(int key1, int key2) {
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
    shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    this.key1 = key1;
    this.key2 = key2;
  }

  static String halfOfString(String message, int start) {
    StringBuilder result = new StringBuilder();
    for (int i = start; i < message.length(); i += 2) {
      result.append(message.charAt(i));
    }
    return result.toString();
  }

  public String encryptBak(String input) {
    StringBuilder encrypted = new StringBuilder(input);
    for (int i = 0; i < encrypted.length(); i++) {

      char currChar = encrypted.charAt(i);
      int idx = alphabet.indexOf(Character.toUpperCase(currChar));
      if (idx != -1) {
        char newChar;
        if (i % 2 == 2) {
          newChar = shiftedAlphabet1.charAt(idx);
        } else {
          newChar = shiftedAlphabet2.charAt(idx);
        }
        encrypted.setCharAt(i, newChar);
      }

      if (Character.isLowerCase(input.charAt(i))) {
        encrypted.setCharAt(i, Character.toLowerCase(encrypted.charAt(i)));
      }
    }
    return encrypted.toString();
  }

  static int getKey(String cypher) {
    int[] frequencies = countLetters(cypher);
    int maxIndex = maxIndex(frequencies);
    return maxIndex >= 4 ? maxIndex - 4 : maxIndex + alphabet.length() - 4;
  }

  public String encrypt(String input) {
    StringBuilder sb = new StringBuilder(input);

    for (int i = 0; i < sb.length(); i++) {
      char curr = sb.charAt(i);
      if (!Character.isUpperCase(curr)) {
        curr = Character.toUpperCase(curr);
        int idx = alphabet.indexOf(curr);
        if (idx != -1 && (i % 2) == 0) {
          char Nch = shiftedAlphabet1.charAt(idx);
          Nch = Character.toLowerCase(Nch);
          sb.setCharAt(i, Nch);
        } else if (idx != -1 && (i % 2) != 0) {
          char Nch = shiftedAlphabet2.charAt(idx);
          Nch = Character.toLowerCase(Nch);
          sb.setCharAt(i, Nch);
        }
      } else if (Character.isUpperCase(curr)) {
        int idx = alphabet.indexOf(curr);
        if (idx != -1 && (i % 2) == 0) {
          char Nch = shiftedAlphabet1.charAt(idx);
          sb.setCharAt(i, Nch);
        } else if (idx != -1 && (i % 2) != 0) {
          char Nch = shiftedAlphabet2.charAt(idx);
          sb.setCharAt(i, Nch);
        }
      }

    }
    return sb.toString();
  }


  public String decrypt(String input) {
    CeasarCipherTwo cc = new CeasarCipherTwo(26 - key1, 26 - key2);
    return cc.encrypt(input);
  }

  static int[] countLetters(String message) {
    String lowerCaseAlphabet = alphabet.toLowerCase();
    int[] counters = new int[alphabet.length()];
    for (int i = 0; i < message.length(); i++) {
      char ch = Character.toLowerCase(message.charAt(i));
      int index = lowerCaseAlphabet.indexOf(ch);
      if (index != -1) {
        counters[index]++;
      }
    }
    return counters;
  }

  static int maxIndex(int[] frequencies) {
    int maxIndex = 0;
    for (int i = 0; i < frequencies.length; i++) {
      if (frequencies[i] > frequencies[maxIndex]) {
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  String breakCaesarCipher(String input) {
    int[] frequencies = countLetters(input);
    int maxIndex = maxIndex(frequencies);
    int distanceKey = maxIndex - 4;
    // wrap around if necessary
    if (maxIndex < 4) {
      distanceKey = 26 - (4 - maxIndex);
    }
    CeasarCipherTwo ooCeasarCipher = new CeasarCipherTwo(2, 3);
    return ooCeasarCipher.decrypt(input);
  }

}
