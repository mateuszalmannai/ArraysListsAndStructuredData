package week1;

import duke.FileResource;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CeasarCipherTwoTest {

  @Test
  public void testEncrypt() throws Exception {
    CeasarCipherTwo ceasarCipherTwo = new CeasarCipherTwo(23, 2);
    String encrypt = ceasarCipherTwo.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees");
    assertThat(encrypt, is("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
  }

  @Test
  public void testDecrypt() throws Exception {
    CeasarCipherTwo ceasarCipherTwo = new CeasarCipherTwo(23, 2);
    String decrypt = ceasarCipherTwo.decrypt("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu");
    assertThat(decrypt, is("Just a test string with lots of eeeeeeeeeeeeeeeees"));
  }

  @Test
  public void testEncryptDecrypt() throws Exception {
    FileResource resource = new FileResource("data/plain.txt");
    CeasarCipherTwo ceasarCipherTwo = new CeasarCipherTwo(17, 3);
    String encrypt = ceasarCipherTwo.encrypt(resource.asString());
    String decrypt = ceasarCipherTwo.decrypt(encrypt);
    System.out.println(encrypt);
    System.out.println(decrypt);
  }

  @Test
  public void testBreakCaesarCipher() throws Exception {
    String plainText = breakCaesarCipher("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu");
    assertThat(plainText, is("Just a test string with lots of eeeeeeeeeeeeeeeees"));
  }

  @Test
  public void testBreakCaesarCipher02() throws Exception {
    FileResource fileResource = new FileResource("data/mysteryTwoKeysQuiz.txt");
    String breakCaesarCipher = breakCaesarCipher(fileResource.asString());
    System.out.println(breakCaesarCipher);

  }

  private String breakCaesarCipher(String input) {
    CeasarCipherTwo ceasarCipherTwo = new CeasarCipherTwo(0, 0);
    String oneHalf = ceasarCipherTwo.halfOfString(input, 0);
    String otherHalf = ceasarCipherTwo.halfOfString(input, 1);
    int keyOne = ceasarCipherTwo.getKey(oneHalf);
    int keyTwo = ceasarCipherTwo.getKey(otherHalf);
    System.err.println(keyOne + " " + keyTwo);
    CeasarCipherTwo ceasarCipherTwoKeyed = new CeasarCipherTwo(keyOne, keyTwo);
    return ceasarCipherTwoKeyed.decrypt(input);
  }
}