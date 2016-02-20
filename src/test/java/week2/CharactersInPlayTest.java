package week2;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CharactersInPlayTest {

  private CharactersInPlay charactersInPlay;

  @Before
  public void setUp() throws Exception {
    charactersInPlay = new CharactersInPlay();
  }

  @Test
  public void testFindAllCharacters() throws Exception {
    charactersInPlay.findAllCharacters();
    List<Integer> counts = charactersInPlay.getCounts();
    List<String> names = charactersInPlay.getNames();

    for (int i = 0; i < names.size(); i++) {
      if (counts.get(i) > 11) {
        System.out.println(counts.get(i) + "\t" + names.get(i));
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCharactersWithNumPartsThrowsExceptionIfParametersAreIncorrect() throws Exception {
    charactersInPlay.charactersWithNumParts(10, 9);
  }

  @Test
  public void testName() throws Exception {
    charactersInPlay.charactersWithNumParts(8, 15);
  }
}