package week1;

public class OOCeasarCipher {
  private String alphabet;
  private String shiftedAlphabet;
  private int key;

  public OOCeasarCipher(int key) {
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    this.key = key;
  }

  public String encrypt(String input) {
    StringBuilder encrypted = new StringBuilder(input);
    for (int i = 0; i < encrypted.length(); i++) {
      char currChar = encrypted.charAt(i);
      int idx = alphabet.indexOf(Character.toUpperCase(currChar));
      if (idx != -1) {
        char newChar = shiftedAlphabet.charAt(idx);
        encrypted.setCharAt(i, newChar);
      }

      if (Character.isLowerCase(input.charAt(i))) {
        encrypted.setCharAt(i, Character.toLowerCase(encrypted.charAt(i)));
      }
    }
    return encrypted.toString();
  }

  public String decrypt(String input) {
    OOCeasarCipher cc = new OOCeasarCipher(26 - key);
    return cc.encrypt(input);
  }

  int[] countLetters(String message) {
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

  int maxIndex(int[] frequencies) {
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
    OOCeasarCipher ooCeasarCipher = new OOCeasarCipher(distanceKey);
    return ooCeasarCipher.decrypt(input);
  }

}
