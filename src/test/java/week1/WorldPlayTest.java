package week1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class WorldPlayTest {

  private WorldPlay worldPlay;

  @Before
  public void setUp() throws Exception {
    worldPlay = new WorldPlay();
  }

  @Test
  public void testIsVowel() throws Exception {

    boolean testOne = worldPlay.isVowel('F');
    boolean testTwo = worldPlay.isVowel('a');
    assertThat(testOne, is(false));
    assertThat(testTwo, is(true));
  }

  @Test
  public void testReplaceVowels() throws Exception {
    String test = worldPlay.replaceVowels("Hello World", '*');
    assertThat(test, is("H*ll* W*rld"));
  }

  @Test
  public void testEmphasize() throws Exception {
    String testOne = worldPlay.emphasize("dna ctgaaactga", 'a');
    assertThat(testOne, is("dn* ctg+*+ctg+"));

    String testTwo = worldPlay.emphasize("Mary Bella Abracadabra", 'a');
    assertThat(testTwo, is("M+ry Bell+ +br*c*d*br+"));
  }
}