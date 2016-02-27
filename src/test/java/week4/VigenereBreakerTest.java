package week4;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class VigenereBreakerTest {


  private final static String BASE_PATH = "VigenereTestData/";
  VigenereBreaker breaker;

  @Before
  public void setUp() throws Exception {
    breaker = new VigenereBreaker();
  }

  @Test
  public void testSliceString() throws Exception {
    final String message = "abcdefghijklm";

    String sliceString1 = breaker.sliceString(message, 0, 3);
    assertThat(sliceString1, is("adgjm"));
    String sliceString2 = breaker.sliceString(message, 1, 3);
    assertThat(sliceString2, is("behk"));
    String sliceString3 = breaker.sliceString(message, 2, 3);
    assertThat(sliceString3, is("cfil"));
    String sliceString4 = breaker.sliceString(message, 0, 4);
    assertThat(sliceString4, is("aeim"));
    String sliceString5 = breaker.sliceString(message, 1, 4);
    assertThat(sliceString5, is("bfj"));
    String sliceString6 = breaker.sliceString(message, 2, 4);
    assertThat(sliceString6, is("cgk"));
    String sliceString7 = breaker.sliceString(message, 3, 4);
    assertThat(sliceString7, is("dhl"));
    String sliceString8 = breaker.sliceString(message, 0, 5);
    assertThat(sliceString8, is("afk"));
    String sliceString9 = breaker.sliceString(message, 1, 5);
    assertThat(sliceString9, is("bgl"));
    String sliceString10 = breaker.sliceString(message, 2, 5);
    assertThat(sliceString10, is("chm"));
    String sliceString11 = breaker.sliceString(message, 3, 5);
    assertThat(sliceString11, is("di"));
    String sliceString12 = breaker.sliceString(message, 4, 5);
    assertThat(sliceString12, is("ej"));
  }

  @Test
  public void testTryKeyLength() throws Exception {
    FileResource resource = new FileResource(BASE_PATH + "athens_keyflute.txt");

    int[] key = breaker.tryKeyLength(resource.asString(), 5, 'e');
    assertThat(key, is(new int[]{5, 11, 20, 19, 4}));
  }

  @Test
  public void testTryKeyLengthQuiz() throws Exception {
    FileResource resource = new FileResource("messages/secretmessage1.txt");

    int[] key = breaker.tryKeyLength(resource.asString(), 4, 'e');
    assertThat(key, is(new int[]{3, 20, 10, 4}));
  }

  @Test
  public void testBreakVigenere() throws Exception {
    FileResource resource = new FileResource(BASE_PATH + "athens_keyflute.txt");
    breaker.breakVigenere(resource);
  }

  @Test
  public void testBreakVigenereQuiz() throws Exception {
    FileResource resource = new FileResource("messages/secretmessage1.txt");
    breaker.breakVigenere(resource);
  }

  @Test
  public void testBreakVigenereQuiz2() throws Exception {
    FileResource resource = new FileResource("messages/secretmessage2.txt");
    breaker.breakVigenere(resource);
    int keyLength = breaker.getKeyLength();
    assertThat(keyLength, is(57));
    int validWords = breaker.getCounter();
    assertThat(validWords, is(31565));

    String cryptoText = resource.asString();
    int[] key = breaker.tryKeyLength(cryptoText, 38, 'e');
    VigenereCipher vigenereCipher = new VigenereCipher(key);
    String decryptedText = vigenereCipher.decrypt(cryptoText);
    FileResource dictionary = new FileResource("dictionaries/English");
    Set<String> dictSet = breaker.readDictionary(dictionary);
    int wordCount = breaker.countWords(decryptedText, dictSet);
    assertThat(wordCount, is(4324));
  }

  @Test
  public void testBreakVigenereWithDictionary() throws Exception {
    FileResource resource = new FileResource(BASE_PATH + "athens_keyflute.txt");
    breaker.breakVigenere(resource);
    System.out.println("Total number of words is " + breaker.getTotalWordCount());
    System.out.println(breaker.getCounter() + " words found in dictionary.");
  }

  @Test
  public void testMostCommonCharIn() throws Exception {
    FileResource dictionary = new FileResource("dictionaries/English");
    Set<String> dictSet = breaker.readDictionary(dictionary);
    char c = breaker.mostCommonCharIn(dictSet);
    assertThat(c, is('e'));
  }

  @Test
  public void testBreakForAllLanguages() throws Exception {
    Map<String, Set<String>> languages = getLanguages();

    FileResource resource = new FileResource(BASE_PATH + "athens_keyflute.txt");

    breaker.breakForAllLanguages(resource.asString(),languages);
  }

  @Test
  public void testBreakForAllLanguagesQuiz() throws Exception {
    Map<String, Set<String>> languages = getLanguages();

    FileResource resource = new FileResource("messages/secretmessage4.txt");

    breaker.breakForAllLanguages(resource.asString(),languages);
  }

  private Map<String, Set<String>> getLanguages() {
    FileResource english = new FileResource("dictionaries/English");
    FileResource dutch = new FileResource("dictionaries/Dutch");
    FileResource french = new FileResource("dictionaries/French");
    FileResource danish = new FileResource("dictionaries/Danish");
    FileResource german = new FileResource("dictionaries/German");
    FileResource italian = new FileResource("dictionaries/Italian");
    FileResource portuguese = new FileResource("dictionaries/Portuguese");
    FileResource spanish = new FileResource("dictionaries/Spanish");
    Set<String> englishDictionary = breaker.readDictionary(english);
    Set<String> dutchDictionary = breaker.readDictionary(dutch);
    Set<String> frenchDictionary = breaker.readDictionary(french);
    Set<String> danishDictionary = breaker.readDictionary(danish);
    Set<String> germanDictionary = breaker.readDictionary(german);
    Set<String> italianDictionary = breaker.readDictionary(italian);
    Set<String> portugueseDictionary = breaker.readDictionary(portuguese);
    Set<String> spanishDictionary = breaker.readDictionary(spanish);


    Map<String, Set<String>> languages = new HashMap<>();
    languages.put("english", englishDictionary);
    languages.put("dutch", dutchDictionary);
    languages.put("french", frenchDictionary);
    languages.put("danish", danishDictionary);
    languages.put("german", germanDictionary);
    languages.put("italian", italianDictionary);
    languages.put("portuguese", portugueseDictionary);
    languages.put("spanish", spanishDictionary);
    return languages;
  }
}