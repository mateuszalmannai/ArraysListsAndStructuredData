package week4;

import duke.FileResource;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CaesarCipherTest {

  private final static String BASE_PATH = "VigenereTestData/";
  private CaesarCipher cipher;

  @Test
  public void testEncryptLetter() throws Exception {
    cipher = new CaesarCipher(5);
    char encryptedLetter = cipher.encryptLetter('A');
    assertThat(encryptedLetter, is('F'));
  }

  @Test
  public void testDecryptLetter() throws Exception {
    cipher = new CaesarCipher(5);
    char decryptedLetter = cipher.decryptLetter('F');
    assertThat(decryptedLetter, is('A'));
  }

  @Test
  public void testDecryptEnglish() throws Exception {
    cipher = new CaesarCipher(5);
    FileResource encryptedResource = new FileResource(BASE_PATH + "titus-small_key5.txt");
    String encryptedString = encryptedResource.asString();
    String decryptedString = cipher.decrypt(encryptedString);

    FileResource plainTextResource = new FileResource(BASE_PATH + "titus-small.txt");
    String plainTextString = plainTextResource.asString();
    assertThat(decryptedString, is(plainTextString));
  }

  @Test
  public void testEncryptEnglish() throws Exception {
    cipher = new CaesarCipher(5);
    FileResource plainTextResource = new FileResource(BASE_PATH + "titus-small.txt");
    String plainTextString = plainTextResource.asString();
    String cipherTextString = cipher.encrypt(plainTextString);

    FileResource encryptedResource = new FileResource(BASE_PATH + "titus-small_key5.txt");
    String encryptedString = encryptedResource.asString();
    assertThat(cipherTextString, is(encryptedString));
  }

}