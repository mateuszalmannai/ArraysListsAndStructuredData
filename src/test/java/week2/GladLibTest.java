package week2;

import org.junit.Test;

import static org.junit.Assert.*;

public class GladLibTest {

  @Test
  public void testMakeStory() throws Exception {

    GladLib gladLib = new GladLib();
    gladLib.makeStory();
  }
}