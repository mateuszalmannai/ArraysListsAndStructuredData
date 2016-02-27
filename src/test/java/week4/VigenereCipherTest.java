package week4;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VigenereCipherTest {

  private final static String BASE_PATH = "VigenereTestData/";
  private VigenereCipher cipher;
  private int[] key;

  @Before
  public void setUp() throws Exception {
    key = new int[]{17, 14, 12, 4};
  }

  @Test
  public void testEncryptAndDecrypt() throws Exception {
    FileResource plainTextResource = new FileResource(BASE_PATH + "titus-small.txt");
    String plainTextString = plainTextResource.asString();

    cipher = new VigenereCipher(key);
    String encryptedString = cipher.encrypt(plainTextString);

    String decryptedString = cipher.decrypt(encryptedString);
    assertThat(decryptedString, is(plainTextString));
  }

  @Test
  public void testDecrypt() throws Exception {
    cipher = new VigenereCipher(key);
    String plainText = cipher.decrypt("Tcmp-pxety mj nikhqv htee mrfhtii tyv");
    assertThat(plainText, is("Coal-black is better than another hue"));
  }
}