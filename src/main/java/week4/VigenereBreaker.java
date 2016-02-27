package week4;

import duke.FileResource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VigenereBreaker {

  private int counter;
  private int totalWordCount;
  private int keyLength;

  /**
   * Pulls out characters from same shift
   * the newline character counts as a string
   *
   * @param message     represents the encrypted message
   * @param whichSlice  indicates the index the slice should start from
   * @param totalSlices indicates the length of the key
   * @return a String consisting of every totalSlices-th character from message
   * starting at the whichSlice-th character
   */
  public String sliceString(String message, int whichSlice, int totalSlices) {
    StringBuilder result = new StringBuilder();
    for (int i = whichSlice; i < message.length(); i += totalSlices) {
      result.append(message.charAt(i));
    }
    return result.toString();
  }

  /**
   * Find key for encrypted for given keylength
   * mostCommon: most frequent letter ('e')
   *
   * @param encrypted
   * @param klength
   * @param mostCommon
   * @return int[] of length keyLength, which holds each of the shifts that the
   * CaesarCracker found for each slice of the message
   */
  public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
    int[] vigenereKey = new int[klength];
    CaesarCracker cracker = new CaesarCracker(mostCommon);
    for (int i = 0; i < klength; i++) {
      String sliceString = sliceString(encrypted, i, klength);
      vigenereKey[i] = cracker.getKey(sliceString);
    }
    return vigenereKey;
  }

  /**
   * this is the entry point
   * it sets everything up and calls tryKeyLength
   * Use a FileResource Object to read in a file to decrypt
   * .asString() reads the entire file as a string
   * @param resource
   *
   */
  public void breakVigenere(FileResource resource) {
    // once the entire file has been read call tryKeyLength
    // passing the just read message, the given keyLength and the most common letter ('e')
    // tryKeyLength() returns the key as an array of ints which is simply passed to the
    // constructor of the VigenereCipher and then make use of its .decrypt() method to
    // decrypt the encrypted message
    // print out the result
    String cipherText = resource.asString();

    FileResource dictionary = new FileResource("dictionaries/English");
    Set<String> dictSet = readDictionary(dictionary);

//    int[] key = tryKeyLength(cipherText, klength, mostCommon);

    String decryptedText = breakForLanguage(cipherText, dictSet);

//    VigenereCipher vigenere = new VigenereCipher(key);
//    String decryptedText = vigenere.decrypt(cipherText);
    System.out.println(decryptedText);
  }

  /**
   *
   * @param fr FileResource with one word per line
   * @return Set representing words in a dictionary
   */
  public Set<String> readDictionary(FileResource fr) {
    Set<String> dictionary = new HashSet<>();
    for (String word : fr.lines()) {
      dictionary.add(word.toLowerCase());
    }
    return dictionary;
  }

  /**
   * Method to check how many words in the message are real words
   * @param message
   * @param dictionary
   * @return
   */
  public int countWords(String message, Set<String> dictionary) {
    String[] words = message.split("\\W+");
    int counter = 0;
    totalWordCount = 0;
    for (String word : words) {
      totalWordCount++;
      if (dictionary.contains(word.toLowerCase())) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Method tries key lengths 1 - 100 to obtain the best decryption for each key length in the range
   * If the range wasn't known the method would need to iterate all the way up to encrypted.length()
   * @param encrypted
   * @param dictionary
   * @return
   */
  public String breakForLanguage(String encrypted, Set<String> dictionary) {
    counter = 0;
    StringBuffer result = new StringBuffer();
    char mostCommonCharIn = mostCommonCharIn(dictionary);
    for (int i = 1; i <= 100; i++) {
      int[] key = tryKeyLength(encrypted, i, mostCommonCharIn);
      VigenereCipher cipher = new VigenereCipher(key);
      String decrypted = cipher.decrypt(encrypted);
      int words = countWords(decrypted, dictionary);
      if (words > counter) {
        counter = words;
        result.replace(0, decrypted.length(), decrypted);
        keyLength = i;
      }
    }

    return result.toString();
  }

  /**
   * Method to find most common character in dictionary
   */
  public char mostCommonCharIn(Set<String> dictionary) {
    Map<Character, Integer> counters = new HashMap<>();
    for (String word : dictionary) {
      for (char c : word.toCharArray()) {
        if (!counters.containsKey(c)) {
          counters.put(c, 1);
        } else {
          counters.put(c, counters.get(c) + 1);
        }
      }
    }
    int max = 0;
    char mostCommon = 0;
    for (Map.Entry<Character, Integer> entry : counters.entrySet()) {
      if (entry.getValue() > max) {
        max = entry.getValue();
        mostCommon = entry.getKey();
      }
    }
    return mostCommon;
  }

  /**
   * Try breaking the encryption for each language and see what gives the best results
   * @param encrypted
   * @param languages
   */
  public void breakForAllLanguages(String encrypted, Map<String, Set<String>> languages) {

    int localCounter = 0;
    StringBuffer decryptedMessage = new StringBuffer();
    StringBuffer encryptedLanguage = new StringBuffer();
    // iterate over the language.keySet() to get the name of each language
    for (String language : languages.keySet()) {
      String message = breakForLanguage(encrypted, languages.get(language));
      int wordCount = countWords(message, languages.get(language));
      if (wordCount > localCounter) {
        localCounter = wordCount;
        decryptedMessage.replace(0, message.length(), message);
        encryptedLanguage.replace(0, language.length(), language);
      }
    }

    System.out.println(decryptedMessage);
    System.out.println("Language: " + encryptedLanguage.toString());

    // use .get() to look up the corresponding dictionary for that language
    // use breakForLanguage() and countWords() to do most of the work

    // print out the decrypted message as well as the language identified for the message




  }


  public int getCounter() {
    return counter;
  }

  public int getTotalWordCount() {
    return totalWordCount;
  }

  public int getKeyLength() {
    return keyLength;
  }
}
