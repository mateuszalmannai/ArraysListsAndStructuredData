package week1;

import duke.FileResource;
import org.junit.Test;

public class OOCeasarCipherTest {

  @Test
  public void testEncryptAndDecrypt() throws Exception {
    FileResource fileResource = new FileResource("data/plain.txt");
    OOCeasarCipher ooCeasarCipher = new OOCeasarCipher(18);
    String ciphertext = ooCeasarCipher.encrypt(fileResource.asString());
    System.out.println(ciphertext);
    String plainText = ooCeasarCipher.decrypt(ciphertext);
    System.out.println(plainText);
  }

  @Test
  public void testBreakCeasarCipher() throws Exception {
    OOCeasarCipher ooCeasarCipher = new OOCeasarCipher(0);
    FileResource fileResource = new FileResource("data/encrypted.txt");
    String plainText = ooCeasarCipher.breakCaesarCipher(fileResource.asString());
    System.out.println(plainText);
  }

}