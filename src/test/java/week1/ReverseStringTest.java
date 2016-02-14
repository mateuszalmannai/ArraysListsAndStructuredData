package week1;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
public class ReverseStringTest {

  @Test
  public void testReverseStringWithForLoop() throws Exception {
    ReverseString reverseString = new ReverseString();
    String reverse = reverseString.reverseStringWithForLoop("computer");
    assertThat(reverse, is("retupmoc"));
  }

  @Test
  public void testReverseStringWithWhileLoop() throws Exception {
    ReverseString reverseString = new ReverseString();
    String reverse = reverseString.reverseStringWithWhileLoop("This is a test");
    assertThat(reverse, is("tset a si sihT"));
  }
}