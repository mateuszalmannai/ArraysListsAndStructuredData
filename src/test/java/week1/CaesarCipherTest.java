package week1;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class CaesarCipherTest {

  private CaesarCipher caesarCipher;

  @Before
  public void setUp() throws Exception {
    caesarCipher = new CaesarCipher();
  }

  @Test
  public void testEncrypt01() throws Exception {

    int key = 17;
    FileResource fileResource = new FileResource("message02.txt");
    String message = fileResource.asString();
    String encrypted = caesarCipher.encrypt(message, key);
    System.out.println(encrypted);
    String decrypted = caesarCipher.encrypt(encrypted, 26 - key);
    System.out.println(decrypted);

  }

  @Test
  public void testEncrypt02() throws Exception {
    String encrypt = caesarCipher.encrypt("FIRST LEGION ATTACK EAST FLANK!", 23);
    assertThat(encrypt, is("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!"));
  }

  @Test
  public void testEncrypt03() throws Exception {
    String encrypt = caesarCipher.encrypt("First Legion", 23);
    assertThat(encrypt, is("Cfopq Ibdflk"));
  }

  @Test
  public void testEncrypt04() throws Exception {
    String encrypt = caesarCipher.encrypt("First Legion", 17);
    assertThat(encrypt, is("Wzijk Cvxzfe"));
  }

  @Test
  public void testEncrypt05() throws Exception {
    String encrypt = caesarCipher.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
    assertThat(encrypt, is("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!"));
  }

  @Test
  public void testEncrypt06() throws Exception {
    String encrypt = caesarCipher.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 8);
    assertThat(encrypt, is("Rcab i bmab abzqvo eqbp twba wn mmmmmmmmmmmmmmmmma"));
  }

  @Test
  public void testEncrypt07() throws Exception {
    String encrypt = caesarCipher.encrypt("Hi, do you want a lollipop today? I own many good flavors, but banana is outstanding.", 20);
    assertThat(encrypt, is("Bc, xi sio quhn u fiffcjij nixus? C iqh guhs aiix zfupilm, von vuhuhu cm ionmnuhxcha."));
  }

  @Test
  public void testEncrypt08() throws Exception {
    String encrypt = caesarCipher.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15);
    System.out.println(encrypt);

  }

  @Test
  public void testEncryptTwoKeys01() throws Exception {
    String encrypt = caesarCipher.encryptTwoKeys("First Legion", 23, 17);
    assertThat(encrypt, is("Czojq Ivdzle"));
  }

  @Test
  public void testEncryptTwoKeys02() throws Exception {
    String encrypt = caesarCipher.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
    assertThat(encrypt, is("Io iwjv jz dv bcm kjvammmikz mwju edbc twpz pvb wi awm v ncmxmqnm xvzog. TMGT TJCY!"));
  }

  @Test
  public void testEncryptTwoKeys03() throws Exception {
    String encryptTwoKeys = caesarCipher.encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees", 23, 2);
    assertThat(encryptTwoKeys, is("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
  }

  @Test
  public void testEncryptTwoKeys04() throws Exception {
    String decrypted = caesarCipher.encryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu", 26 - 23, 26 - 2);
    assertThat(decrypted, is("Just a test string with lots of eeeeeeeeeeeeeeeees"));
  }

  @Test
  public void testEncryptTwoKeys05() throws Exception {
    String encryptTwoKeys = caesarCipher.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26 - 2, 26 - 20);
    System.out.println(encryptTwoKeys);
  }

  @Test
  public void testEncryptTwoKeys06() throws Exception {
    String encryptTwoKeys = caesarCipher.encryptTwoKeys("Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?", 21, 8);
    System.out.println(encryptTwoKeys);
  }

  @Test
  public void testEncryptTwoKeys07() throws Exception {
    String encryptTwoKeys = caesarCipher.encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy", 26-14, 26-24);
    System.out.println(encryptTwoKeys);

  }
}