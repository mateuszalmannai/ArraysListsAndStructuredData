package week4;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CaesarCrackerTest {

  private final static String BASE_PATH = "VigenereTestData/";
  CaesarCracker cracker;
  private FileResource cipherTextResource;
  private String cipherTextString;
  private FileResource plainTextResource;
  private String plainTextString;

  @Before
  public void setUp() throws Exception {
    cracker = new CaesarCracker();

  }

  @Test
  public void testCountLetters() throws Exception {
    cipherTextResource = new FileResource(BASE_PATH + "titus-small_key5.txt");
    cipherTextString = cipherTextResource.asString();
    plainTextResource = new FileResource(BASE_PATH + "titus-small.txt");
    plainTextString = plainTextResource.asString();

    int[] letters = cracker.countLetters("Wzijk Cvxzfe");

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
    cipherTextResource = new FileResource(BASE_PATH + "titus-small_key5.txt");
    cipherTextString = cipherTextResource.asString();
    plainTextResource = new FileResource(BASE_PATH + "titus-small.txt");
    plainTextString = plainTextResource.asString();

    int[] counters = cracker.countLetters("Hi, do you want a lollipop today? I own many good flavors, but banana is outstanding.");
    int maxIndex = cracker.maxIndex(counters);
    assertThat(maxIndex, is(14));
  }

  @Test
  public void testGetKeyEnglish() throws Exception {
    cipherTextResource = new FileResource(BASE_PATH + "titus-small_key5.txt");
    cipherTextString = cipherTextResource.asString();
    plainTextResource = new FileResource(BASE_PATH + "titus-small.txt");
    plainTextString = plainTextResource.asString();

    final int key = cracker.getKey(cipherTextString);

    assertThat(key, is(5));
  }

  @Test
  public void testDecryptEnglish() throws Exception {
    cipherTextResource = new FileResource(BASE_PATH + "titus-small_key5.txt");
    cipherTextString = cipherTextResource.asString();
    plainTextResource = new FileResource(BASE_PATH + "titus-small.txt");
    plainTextString = plainTextResource.asString();

    final String decryptedString = cracker.decrypt(cipherTextString);

    assertThat(decryptedString, is(plainTextString));
  }

  @Test
  public void testGetKeyPortuguese() throws Exception {
    cracker = new CaesarCracker('a');
    cipherTextResource = new FileResource(BASE_PATH + "oslusiadas_key17.txt");
    cipherTextString = cipherTextResource.asString();
    plainTextResource = new FileResource(BASE_PATH + "oslusiadas.txt");
    plainTextString = plainTextResource.asString();

    final int key = cracker.getKey(cipherTextString);

    assertThat(key, is(17));
  }

  @Test
  public void testDecryptPortuguese() throws Exception {
    cracker = new CaesarCracker('a');
    cipherTextResource = new FileResource(BASE_PATH + "oslusiadas_key17.txt");
    cipherTextString = cipherTextResource.asString();
    plainTextResource = new FileResource(BASE_PATH + "oslusiadas.txt");
    plainTextString = plainTextResource.asString();

    final String decryptedString = cracker.decrypt(cipherTextString);

    assertThat(decryptedString, is(plainTextString));
  }

}