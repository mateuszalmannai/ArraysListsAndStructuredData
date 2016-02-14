package week1;

public class CaesarCipher {

  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int LENGTH_OF_ALPHABET = ALPHABET.length();

  public static String encrypt(String input, int key) {
    // Make a StringBuilder with message (encrypted)
    StringBuilder encrypted = new StringBuilder(input);
    // Write down alphabet
    // Compute the shifted alphabet
    String shiftedAlphabet = ALPHABET.substring(key) + ALPHABET.substring(0, key);

    // Count from 0 to < length of encrypted (call it i)
    for (int i = 0; i < encrypted.length(); i++) {
      // Look at the length of the ith character of encrypted (call it currChar)
      char currChar = encrypted.charAt(i);
      // Find the index of currChar in the alphabet (call it idx)
      int idx = ALPHABET.indexOf(Character.toUpperCase(currChar));
      // If currChar is in the alphabet
      if (idx != -1) {
        // Get the idxth character of shiftedAlphabet (newChar)
        char newChar = shiftedAlphabet.charAt(idx);
        // Replace the ith character of encrypted with newChar
        encrypted.setCharAt(i, newChar);
      }

      // Correct the case again to that of the input
      if (Character.isLowerCase(input.charAt(i))) {
        encrypted.setCharAt(i, Character.toLowerCase(encrypted.charAt(i)));
      }

      // otherwise do nothing

    }
    return encrypted.toString();
  }

  public static String encryptTwoKeys(String input, int key1, int key2) {
    StringBuilder encrypted = new StringBuilder(input);
    String shift01 = ALPHABET.substring(key1) + ALPHABET.substring(0, key1);
    String shift02 = ALPHABET.substring(key2) + ALPHABET.substring(0, key2);

    for (int i = 0; i < encrypted.length(); i++) {

      char currChar = encrypted.charAt(i);
      int idx = ALPHABET.indexOf(Character.toUpperCase(currChar));
      if (idx != -1) {
        char newChar;
        if (i % 2 == 0) {
          newChar = shift01.charAt(idx);
        } else {
          newChar = shift02.charAt(idx);
        }
        encrypted.setCharAt(i, newChar);
      }

      if (Character.isLowerCase(input.charAt(i))) {
        encrypted.setCharAt(i, Character.toLowerCase(encrypted.charAt(i)));
      }
    }

    return encrypted.toString();
  }

  public int indexOf(String[] list, String word) {
    int result = -1;
    for (int i = 0; i < list.length; i++) {
      if (list[i].equals(word)) {
        result = i;
      }
    }
    return result;
  }
}
