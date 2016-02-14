package week1;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;
import sun.security.util.AuthResources_it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CaesarBreakerTest {

  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  CaesarBreaker breaker;

  @Before
  public void setUp() throws Exception {
    breaker = new CaesarBreaker();
  }

  @Test
  public void testDecrypt() throws Exception {
    String encrypted = "Rcab i bmab abzqvo eqbp twba wn mmmmmmmmmmmmmmmmma";
    String decrypt = breaker.decrypt(encrypted);
    assertThat(decrypt, is("Just a test string with lots of eeeeeeeeeeeeeeeees"));
  }

  @Test
  public void testEyeballDecrypt() throws Exception {
//    breaker.eyeballDecrypt("Lujyfwapvu huk zljbypaf hyl mbukhtluahs whyaz vm avkhf'z Pualyula.");
    breaker.eyeballDecrypt("Rcab i bmab abzqvo eqbp twba wn mmmmmmmmmmmmmmmmma");
  }

  @Test
  public void testCountLetters() throws Exception {
    String alphabet = ALPHABET.toLowerCase();
    int[] letters = breaker.countLetters("Wzijk Cvxzfe");
//    for (int i = 0; i < letters.length; i++) {
//      System.out.printf(alphabet.charAt(i) + ": " + letters[i] + "\n");
//    }
    assertThat(letters[2], is(1));
    assertThat(letters[4], is(1));
    assertThat(letters[5], is(1));
    assertThat(letters[8], is(1));
    assertThat(letters[9], is(1));
    assertThat(letters[10], is(1));
    assertThat(letters[21], is(1));
    assertThat(letters[22], is(1));
    assertThat(letters[23], is(1));
    assertThat(letters[25], is(2));
  }

  @Test
  public void testMaxIndex() throws Exception {
    String alphabet = ALPHABET.toLowerCase();
    int[] counters = breaker.countLetters("Hi, do you want a lollipop today? I own many good flavors, but banana is outstanding.");
//    for (int i = 0; i < counters.length; i++) {
//      System.out.printf(alphabet.charAt(i) + ": " + counters[i] + "\n");
//    }

    int maxIndex = breaker.maxIndex(counters);
    assertThat(maxIndex, is(14));
    assertThat(alphabet.charAt(maxIndex), is('o'));
  }

  @Test
  public void testGetKey() throws Exception {
    int key = breaker.getKey("Bc, xi sio quhn u fiffcjij nixus? C iqh guhs aiix zfupilm, von vuhuhu cm ionmnuhxcha.");
    assertThat(key, is(4));
  }

  @Test
  public void testGetKey03() throws Exception {
    int key = breaker.getKey("Just a test string with lots of eeeeeeeeeeeeeeeees");
    assertThat(key, is(0));
  }

  @Test
  public void testHalfOfString() throws Exception {
    String halfOfOneString = breaker.halfOfString("Qbkm Zgis", 0);
    String halfOfAnotherString = breaker.halfOfString("Qbkm Zgis", 1);
    assertThat(halfOfOneString, is("Qk gs"));
    assertThat(halfOfAnotherString, is("bmZi"));
  }

  @Test
  public void testDecryptTwoKeys() throws Exception {
    String decryptTwoKeys = breaker.decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu");
    assertThat(decryptTwoKeys, is("Just a test string with lots of eeeeeeeeeeeeeeeees"));
  }

  @Test
  public void testDecryptTwoKeys02() throws Exception {
    String decryptTwoKeys = breaker.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
    System.out.println(decryptTwoKeys);

  }

  @Test
  public void testDectyptTwoKeys03() throws Exception {
    String decryptTwoKeys = breaker.decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
    System.out.println(decryptTwoKeys);

  }

  @Test
  public void testName() throws Exception {
    FileResource resource = new FileResource("data/mysteryTwoKeysPracticeText.txt");
    String decryptTwoKeys = breaker.decryptTwoKeys(resource.asString());
    System.out.println(decryptTwoKeys.toString());
  }
}